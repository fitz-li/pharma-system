package com.sanofi.pharma.controller;

import com.sanofi.pharma.dto.api.audit_log.AuditLogListReq;
import com.sanofi.pharma.dto.common.ApiResponse;
import com.sanofi.pharma.model.AuditLog;
import com.sanofi.pharma.service.AuditLogService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audit-logs")
public class AuditLogController {
    private final AuditLogService service;

    public AuditLogController(AuditLogService service) {
        this.service = service;
    }

    @GetMapping()
    public ApiResponse<List<AuditLog>> list(
            @Valid @ModelAttribute AuditLogListReq req
    ) throws BadRequestException {
        return new ApiResponse<>(service.list(req));
    }
}
