package com.sanofi.pharma.dao;

import com.sanofi.pharma.dto.common.PaginationRequest;
import com.sanofi.pharma.model.Pharmacy;
import com.sanofi.pharma.model.PharmacyDrugAllocation;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PharmacyDrugAllocationDao {

    List<PharmacyDrugAllocation> getByDrugId(Long drugId);


    int updateVersionAndLimitByIdAndVersion(Long id, Integer version, Integer newLimit);
}
