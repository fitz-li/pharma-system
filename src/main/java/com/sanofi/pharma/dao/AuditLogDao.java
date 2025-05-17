package com.sanofi.pharma.dao;

import com.sanofi.pharma.dto.common.PaginationRequest;
import com.sanofi.pharma.model.AuditLog;
import com.sanofi.pharma.model.DrugLot;
import org.springframework.data.domain.Page;

public interface AuditLogDao {
    AuditLog create(AuditLog auditLog);

}
