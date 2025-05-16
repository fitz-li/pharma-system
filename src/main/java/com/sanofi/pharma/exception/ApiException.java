package com.sanofi.pharma.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApiException extends RuntimeException {
    private final String type;

    public ApiException(String type, String message) {
        super(message);
        this.type = type;
    }
    // getter
}
