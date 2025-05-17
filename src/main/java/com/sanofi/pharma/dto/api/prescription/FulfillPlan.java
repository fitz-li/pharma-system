package com.sanofi.pharma.dto.api.prescription;

import com.sanofi.pharma.model.PharmacyDrugAllocation;
import lombok.Data;

import java.util.List;

@Data
public class FulfillPlan {
    private List<FulfillDetail> fulfillDetailList;
    private List<PharmacyDrugAllocation> allocationUpdateList;
}
