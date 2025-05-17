package com.sanofi.pharma.dao.repository;


import com.sanofi.pharma.model.Prescription;
import com.sanofi.pharma.model.PrescriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    int updateStatusById(Long id, PrescriptionStatus status);
}
