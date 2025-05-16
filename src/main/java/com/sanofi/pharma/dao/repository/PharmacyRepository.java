package com.sanofi.pharma.dao.repository;


import com.sanofi.pharma.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
}
