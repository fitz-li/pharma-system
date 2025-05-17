package com.sanofi.pharma.dao.repository;


import com.sanofi.pharma.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
