package com.sanofi.pharma.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;


@Entity
@Table(name = "drug_lot")
@Data
public class DrugLot {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "drug_id", nullable = false)
    private Drug drug;
    private String name;
    private String manufacturer;
    private String batchNumber;
    private Instant expiryDate;
    private Integer stock;
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