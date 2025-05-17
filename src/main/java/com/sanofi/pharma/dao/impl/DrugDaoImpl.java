package com.sanofi.pharma.dao.impl;

import com.sanofi.pharma.dao.DrugDao;
import com.sanofi.pharma.dao.repository.DrugRepository;
import com.sanofi.pharma.model.Drug;
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
    public Drug get(Long id) {
        return drugRepository.findById(id).orElse(null);
    }

}
