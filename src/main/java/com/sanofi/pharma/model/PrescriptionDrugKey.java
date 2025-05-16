package com.sanofi.pharma.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class PrescriptionDrugKey {
    private Long prescriptionId;
    private Long drugId;
    // equals & hashCode
}
