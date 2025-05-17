package com.sanofi.pharma.service.impl;

import com.sanofi.pharma.aop.Audit;
import com.sanofi.pharma.dao.*;
import com.sanofi.pharma.dto.api.prescription.*;
import com.sanofi.pharma.exception.FulfillException;
import com.sanofi.pharma.exception.LockException;
import com.sanofi.pharma.model.*;
import com.sanofi.pharma.service.PrescriptionService;
import com.sanofi.pharma.util.IdGenerator;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final Integer MAX_RETRY = 3;
    private final PharmacyDao pharmacyDao;
    private final DrugLotDao drugDao;
    private final PrescriptionTrans trans;
    private final PrescriptionDao prescriptionDao;

    private final PharmacyDrugAllocationDao pharmacyDrugAllocationDao;

    public PrescriptionServiceImpl(DrugLotDao drugDao,
                                   PharmacyDao pharmacyDao,
                                   PrescriptionTrans trans,
                                   PrescriptionDao prescriptionDao,
                                   PharmacyDrugAllocationDao pharmacyDrugAllocationDao) {
        this.drugDao = drugDao;
        this.pharmacyDao = pharmacyDao;
        this.prescriptionDao = prescriptionDao;
        this.pharmacyDrugAllocationDao = pharmacyDrugAllocationDao;
        this.trans = trans;
    }

    @Override
    public Prescription create(PrescriptionCreateReq req) throws BadRequestException {
        // valid pharmacy patient doctor
        Pharmacy pharmacy = pharmacyDao.get(req.getPharmacyId());
        if (pharmacy == null) {
            throw new BadRequestException("Pharmacy not existed");
        }

        for (PrescriptionDrugReq drugInPrescription : req.getDrugs()) {
            DrugLot drug = drugDao.get(drugInPrescription.getDrugId());
            if (drug == null) {
                throw new BadRequestException(String.format("Drug %d not existed", drugInPrescription.getDrugId()));
            }
        }

        Prescription prescription = req.toEntity();
        prescription.setStatus(PrescriptionStatus.PENDING);
        prescription.setId(IdGenerator.generatePrescriptionId());
        List<PrescriptionDrug> prescriptionDrugs = req.getPrescriptionDrugs();
        for (PrescriptionDrug prescriptionDrug : prescriptionDrugs) {
            prescriptionDrug.setId(IdGenerator.generateRelationId());
            prescriptionDrug.setPrescription(new Prescription(prescription.getId()));
        }
        trans.createRecords(prescription, prescriptionDrugs);
        return prescription;
    }

    @Override
    public List<FulfillDetail> fulfill(PrescriptionFulfillReq req) throws BadRequestException {
        Prescription prescription = prescriptionDao.get(req.getPrescriptionId());
        return fulfillRetry(prescription);
    }

    @Audit
    private List<FulfillDetail> fulfillRetry(Prescription prescription) throws BadRequestException {
        for (int i = 0; i < MAX_RETRY; i++) {
            try {
                FulfillPlan plan = preparePlan(prescription);
                trans.executePlan(prescription.getId(), plan.getAllocationUpdateList());
                return plan.getFulfillDetailList();
            } catch (Exception e) {
                if (!(e instanceof LockException)) {
                    throw e;
                }
            }
        }
        throw new FulfillException("Fulfill failed, reach the max try");
    }

    private FulfillPlan preparePlan(Prescription prescription) throws BadRequestException {
        FulfillPlan plan = new FulfillPlan();
        List<FulfillDetail> fulfillDetails = new ArrayList<>();
        List<PharmacyDrugAllocation> planAllocationList = new ArrayList<>();
        plan.setFulfillDetailList(fulfillDetails);
        plan.setAllocationUpdateList(planAllocationList);
        for (PrescriptionDrug drug : prescription.getDrugs()) {
            List<PharmacyDrugAllocation> allocationList = this.pharmacyDrugAllocationDao.getByDrugId(drug.getDrugId());

            Integer acquiredDosage = drug.getDosage();

            for (PharmacyDrugAllocation pharmacyDrugAllocation : allocationList) {
                if (pharmacyDrugAllocation.getAllocationLimit() >= acquiredDosage) {
                    pharmacyDrugAllocation.setAllocationLimit(pharmacyDrugAllocation.getAllocationLimit() - acquiredDosage);
                    fulfillDetails.add(new FulfillDetail(drug.getId(), pharmacyDrugAllocation.getDrugLot().getId(), acquiredDosage));
                    planAllocationList.add(pharmacyDrugAllocation);
                    acquiredDosage = 0;
                    break;
                } else {
                    acquiredDosage -= pharmacyDrugAllocation.getAllocationLimit();
                    fulfillDetails.add(new FulfillDetail(drug.getId(), pharmacyDrugAllocation.getDrugLot().getId(), pharmacyDrugAllocation.getAllocationLimit()));
                    pharmacyDrugAllocation.setAllocationLimit(0);
                    planAllocationList.add(pharmacyDrugAllocation);
                }
            }
            // not enough
            if (acquiredDosage != 0) {
                throw new BadRequestException(String.format("Drug %d is not enough", drug.getDrugId()));
            }
        }
        return plan;
    }
}
