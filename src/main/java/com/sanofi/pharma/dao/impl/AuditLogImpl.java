package com.sanofi.pharma.dao.impl;

import com.sanofi.pharma.dto.api.audit_log.AuditLogListReq;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;

import com.sanofi.pharma.dao.AuditLogDao;
import com.sanofi.pharma.dao.repository.AuditLogRepository;
import com.sanofi.pharma.model.AuditLog;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<AuditLog> list(AuditLogListReq filter) {
        Specification<AuditLog> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getPatientId() != null) {
                predicates.add(cb.equal(root.get("patientId"), filter.getPatientId()));
            }
            if (filter.getPharmacyId() != null) {
                predicates.add(cb.equal(root.get("pharmacyId"), filter.getPharmacyId()));
            }
            if (filter.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), filter.getStatus()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return auditLogRepository.findAll(spec);
    }
}
