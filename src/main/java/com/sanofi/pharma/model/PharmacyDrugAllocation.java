package com.sanofi.pharma.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(
        name = "pharmacy_drug_allocation",
        indexes = {
                @Index(name = "idx_drug_id", columnList = "drug_id")
        }
)
@Data
public class PharmacyDrugAllocation {
    @Id
    private Long id;

    private Long pharmacyId;

    @ManyToOne
    @JoinColumn(name = "drug_id", nullable = false)
    private Drug drug;

    @ManyToOne
    @JoinColumn(name = "drug_lot_id", nullable = false)
    private DrugLot drugLot;

    private Integer allocationLimit;

    private Integer version;

    private Instant expiryDate;

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
