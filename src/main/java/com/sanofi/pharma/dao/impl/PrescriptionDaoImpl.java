package com.sanofi.pharma.dao.impl;

import com.sanofi.pharma.dao.PrescriptionDao;
import com.sanofi.pharma.dao.repository.PrescriptionRepository;
import com.sanofi.pharma.model.Prescription;
import com.sanofi.pharma.model.PrescriptionStatus;
import org.springframework.stereotype.Repository;

@Repository
public class PrescriptionDaoImpl implements PrescriptionDao {

    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionDaoImpl(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public Prescription create(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    @Override
    public Prescription get(Long id) {
        return prescriptionRepository.getById(id);
    }

    @Override
    public int complete(Long id) {
        return prescriptionRepository.updateStatusById(id, PrescriptionStatus.FULFILLED);
    }
}
