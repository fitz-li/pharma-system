package com.sanofi.pharma.dao.impl;

import com.sanofi.pharma.dao.PrescriptionDrugDao;
import com.sanofi.pharma.dao.repository.PrescriptionDrugRepository;
import com.sanofi.pharma.model.PrescriptionDrug;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PrescriptionDrugDaoImpl implements PrescriptionDrugDao {

    private final PrescriptionDrugRepository prescriptionDrugRepository;

    public PrescriptionDrugDaoImpl(PrescriptionDrugRepository prescriptionDrugRepository) {
        this.prescriptionDrugRepository = prescriptionDrugRepository;
    }

    @Override
    public List<PrescriptionDrug> batchCreate(List<PrescriptionDrug> prescriptionDrugs) {
        return prescriptionDrugRepository.saveAll(prescriptionDrugs);
    }

    @Override
    public List<PrescriptionDrug> listByPrescription(Long prescriptionId) {
        return prescriptionDrugRepository.findByPrescriptionId(prescriptionId);
    }

    @Override
    public void clean() {
        prescriptionDrugRepository.deleteAll();
    }
}
