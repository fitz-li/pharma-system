package com.sanofi.pharma.dao.impl;

import com.sanofi.pharma.dao.DrugDao;
import com.sanofi.pharma.dto.common.PaginationRequest;
import com.sanofi.pharma.model.Drug;
import com.sanofi.pharma.dao.repository.DrugRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class DrugDaoImpl implements DrugDao {

    private final DrugRepository drugRepository;

    public DrugDaoImpl(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    @Override
    public Drug create(Drug drug) {
        return drugRepository.save(drug);
    }

    @Override
    public Page<Drug> list(@NotNull PaginationRequest pageRequest) {
        Pageable pageable = PageRequest.of(pageRequest.getPageNum(), pageRequest.getPageSize());
        return drugRepository.findAll(pageable);
    }

}
