package com.sanofi.pharma.service;


import com.sanofi.pharma.dao.PharmacyDao;
import com.sanofi.pharma.dao.PharmacyDrugAllocationDao;
import com.sanofi.pharma.dao.PrescriptionDao;
import com.sanofi.pharma.dto.api.prescription.PrescriptionCreateReq;
import com.sanofi.pharma.dto.api.prescription.PrescriptionDrugReq;
import com.sanofi.pharma.exception.ApiException;
import com.sanofi.pharma.model.*;
import com.sanofi.pharma.service.impl.PrescriptionServiceImpl;
import com.sanofi.pharma.service.impl.PrescriptionTrans;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PrescriptionServiceImplTest {

    @Mock
    private PharmacyDao pharmacyDao;
    @Mock
    private PrescriptionTrans trans;
    @Mock
    private PrescriptionDao prescriptionDao;
    @Mock
    private PharmacyDrugAllocationDao pharmacyDrugAllocationDao;
    @InjectMocks
    private PrescriptionServiceImpl service;

    @Test
    void testCreateSuccess() throws BadRequestException {
        when(pharmacyDao.get(eq(123L))).thenReturn(new Pharmacy());
        List<PharmacyDrugAllocation> allocations = new ArrayList<>();
        var allocation = new PharmacyDrugAllocation();
        allocation.setAllocationLimit(10);
        allocations.add(allocation);
        when(pharmacyDrugAllocationDao.getByDrugId(any(Long.class), any(Long.class))).thenReturn(allocations);

        //test
        PrescriptionCreateReq req = new PrescriptionCreateReq();
        req.setPharmacyId(123L);
        var drug = new PrescriptionDrugReq();
        drug.setDrugId(456L);
        drug.setDosage(5);
        req.setDrugs(new ArrayList<>());
        req.getDrugs().add(drug);
        Prescription prescription = service.create(req);

        //assert
        assertThat(prescription.getStatus()).isEqualTo(PrescriptionStatus.PENDING);
        verify(pharmacyDao, times(1)).get(eq(123L));
    }

    @Test
    void testCreateFailed() throws BadRequestException {
        when(pharmacyDao.get(eq(123L))).thenReturn(new Pharmacy());
        List<PharmacyDrugAllocation> allocations = new ArrayList<>();
        var allocation = new PharmacyDrugAllocation();
        allocation.setAllocationLimit(10);
        allocations.add(allocation);
        when(pharmacyDrugAllocationDao.getByDrugId(any(Long.class), any(Long.class))).thenReturn(allocations);

        //test
        PrescriptionCreateReq req = new PrescriptionCreateReq();
        req.setPharmacyId(123L);
        var drug = new PrescriptionDrugReq();
        drug.setDrugId(456L);
        drug.setDosage(12);
        req.setDrugs(new ArrayList<>());
        req.getDrugs().add(drug);
        try {
            Prescription prescription = service.create(req);
        } catch (ApiException e) {
            assertThat(e.getType()).isEqualTo("STOCK_NOT_ENOUGH");
        }
        //assert
        verify(pharmacyDao, times(1)).get(eq(123L));
    }
}
