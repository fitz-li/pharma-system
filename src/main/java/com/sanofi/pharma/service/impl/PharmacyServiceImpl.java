package com.sanofi.pharma.service.impl;

import com.sanofi.pharma.dao.PharmacyDao;
import com.sanofi.pharma.dto.common.ApiResponse;
import com.sanofi.pharma.dto.common.PaginationRequest;
import com.sanofi.pharma.model.Pharmacy;
import com.sanofi.pharma.service.PharmacyService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacyServiceImpl implements PharmacyService {
    private final PharmacyDao pharmacyDao;

    public PharmacyServiceImpl(PharmacyDao pharmacyDao) {
        this.pharmacyDao = pharmacyDao;
    }

    @Override
    public ApiResponse<List<Pharmacy>> list(PaginationRequest pageRequest) {
        Page<Pharmacy> pharmacyPage = pharmacyDao.list(pageRequest);
        return ApiResponse.fromPage(pharmacyPage);
    }
}
