package com.sanofi.pharma.util;

/**
 * Distributed unique ID generator based on Snowflake algorithm.
 */
public class IdGenerator {
    // Custom epoch (March 1, 2021)
    private static final long EPOCH = 1614556800000L;

    private static final long WORKER_ID_BITS = 5L;
    private static final long DATACENTER_ID_BITS = 5L;
    private static final long SEQUENCE_BITS = 12L;

    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
    private static final long MAX_DATACENTER_ID = ~(-1L << DATACENTER_ID_BITS);

    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    private static final long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;

    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    private final long workerId;
    private final long datacenterId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    private IdGenerator(long workerId, long datacenterId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException("Worker ID out of range: " + workerId);
        }
        if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
            throw new IllegalArgumentException("Datacenter ID out of range: " + datacenterId);
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    // Holder for thread-safe lazy-initialized singleton
    private static class Holder {
        private static final IdGenerator INSTANCE = new IdGenerator(1, 1);
    }

    /**
     * Get the singleton instance of the ID generator.
     */
    private static IdGenerator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Generate the next unique ID.
     */
    private synchronized long nextId() {
        long timestamp = System.currentTimeMillis();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id.");
        }
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                while (timestamp <= lastTimestamp) {
                    timestamp = System.currentTimeMillis();
                }
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return ((timestamp - EPOCH) << TIMESTAMP_SHIFT)
                | (datacenterId << DATACENTER_ID_SHIFT)
                | (workerId << WORKER_ID_SHIFT)
                | sequence;
    }


    public static long generateId() {
        return getInstance().nextId();
    }

    /**
     * Generate a new unique ID for Drug entities.
     */
    public static long generateDrugId() {
        return getInstance().nextId();
    }

    /**
     * Generate a new unique ID for Pharmacy entities.
     */
    public static long generatePharmacyId() {
        return getInstance().nextId();
    }

    /**
     * Generate a new unique ID for Prescription entities.
     */
    public static long generatePrescriptionId() {
        return getInstance().nextId();
    }

    /**
     * Generate a new unique ID for AuditLog entries.
     */
    public static long generateAuditLogId() {
        return getInstance().nextId();
    }
}
