package com.sanofi.pharma.service.impl;

import com.sanofi.pharma.dao.AuditLogDao;
import com.sanofi.pharma.dto.api.audit_log.AuditLogListReq;
import com.sanofi.pharma.model.AuditLog;
import com.sanofi.pharma.service.AuditLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditLogServiceImpl implements AuditLogService {
    private final AuditLogDao auditLogDao;

    public AuditLogServiceImpl(AuditLogDao auditLogDao) {
        this.auditLogDao = auditLogDao;
    }

    @Override
    public List<AuditLog> list(AuditLogListReq req) {
        return auditLogDao.list(req);
    }
}
