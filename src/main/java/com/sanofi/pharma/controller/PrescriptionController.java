package com.sanofi.pharma.controller;

import com.sanofi.pharma.dto.api.prescription.FulfillDetail;
import com.sanofi.pharma.dto.api.prescription.PrescriptionCreateReq;
import com.sanofi.pharma.dto.api.prescription.PrescriptionFulfillReq;
import com.sanofi.pharma.dto.common.ApiResponse;
import com.sanofi.pharma.model.Prescription;
import com.sanofi.pharma.service.PrescriptionService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {
    private final PrescriptionService service;

    public PrescriptionController(PrescriptionService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<Prescription> create(@Valid @RequestBody PrescriptionCreateReq req) throws BadRequestException {
        return new ApiResponse<>(service.create(req));
    }

    @PostMapping("/{prescriptionId}/fulfill")
    public ApiResponse<List<FulfillDetail>> fulfill(
            @PathVariable("prescriptionId") Long prescriptionId,
            @Valid @RequestBody PrescriptionFulfillReq req
    ) throws BadRequestException {
        req.setPrescriptionId(prescriptionId);
        return new ApiResponse<>(service.fulfill(req));
    }
}
