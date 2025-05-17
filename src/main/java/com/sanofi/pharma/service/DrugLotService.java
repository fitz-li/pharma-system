package com.sanofi.pharma.service;

import com.sanofi.pharma.dto.api.drug_lot.DrugLotCreateReq;
import com.sanofi.pharma.dto.common.ApiResponse;
import com.sanofi.pharma.dto.common.PaginationRequest;
import com.sanofi.pharma.model.DrugLot;

import java.util.List;

public interface DrugLotService {
    /**
     * Create and persist a new DrugLot.
     *
     * @param drug the drugLot to create
     * @return the created DrugLot
     */
    DrugLot create(DrugLotCreateReq drug);

    ApiResponse<List<DrugLot>> list(PaginationRequest pageRequest);
}
