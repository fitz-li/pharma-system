package com.sanofi.pharma.exception;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class LockException extends ApiException {
    public LockException() {
        super("OPTIMISTIC_LOCK_FAILED", "Not all allocations were updated, rolling back transaction");
    }
}
