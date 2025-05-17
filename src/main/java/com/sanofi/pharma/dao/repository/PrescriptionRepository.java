package com.sanofi.pharma.dao.repository;


import com.sanofi.pharma.model.Prescription;
import com.sanofi.pharma.model.PrescriptionStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    @Modifying
    @Transactional
    @Query("""
            UPDATE Prescription p
            SET p.status = :status
            WHERE p.id = :id
              AND p.status = 'PENDING'
            """)
    int updateStatusById(Long id, PrescriptionStatus status);
}
