package com.sanofi.pharma.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "drug")
@Data
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String manufacturer;
    private String batchNumber;
    private LocalDate expiryDate;
    private Integer stock;

    // 一对多映射：Allocation
    @OneToMany(mappedBy = "drug")
    private List<PharmacyDrugAllocation> allocations;

    // getters/setters
}