package com.sanofi.pharma.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;


@Entity
@Table(name = "drug_lot")
@Data
public class DrugLot {
    @Id
    private Long id;
    private Long drugId;
    private String name;
    private String manufacturer;
    private String batchNumber;
    private LocalDate expiryDate;
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