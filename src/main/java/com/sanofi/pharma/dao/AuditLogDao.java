package com.sanofi.pharma.dao;

import com.sanofi.pharma.dto.api.audit_log.AuditLogListReq;
import com.sanofi.pharma.model.AuditLog;

import java.util.List;

public interface AuditLogDao {
    AuditLog create(AuditLog auditLog);
    List<AuditLog> list(AuditLogListReq filter);

}
