package com.sanofi.pharma.dao;

import com.sanofi.pharma.dto.common.PaginationRequest;
import com.sanofi.pharma.model.Pharmacy;
import com.sanofi.pharma.model.Prescription;
import org.springframework.data.domain.Page;

public interface PrescriptionDao {

    Prescription create(Prescription prescription);

    Prescription get(Long id);

    int complete(Long id);

}
