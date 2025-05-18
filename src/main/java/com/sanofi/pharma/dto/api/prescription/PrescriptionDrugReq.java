package com.sanofi.pharma.dto.api.prescription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionDrugReq {
    private Long drugId;

    private Integer dosage;

}
