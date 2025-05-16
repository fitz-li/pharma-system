package com.sanofi.pharma.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "prescription")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patientId;

    @ManyToOne
    private Pharmacy pharmacy;

    private String status;
    private java.time.LocalDateTime createdAt;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<PrescriptionDrug> drugs;
    // getters/setters
}
