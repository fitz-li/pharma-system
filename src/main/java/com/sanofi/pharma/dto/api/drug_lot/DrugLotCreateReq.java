package com.sanofi.pharma.dto.api.drug_lot;

import com.sanofi.pharma.model.Drug;
import com.sanofi.pharma.model.DrugLot;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;

@Data
public class DrugLotCreateReq {
    private Long drugId;
    @Size(min = 1, max = 20)
    private String manufacturer;
    @Size(min = 1, max = 100)
    private String batchNumber;
    private Instant expiryDate;
    @Min(1)
    private Integer stock;

    public DrugLot toEntity() {
        DrugLot drugLot = new DrugLot();
        Drug drug = new Drug();
        drug.setId(this.getDrugId());
        drugLot.setDrug(drug);
        drugLot.setStock(this.stock);
        drugLot.setManufacturer(this.manufacturer);
        drugLot.setBatchNumber(this.batchNumber);
        drugLot.setExpiryDate(this.expiryDate);
        return drugLot;
    }
}


