package com.sanofi.pharma.service;

import com.sanofi.pharma.dto.api.prescription.FulfillDetail;
import com.sanofi.pharma.dto.api.prescription.PrescriptionCreateReq;
import com.sanofi.pharma.dto.api.prescription.PrescriptionFulfillReq;
import com.sanofi.pharma.model.Prescription;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface PrescriptionService {
    Prescription create(PrescriptionCreateReq req) throws BadRequestException;

    List<FulfillDetail> fulfill(PrescriptionFulfillReq req) throws BadRequestException;
}
