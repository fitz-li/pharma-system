package com.sanofi.pharma.controller;

import com.sanofi.pharma.dto.api.drug_lot.DrugLotCreateReq;
import com.sanofi.pharma.dto.common.ApiResponse;
import com.sanofi.pharma.dto.common.PaginationRequest;
import com.sanofi.pharma.model.DrugLot;
import com.sanofi.pharma.service.DrugLotService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drug-lots")
public class DrugLotController {
    private final DrugLotService service;

    public DrugLotController(DrugLotService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<DrugLot> addDrug(@Valid @RequestBody DrugLotCreateReq drug) {
        DrugLot newDrug = service.create(drug);
        return new ApiResponse<>(newDrug);
    }

    @GetMapping
    public ApiResponse<List<DrugLot>> list(@Valid @ModelAttribute PaginationRequest pageRequest) {
        return service.list(pageRequest);
    }
}
