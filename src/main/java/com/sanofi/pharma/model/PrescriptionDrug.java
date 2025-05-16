package com.sanofi.pharma.model;

import jakarta.persistence.*;

@Entity
@Table(name = "prescription_drug")
public class PrescriptionDrug {
    @EmbeddedId
    private PrescriptionDrugKey id;

    @ManyToOne
    @MapsId("prescriptionId")
    private Prescription prescription;

    @ManyToOne
    @MapsId("drugId")
    private Drug drug;

    private String dosage;
    // getters/setters
}