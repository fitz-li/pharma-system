package com.sanofi.pharma.aop;


import com.sanofi.pharma.dao.AuditLogDao;
import com.sanofi.pharma.dao.PrescriptionDao;
import com.sanofi.pharma.dto.api.prescription.PrescriptionFulfillReq;
import com.sanofi.pharma.model.AuditLog;
import com.sanofi.pharma.model.Prescription;
import com.sanofi.pharma.model.PrescriptionStatus;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;

@Aspect
@Component
public class AuditAspect {
    private final AuditLogDao auditLogDao;
    private final PrescriptionDao prescriptionDao;

    public AuditAspect(AuditLogDao auditLogDao, PrescriptionDao prescriptionDao) {
        this.auditLogDao = auditLogDao;
        this.prescriptionDao = prescriptionDao;
    }

    @Around("@annotation(com.sanofi.pharma.aop.Audit)")
    public Object audit(ProceedingJoinPoint pjp) throws Throwable {
        // Extract Prescription and drug details from method args
        PrescriptionFulfillReq req = null;

        for (Object arg : pjp.getArgs()) {
            if (arg instanceof PrescriptionFulfillReq) {
                req = (PrescriptionFulfillReq) arg;
            }
        }
        if (req == null) {
            return pjp.proceed();
        }
        Prescription prescription = this.prescriptionDao.get(req.getPrescriptionId());

        if (prescription == null) {
            return pjp.proceed();
        }
        // Configure ObjectMapper to handle Java 8 date/time types
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        AuditLog log = new AuditLog();
        log.setPrescriptionId(prescription.getId());
        log.setDoctorId(prescription.getDoctorId());
        log.setPatientId(prescription.getPatientId());
        log.setPharmacyId(prescription.getPharmacyId());
        String drugsRequestedJson;
        try {
            drugsRequestedJson = mapper.writeValueAsString(prescription);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize prescription to JSON", e);
        }
        log.setDrugsRequested(drugsRequestedJson);
        try {
            // Proceed with the original method
            Object result = pjp.proceed();
            // Record success audit
            String dispensed = mapper.writeValueAsString(result);
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
