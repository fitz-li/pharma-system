package com.sanofi.pharma.dao.impl;

import com.sanofi.pharma.dao.DrugLotDao;
import com.sanofi.pharma.dto.common.PaginationRequest;
import com.sanofi.pharma.dao.repository.DrugLotRepository;
import com.sanofi.pharma.model.DrugLot;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class DrugLotDaoImpl implements DrugLotDao {

    private final DrugLotRepository drugRepository;

    public DrugLotDaoImpl(DrugLotRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    @Override
    public DrugLot create(DrugLot drug) {
        return drugRepository.save(drug);
    }

    @Override
    public DrugLot get(Long id) {
        return drugRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        drugRepository.deleteById(id);
    }

    @Override
    public Page<DrugLot> list(@NotNull PaginationRequest pageRequest) {
        Pageable pageable = PageRequest.of(pageRequest.getPageNum(), pageRequest.getPageSize());
        return drugRepository.findAll(pageable);
    }

}
