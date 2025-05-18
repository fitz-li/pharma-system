package com.sanofi.pharma.service;

import com.sanofi.pharma.dto.common.ApiResponse;
import com.sanofi.pharma.dto.common.PaginationRequest;
import com.sanofi.pharma.model.Pharmacy;
import com.sanofi.pharma.model.PharmacyDrugAllocation;

import java.util.List;

public interface PharmacyService {


    ApiResponse<List<Pharmacy>> list(PaginationRequest pageRequest);

    List<PharmacyDrugAllocation> listDrugs(Long pharmacyId);
}
