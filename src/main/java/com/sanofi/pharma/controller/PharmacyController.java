package com.sanofi.pharma.controller;

import com.sanofi.pharma.dto.common.ApiResponse;
import com.sanofi.pharma.dto.common.PaginationRequest;
import com.sanofi.pharma.model.Drug;
import com.sanofi.pharma.model.Pharmacy;
import com.sanofi.pharma.service.DrugService;
import com.sanofi.pharma.service.PharmacyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {
    private final PharmacyService service;

    public PharmacyController(PharmacyService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<Pharmacy>> list(@Valid @ModelAttribute PaginationRequest pageRequest) {
        return service.list(pageRequest);
    }
}
