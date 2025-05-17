package com.sanofi.pharma.service;

import com.sanofi.pharma.dto.api.audit_log.AuditLogListReq;
import com.sanofi.pharma.model.AuditLog;

import java.util.List;

public interface AuditLogService {
    List<AuditLog> list(AuditLogListReq req);
}
