package com.sanofi.pharma.dto.api.prescription;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FulfillDetail {
    private Long drugId;
    private Long drugLotId;
    private Integer dosage;
}

