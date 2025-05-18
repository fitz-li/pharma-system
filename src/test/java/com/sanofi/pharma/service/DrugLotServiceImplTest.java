package com.sanofi.pharma.service;

import com.sanofi.pharma.dao.DrugDao;
import com.sanofi.pharma.dao.DrugLotDao;
import com.sanofi.pharma.dto.api.drug_lot.DrugLotCreateReq;
import com.sanofi.pharma.model.Drug;
import com.sanofi.pharma.model.DrugLot;
import com.sanofi.pharma.service.impl.DrugLotServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DrugLotServiceImplTest {

    @Mock
    private DrugLotDao drugLotDao;

    @Mock
    private DrugDao drugDao;

    @InjectMocks
    private DrugLotServiceImpl drugService;

    @Test
    void testCreate() {
        //mock
        DrugLot expected = new DrugLot();
        when(drugLotDao.create(any(DrugLot.class))).thenReturn(expected);

        when(drugDao.get(any(Long.class))).thenReturn(new Drug());

        //test
        DrugLotCreateReq input = new DrugLotCreateReq();
        input.setDrugId(9527L);
        input.setManufacturer("666");
        input.setBatchNumber("IMDN-412921");
        input.setExpiryDate(Instant.now());
        input.setStock(100);
        DrugLot result = drugService.create(input);

        //assert
        assertThat(result).isSameAs(expected);
        verify(drugLotDao, times(1)).create(any(DrugLot.class));
    }
}
