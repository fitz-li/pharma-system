package com.sanofi.pharma.dao.impl;

import com.sanofi.pharma.dao.PharmacyDrugAllocationDao;
import com.sanofi.pharma.dao.repository.PharmacyDrugAllocationRepository;
import com.sanofi.pharma.model.PharmacyDrugAllocation;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public class PharmacyDrugAllocationDaoImpl implements PharmacyDrugAllocationDao {

    private final PharmacyDrugAllocationRepository pharmacyDrugAllocationRepository;

    public PharmacyDrugAllocationDaoImpl(PharmacyDrugAllocationRepository pharmacyDrugAllocationRepository) {
        this.pharmacyDrugAllocationRepository = pharmacyDrugAllocationRepository;
    }


    @Override
    public List<PharmacyDrugAllocation> getByDrugId(Long drugId) {
        return pharmacyDrugAllocationRepository.findByDrugIdAndExpiryDateAfter(drugId, Instant.now());
    }

    @Override
    public int updateVersionAndLimitByIdAndVersion(Long id, Integer version, Integer newLimit) {
        return pharmacyDrugAllocationRepository.updateVersionAndLimitByIdAndVersion(id, version, newLimit);
    }

}
