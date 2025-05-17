package com.sanofi.pharma.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "audit_log")
@Data
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long doctorId;

    private Long prescriptionId;
    private Long patientId;
    private Long pharmacyId;
    /**
     * Raw text of requested drugs details; allows large content.
     */
    @Lob
    @Column(name = "drugs_requested", columnDefinition = "TEXT")
    private String drugsRequested;
    /**
     * Raw text of requested drugs details; allows large content.
     */
    @Lob
    @Column(name = "drugs_dispensed", columnDefinition = "TEXT")
    private String drugsDispensed;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PrescriptionStatus status;
    private String failureReason;

    /**
     * Record creation timestamp (set automatically on insert).
     */
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant createdAt;

    /**
     * Record last update timestamp (set automatically on update).
     */
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant updatedAt;
}