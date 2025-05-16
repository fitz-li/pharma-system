package com.sanofi.pharma.dto.common;

import lombok.Data;

@Data
public class PaginationRequest {
    private String cursor;
    private Integer pageNum;
    private Integer pageSize;
}
