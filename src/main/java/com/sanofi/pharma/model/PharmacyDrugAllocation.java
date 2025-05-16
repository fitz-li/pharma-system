package com.sanofi.pharma.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pharmacy_drug_allocation")
public class PharmacyDrugAllocation {
    @EmbeddedId
    private PharmacyDrugKey id;

    @ManyToOne
    @MapsId("pharmacyId")
    private Pharmacy pharmacy;

    @ManyToOne
    @MapsId("drugId")
    private Drug drug;

    private Integer allocationLimit;
    // getters/setters
}
