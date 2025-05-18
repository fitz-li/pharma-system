package com.sanofi.pharma.dao;

import com.sanofi.pharma.model.Prescription;

public interface PrescriptionDao {

    Prescription create(Prescription prescription);

    Prescription get(Long id);

    boolean complete(Long id);

    boolean failed(Long id);

}
