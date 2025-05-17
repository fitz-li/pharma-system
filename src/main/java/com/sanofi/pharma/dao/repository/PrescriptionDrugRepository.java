package com.sanofi.pharma.dao.repository;


import com.sanofi.pharma.model.PrescriptionDrug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionDrugRepository extends JpaRepository<PrescriptionDrug, Long> {

    List<PrescriptionDrug> findByPrescriptionId(Long prescriptionId);
}
