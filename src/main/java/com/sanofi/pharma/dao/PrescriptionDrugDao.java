package com.sanofi.pharma.dao;

import com.sanofi.pharma.model.PrescriptionDrug;

import java.util.List;

public interface PrescriptionDrugDao {
    List<PrescriptionDrug> batchCreate(List<PrescriptionDrug> prescriptionDrugs);

    List<PrescriptionDrug> listByPrescription(Long prescriptionId);

    void clean();

}
