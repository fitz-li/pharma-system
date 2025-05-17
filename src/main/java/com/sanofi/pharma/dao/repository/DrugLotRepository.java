package com.sanofi.pharma.dao.repository;


import com.sanofi.pharma.model.Drug;
import com.sanofi.pharma.model.DrugLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugLotRepository extends JpaRepository<DrugLot, Long> {
}
