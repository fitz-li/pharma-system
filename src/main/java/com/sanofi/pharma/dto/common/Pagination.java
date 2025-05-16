package com.sanofi.pharma.dto.common;

import lombok.Data;

@Data

public class Pagination {
    public Pagination(Long total) {
        this.total = total;
    }

    private String cursor;
    private Integer pageSize;
    private Long total;
}
