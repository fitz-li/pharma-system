package com.sanofi.pharma.dto.api.prescription;

import com.sanofi.pharma.model.Drug;
import com.sanofi.pharma.model.Prescription;
import com.sanofi.pharma.model.PrescriptionDrug;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PrescriptionCreateReq {

    private Long doctorId;

    private Long patientId;

    private Long pharmacyId;

    private List<PrescriptionDrugReq> drugs;

    public Prescription toEntity() {
        Prescription p = new Prescription();
        p.setDoctorId(this.getDoctorId());
        p.setPatientId(this.getPatientId());
        p.setPharmacyId(this.getPharmacyId());
        return p;
    }

    public List<PrescriptionDrug> getPrescriptionDrugs() {
        List<PrescriptionDrug> drugRecords = new ArrayList<>();
        for (PrescriptionDrugReq drugInPrescription : this.getDrugs()) {
            PrescriptionDrug drug = new PrescriptionDrug();
            drug.setDosage(drugInPrescription.getDosage());
            drugRecords.add(drug);
        }
        return drugRecords;
    }
}



