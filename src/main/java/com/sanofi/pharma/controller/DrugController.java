package com.sanofi.pharma.controller;

import com.sanofi.pharma.dto.common.ApiResponse;
import com.sanofi.pharma.dto.common.PaginationRequest;
import com.sanofi.pharma.model.Drug;
import com.sanofi.pharma.service.DrugService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drugs")
public class DrugController {
    private final DrugService service;

    public DrugController(DrugService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<Drug> addDrug(@RequestBody Drug drug) {
        Drug newDrug = service.create(drug);
        return new ApiResponse<>(newDrug);
    }

    @GetMapping
    public ApiResponse<List<Drug>> list(@Valid @ModelAttribute PaginationRequest pageRequest) {
        return service.list(pageRequest);
    }
}
