package com.sanofi.pharma.integration;

import com.sanofi.pharma.dao.*;
import com.sanofi.pharma.dto.api.drug_lot.DrugLotCreateReq;
import com.sanofi.pharma.dto.api.prescription.PrescriptionCreateReq;
import com.sanofi.pharma.dto.api.prescription.PrescriptionDrugReq;
import com.sanofi.pharma.dto.common.ApiResponse;
import com.sanofi.pharma.model.*;
import com.sanofi.pharma.util.IdGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.core.type.TypeReference;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PharmaApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DrugDao drugDao;

    @Autowired
    private DrugLotDao drugLotDao;

    @Autowired
    private PharmacyDao pharmacyDao;

    @Autowired
    private PharmacyDrugAllocationDao allocationDao;

    @Autowired
    private PrescriptionDao prescriptionDao;

    @Autowired
    private PrescriptionDrugDao prescriptionDrugDao;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void prepare() {
        // prepare workbench
    }

    @AfterEach
    void tearDown() {
        // clean workbench
        prescriptionDrugDao.clean();
        prescriptionDao.clean();
        allocationDao.clean();
        pharmacyDao.clean();
        drugLotDao.clean();
        drugDao.clean();
    }

    @Test
    void testIntegration() throws Exception {
        testDrugLot();
        testPharmacy();
        testPrescription();
    }

    void testDrugLot() throws Exception {

        // prepare data

        // 1) create Drug
        var newDrug = new Drug();
        newDrug.setName("TestDrug");
        Long drugId = IdGenerator.generateDrugId();
        newDrug.setId(drugId);
        drugDao.create(newDrug);


        // api test
        // 1)  test create drug lot
        DrugLotCreateReq lot = new DrugLotCreateReq();
        lot.setDrugId(drugId);
        lot.setManufacturer("TestManu");
        lot.setBatchNumber("B1");
        lot.setExpiryDate(Instant.now().plusSeconds(86400));
        lot.setStock(50);
        String json = objectMapper.writeValueAsString(lot);
        MvcResult r = mockMvc.perform(post("/pharma/v1/drug-lots")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").isNumber())
                .andExpect(jsonPath("$.data.name").value("TestDrug")).andReturn();

        String responseJson = r.getResponse().getContentAsString();

        TypeReference<ApiResponse<DrugLot>> typeRef = new TypeReference<>() {
        };
        ApiResponse<DrugLot> apiResponse = objectMapper.readValue(responseJson, typeRef);

        Long drugLotId = apiResponse.getData().getId();


        // 2) List drugs and verify the created one is present
        mockMvc.perform(get("/pharma/v1/drug-lots")
                        .param("pageNum", "0")
                        .param("pageSize", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(drugLotId));


        // clean data
        drugLotDao.delete(drugLotId);
        drugDao.delete(drugId);
    }

    void testPharmacy() throws Exception {

        // prepare data

        // 1) create Pharmacy
        var pharmacy = new Pharmacy();
        pharmacy.setName("TestPharmacy");
        pharmacy.setLocation("TestPharmacy location");
        Long pharmacyId = IdGenerator.generatePharmacyId();
        pharmacy.setId(pharmacyId);
        pharmacyDao.create(pharmacy);


        // api test
        // 1) List pharmacy and verify the created one is present
        mockMvc.perform(get("/pharma/v1/pharmacies")
                        .param("pageNum", "0")
                        .param("pageSize", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(pharmacyId));
        // clean data
        pharmacyDao.delete(pharmacyId);
    }

    void testPrescription() throws Exception {

        // prepare data


        // 1) create Drug
        var newDrug = new Drug();
        newDrug.setName("TestDrug");
        Long drugId = IdGenerator.generateDrugId();
        newDrug.setId(drugId);
        drugDao.create(newDrug);

        // 2) create DrugLot
        var lot = new DrugLot();
        lot.setDrug(newDrug);
        lot.setManufacturer("TestManu");
        lot.setBatchNumber("B1");
        lot.setExpiryDate(Instant.now().plusSeconds(86400));
        lot.setStock(50);
        lot.setName(newDrug.getName());
        lot.setId(IdGenerator.generateDrugId());
        drugLotDao.create(lot);


        // 3) create Pharmacy
        var pharmacy = new Pharmacy();
        pharmacy.setName("TestPharmacy");
        pharmacy.setLocation("TestPharmacy location");
        Long pharmacyId = IdGenerator.generatePharmacyId();
        pharmacy.setId(pharmacyId);
        pharmacyDao.create(pharmacy);

        // 4) create PharmacyDrugAllocation
        var allocation = new PharmacyDrugAllocation();
        allocation.setDrug(newDrug);
        allocation.setPharmacy(pharmacy);
        allocation.setAllocationLimit(20);
        allocation.setVersion(0);
        allocation.setDrugLot(lot);
        allocation.setExpiryDate(lot.getExpiryDate());
        allocation.setId(IdGenerator.generateId());
        allocationDao.create(allocation);


        // api test
        // 1.1) create prescription
        PrescriptionCreateReq req = new PrescriptionCreateReq();
        req.setPharmacyId(pharmacyId);
        req.setPatientId(IdGenerator.generateId());
        req.setDoctorId(IdGenerator.generateId());
        List<PrescriptionDrugReq> drugs = new ArrayList<>();
        drugs.add(new PrescriptionDrugReq(drugId, 10));
        req.setDrugs(drugs);
        String json = objectMapper.writeValueAsString(req);
        MvcResult r = mockMvc.perform(post("/pharma/v1/prescriptions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").isNumber()).andReturn();


        // 1.2) test create failed


        String responseJson = r.getResponse().getContentAsString();

        TypeReference<ApiResponse<Prescription>> typeRef = new TypeReference<>() {
        };
        ApiResponse<Prescription> apiResponse = objectMapper.readValue(responseJson, typeRef);

        Long prescriptionId = apiResponse.getData().getId();

        // fulfill success
        mockMvc.perform(post(String.format("/pharma/v1/prescriptions/%d/fulfill", prescriptionId))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
        // fulfill failed

    }
}
