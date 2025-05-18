package com.sanofi.pharma.dao;

import com.sanofi.pharma.model.PharmacyDrugAllocation;

import java.util.List;

public interface PharmacyDrugAllocationDao {

    List<PharmacyDrugAllocation> getByDrugId(Long pharmacyId, Long drugId);

    List<PharmacyDrugAllocation> listByPharmacyId(Long pharmacyId);


    PharmacyDrugAllocation create(PharmacyDrugAllocation allocation);

    void delete(Long id);

    void clean();


    int updateVersionAndLimitByIdAndVersion(Long id, Integer version, Integer newLimit);
}
