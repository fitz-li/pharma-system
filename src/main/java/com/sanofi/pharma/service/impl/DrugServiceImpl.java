package com.sanofi.pharma.service.impl;

import com.sanofi.pharma.dao.DrugDao;
import com.sanofi.pharma.dto.common.ApiResponse;
import com.sanofi.pharma.dto.common.PaginationRequest;
import com.sanofi.pharma.model.Drug;
import com.sanofi.pharma.service.DrugService;
import com.sanofi.pharma.util.IdGenerator;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DrugServiceImpl implements DrugService {
    private final DrugDao drugDao;

    public DrugServiceImpl(DrugDao drugDao) {
        this.drugDao = drugDao;
    }

    @Override
    @Transactional
    public Drug create(Drug drug) {
        drug.setId(IdGenerator.generateDrugId());
        return drugDao.create(drug);
    }

    @Override
    public ApiResponse<List<Drug>> list(PaginationRequest pageRequest) {
        Page<Drug> drugPage = drugDao.list(pageRequest);
        return ApiResponse.fromPage(drugPage);
    }
}
