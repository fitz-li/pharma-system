package com.sanofi.pharma.dao;

import com.sanofi.pharma.dto.common.PaginationRequest;
import com.sanofi.pharma.model.DrugLot;
import org.springframework.data.domain.Page;

public interface DrugLotDao {
    DrugLot create(DrugLot drug);

    DrugLot get(Long id);

    Page<DrugLot> list(PaginationRequest pageRequest);

}
