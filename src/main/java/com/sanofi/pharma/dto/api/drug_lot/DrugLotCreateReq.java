package com.sanofi.pharma.dto.api.drug_lot;

import com.sanofi.pharma.model.DrugLot;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DrugLotCreateReq {
    @NotEmpty
    private Long drugId;
    @Min(1)
    @Max(20)
    private String manufacturer;
    private String batchNumber;
    private LocalDate expiryDate;
    private Integer stock;

    public DrugLot toEntity() {
        DrugLot drug = new DrugLot();
        drug.setStock(this.stock);
        drug.setManufacturer(this.manufacturer);
        drug.setBatchNumber(this.batchNumber);
        drug.setExpiryDate(this.expiryDate);
        return drug;
    }
}


