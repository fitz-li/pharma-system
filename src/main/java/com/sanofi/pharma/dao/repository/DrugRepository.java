package com.sanofi.pharma.dao.repository;


import com.sanofi.pharma.model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepository extends JpaRepository<Drug, Long> {
}
