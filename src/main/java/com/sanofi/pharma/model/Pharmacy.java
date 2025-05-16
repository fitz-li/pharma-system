package com.sanofi.pharma.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "pharmacy")
@Data
public class Pharmacy {
    @Id
    private Long id;
    private String name;
    private String location;

    @OneToMany(mappedBy = "pharmacy")
    private List<PharmacyDrugAllocation> allocations;
}
