package com.sanofi.pharma.controller;

import com.sanofi.pharma.dto.common.ApiResponse;
import com.sanofi.pharma.dto.common.PaginationRequest;
import com.sanofi.pharma.model.Pharmacy;
import com.sanofi.pharma.model.PharmacyDrugAllocation;
import com.sanofi.pharma.service.PharmacyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmacies")
public class PharmacyController {
    private final PharmacyService service;

    public PharmacyController(PharmacyService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<Pharmacy>> list(@Valid @ModelAttribute PaginationRequest pageRequest) {
        return service.list(pageRequest);
    }

    @GetMapping("{pharmacyId}/drugs")
    public ApiResponse<List<PharmacyDrugAllocation>> listDrugs(@PathVariable("pharmacyId") Long pharmacyId) {
        return new ApiResponse<>(service.listDrugs(pharmacyId));
    }
}
