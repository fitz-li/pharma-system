package com.sanofi.pharma.dao;

import com.sanofi.pharma.dto.common.PaginationRequest;
import com.sanofi.pharma.model.Drug;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DrugDao {
    Drug create(Drug drug);

    Page<Drug> list(PaginationRequest pageRequest);

}
