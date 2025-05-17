package com.sanofi.pharma.service.impl;

import com.sanofi.pharma.dao.DrugDao;
import com.sanofi.pharma.dao.DrugLotDao;
import com.sanofi.pharma.dto.api.drug_lot.DrugLotCreateReq;
import com.sanofi.pharma.dto.common.ApiResponse;
import com.sanofi.pharma.dto.common.PaginationRequest;
import com.sanofi.pharma.exception.AssetNotFoundException;
import com.sanofi.pharma.model.Drug;
import com.sanofi.pharma.model.DrugLot;
import com.sanofi.pharma.service.DrugLotService;
import com.sanofi.pharma.util.IdGenerator;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugLotServiceImpl implements DrugLotService {
    private final DrugLotDao drugLotDao;
    private final DrugDao drugDao;

    public DrugLotServiceImpl(DrugLotDao drugLotDao, DrugDao drugDao) {
        this.drugLotDao = drugLotDao;
        this.drugDao = drugDao;
    }

    @Override
    public DrugLot create(DrugLotCreateReq req) {
        Drug drug = this.drugDao.get(req.getDrugId());
        if (drug == null) {
            throw new AssetNotFoundException(String.format("Drug %d not existed", req.getDrugId()));
        }
        DrugLot drugLot = req.toEntity();
        drugLot.setId(IdGenerator.generateDrugId());
        drugLot.setName(drug.getName());
        return drugLotDao.create(drugLot);
    }

    @Override
    public ApiResponse<List<DrugLot>> list(PaginationRequest pageRequest) {
        Page<DrugLot> drugPage = drugLotDao.list(pageRequest);
        return ApiResponse.fromPage(drugPage);
    }
}
