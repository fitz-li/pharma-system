package com.sanofi.pharma.dao.impl;

import com.sanofi.pharma.dao.AuditLogDao;
import com.sanofi.pharma.dao.repository.AuditLogRepository;
import com.sanofi.pharma.model.AuditLog;
import org.springframework.stereotype.Repository;

@Repository
public class AuditLogImpl implements AuditLogDao {

    private final AuditLogRepository auditLogRepository;

    public AuditLogImpl(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Override
    public AuditLog create(AuditLog auditLog) {
        return this.auditLogRepository.save(auditLog);
    }
}
