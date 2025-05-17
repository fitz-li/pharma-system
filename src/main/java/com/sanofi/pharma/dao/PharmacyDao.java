package com.sanofi.pharma.dao;

import com.sanofi.pharma.dto.common.PaginationRequest;
import com.sanofi.pharma.model.Pharmacy;
import org.springframework.data.domain.Page;

public interface PharmacyDao {

    Page<Pharmacy> list(PaginationRequest pageRequest);

    Pharmacy get(Long id);
}
