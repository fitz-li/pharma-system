package com.sanofi.pharma.dto.api.prescription;

import lombok.Data;

@Data
public class PrescriptionDrugReq {
    private Long drugId;

    private Integer dosage;

}
