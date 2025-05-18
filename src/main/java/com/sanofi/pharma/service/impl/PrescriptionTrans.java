package com.sanofi.pharma.service.impl;

import com.sanofi.pharma.dao.PharmacyDrugAllocationDao;
import com.sanofi.pharma.dao.PrescriptionDao;
import com.sanofi.pharma.dao.PrescriptionDrugDao;
import com.sanofi.pharma.exception.ApiException;
import com.sanofi.pharma.exception.LockException;
import com.sanofi.pharma.model.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrescriptionTrans {
    private final PrescriptionDao prescriptionDao;
    private final PrescriptionDrugDao prescriptionDrugDao;

    private final PharmacyDrugAllocationDao allocationDao;

    public PrescriptionTrans(PrescriptionDao prescriptionDao, PrescriptionDrugDao prescriptionDrugDao, PharmacyDrugAllocationDao allocationDao) {
        this.prescriptionDao = prescriptionDao;
        this.prescriptionDrugDao = prescriptionDrugDao;
        this.allocationDao = allocationDao;
    }

    @Transactional
    protected void createRecords(@NotNull Prescription prescription, List<PrescriptionDrug> prescriptionDrugs) {
        prescriptionDao.create(prescription);
        prescriptionDrugDao.batchCreate(prescriptionDrugs);
    }

    @Transactional
    protected void executePlan(Long id, @NotNull List<PharmacyDrugAllocation> allocationList) {
        int totalAffected = 0;
        for (PharmacyDrugAllocation allocation : allocationList) {
            int updated = allocationDao.updateVersionAndLimitByIdAndVersion(
                    allocation.getId(), allocation.getVersion(), allocation.getAllocationLimit()
            );
            totalAffected += updated;
        }
        if (totalAffected < allocationList.size()) {
            prescriptionDao.failed(id);
            throw new LockException();
        }
        if (!prescriptionDao.complete(id)) {
            throw new ApiException("RECORD_MODIFIED", "Prescription status modified");
        }
    }
}
