package com.sanofi.pharma.dao.impl;

import com.sanofi.pharma.dao.PharmacyDao;
import com.sanofi.pharma.dto.common.PaginationRequest;
import com.sanofi.pharma.model.Pharmacy;
import com.sanofi.pharma.dao.repository.PharmacyRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PharmacyDaoImpl implements PharmacyDao {

    private final PharmacyRepository pharmacyRepository;

    public PharmacyDaoImpl(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }


    @Override
    public Page<Pharmacy> list(@NotNull PaginationRequest pageRequest) {
        Pageable pageable = PageRequest.of(pageRequest.getPageNum(), pageRequest.getPageSize());
        return pharmacyRepository.findAll(pageable);
    }

}
