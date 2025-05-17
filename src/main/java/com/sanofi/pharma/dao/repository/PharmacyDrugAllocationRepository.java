package com.sanofi.pharma.dao.repository;


import com.sanofi.pharma.model.PharmacyDrugAllocation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PharmacyDrugAllocationRepository extends JpaRepository<PharmacyDrugAllocation, Long> {
    List<PharmacyDrugAllocation> findByDrugId(Long drugId);

    @Modifying
    @Transactional
    @Query("""
    UPDATE PharmacyDrugAllocation p
    SET p.version = p.version + 1,
        p.allocationLimit = :newLimit
    WHERE p.id = :id
      AND p.version = :version
    """)
    int updateVersionAndLimitByIdAndVersion(Long id, Integer version, Integer newLimit);
}
