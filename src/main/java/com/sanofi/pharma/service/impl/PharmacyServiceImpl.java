package com.sanofi.pharma.service.impl;

import com.sanofi.pharma.dao.PharmacyDao;
import com.sanofi.pharma.dao.PharmacyDrugAllocationDao;
import com.sanofi.pharma.dto.common.ApiResponse;
import com.sanofi.pharma.dto.common.PaginationRequest;
import com.sanofi.pharma.model.Pharmacy;
import com.sanofi.pharma.model.PharmacyDrugAllocation;
import com.sanofi.pharma.service.PharmacyService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacyServiceImpl implements PharmacyService {
    private final PharmacyDao pharmacyDao;

    private final PharmacyDrugAllocationDao allocationDao;

    public PharmacyServiceImpl(PharmacyDao pharmacyDao, PharmacyDrugAllocationDao allocationDao) {
        this.pharmacyDao = pharmacyDao;
        this.allocationDao = allocationDao;
    }

    @Override
    public ApiResponse<List<Pharmacy>> list(PaginationRequest pageRequest) {
        Page<Pharmacy> pharmacyPage = pharmacyDao.list(pageRequest);
        return ApiResponse.fromPage(pharmacyPage);
    }

    @Override
    public List<PharmacyDrugAllocation> listDrugs(Long pharmacyId) {
        return allocationDao.listByPharmacyId(pharmacyId);
    }
}
