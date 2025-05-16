package com.sanofi.pharma.service;

import com.sanofi.pharma.dto.common.ApiResponse;
import com.sanofi.pharma.dto.common.PaginationRequest;
import com.sanofi.pharma.model.Drug;
import com.sanofi.pharma.model.Pharmacy;

import java.util.List;

public interface PharmacyService {


    ApiResponse<List<Pharmacy>> list(PaginationRequest pageRequest);
}
