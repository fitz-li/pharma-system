package com.sanofi.pharma.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class PharmacyDrugKey {
    private Long pharmacyId;
    private Long drugId;
    // equals & hashCode
}