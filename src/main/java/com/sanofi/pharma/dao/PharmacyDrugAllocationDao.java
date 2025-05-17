package com.sanofi.pharma.dao;

import com.sanofi.pharma.model.PharmacyDrugAllocation;

import java.util.List;

public interface PharmacyDrugAllocationDao {

    List<PharmacyDrugAllocation> getByDrugId(Long drugId);


    int updateVersionAndLimitByIdAndVersion(Long id, Integer version, Integer newLimit);
}
