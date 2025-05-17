package com.sanofi.pharma.dto.api.prescription;

import lombok.Data;

@Data
public class PrescriptionFulfillReq {
    private Long operatorId;
    private Long prescriptionId;
}


