package com.sanofi.pharma.dto.api.audit_log;

import com.sanofi.pharma.model.PrescriptionStatus;
import lombok.Data;

@Data
public class AuditLogListReq {
    private Long doctorId;

    private Long patientId;

    private Long pharmacyId;

    private PrescriptionStatus status;


}
