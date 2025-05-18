package com.sanofi.pharma.dao.repository;

import java.time.Instant;


import com.sanofi.pharma.model.PharmacyDrugAllocation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PharmacyDrugAllocationRepository extends JpaRepository<PharmacyDrugAllocation, Long> {
    /**
     * Find allocations for a given drug ID where the drug's expiry date is after the given date.
     *
     * @param drugId the ID of the drug
     * @param today  the current date to compare against expiryDate
     * @return list of non-expired allocations
     */
    List<PharmacyDrugAllocation> findByDrugIdAndExpiryDateAfter(Long drugId, Instant today);

    List<PharmacyDrugAllocation> findByPharmacyIdAndExpiryDateAfter(Long pharmacyId, Instant today);

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
