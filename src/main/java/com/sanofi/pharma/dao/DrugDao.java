package com.sanofi.pharma.dao;

import com.sanofi.pharma.model.Drug;

public interface DrugDao {
    Drug create(Drug drug);

    Drug get(Long id);


}
