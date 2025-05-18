package com.sanofi.pharma.exception;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class StockNotEnoughException extends ApiException {
    public StockNotEnoughException(String message) {
        super("STOCK_NOT_ENOUGH", message);
    }
}
