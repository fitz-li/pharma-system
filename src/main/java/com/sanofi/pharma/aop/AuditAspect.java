package com.sanofi.pharma.aop;


import com.sanofi.pharma.dao.AuditLogDao;
import com.sanofi.pharma.model.AuditLog;
import com.sanofi.pharma.model.Prescription;
import com.sanofi.pharma.model.PrescriptionDrug;
import com.sanofi.pharma.model.PrescriptionStatus;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class AuditAspect {
    private final AuditLogDao auditLogDao;

    public AuditAspect(AuditLogDao auditLogDao) {
        this.auditLogDao = auditLogDao;
    }

    @Around("@annotation(com.sanofi.pharma.aop.Audit)")
    public Object audit(ProceedingJoinPoint pjp) throws Throwable {
        // Extract Prescription and drug details from method args
        Prescription prescription = null;
        List<PrescriptionDrug> drugs = null;

        for (Object arg : pjp.getArgs()) {
            if (arg instanceof Prescription) {
                prescription = (Prescription) arg;
            }
        }
        if (prescription == null) {
            return pjp.proceed();
        }
        AuditLog log = new AuditLog();
        log.setPrescriptionId(prescription.getId());
        log.setDoctorId(prescription.getDoctorId());
        log.setPatientId(prescription.getPatientId());
        log.setPharmacyId(prescription.getPatientId());
        String drugsRequestedJson = (new ObjectMapper()).writeValueAsString(prescription);
        log.setDrugsRequested(drugsRequestedJson);
        try {
            // Proceed with the original method
            Object result = pjp.proceed();
            // Record success audit
            String dispensed = (new ObjectMapper()).writeValueAsString(result);
            log.setDrugsDispensed(dispensed);
            log.setStatus(PrescriptionStatus.FULFILLED);
            auditLogDao.create(log);
            return result;
        } catch (Throwable ex) {
            // Record failure audit
            log.setStatus(PrescriptionStatus.FAILED);
            log.setFailureReason(ex.getMessage());
            auditLogDao.create(log);
            throw ex;
        }
    }
}
