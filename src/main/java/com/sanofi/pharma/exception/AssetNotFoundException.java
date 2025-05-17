package com.sanofi.pharma.exception;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class AssetNotFoundException extends ApiException {
    public AssetNotFoundException(String message) {
        super("NOT_FOUND", message);
    }
}
