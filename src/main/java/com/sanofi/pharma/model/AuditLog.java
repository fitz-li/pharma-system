package com.sanofi.pharma.model;

import jakarta.persistence.*;

@Entity
@Table(name = "audit_log")
public class AuditLog {
    @Id
    private Long id;
    private Long prescriptionId;
    private Long patientId;
    private Long pharmacyId;
    private String drugsRequested;
    private String drugsDispensed;
    private String status;
    private String failureReason;
    private java.time.LocalDateTime timestamp;
    // getters/setters
}