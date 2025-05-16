package com.sanofi.pharma.service;

import com.sanofi.pharma.dto.common.ApiResponse;
import com.sanofi.pharma.dto.common.PaginationRequest;
import com.sanofi.pharma.model.Drug;

import java.util.List;

public interface DrugService {
    /**
     * Create and persist a new Drug.
     *
     * @param drug the drug to create
     * @return the created Drug
     */
    Drug create(Drug drug);

    ApiResponse<List<Drug>> list(PaginationRequest pageRequest);
}
