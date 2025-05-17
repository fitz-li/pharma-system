package com.sanofi.pharma.exception;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class FulfillException extends ApiException {
    public FulfillException(String message) {
        super("FULFILL_FAILED", message);
    }
}
