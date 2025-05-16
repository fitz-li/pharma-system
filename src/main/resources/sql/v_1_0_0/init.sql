/*
 * PharmaFlow — Pharma Supply Chain & Prescription Fulfillment System
 */


CREATE TABLE drug (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    manufacturer VARCHAR(255) NOT NULL,
    batch_number VARCHAR(100) NOT NULL,
    expiry_date DATE NOT NULL,
    stock INTEGER NOT NULL CHECK (stock >= 0)
);

CREATE TABLE pharmacy (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255)
);

CREATE TABLE pharmacy_drug_allocation (
    pharmacy_id INTEGER NOT NULL REFERENCES pharmacy(id),
    drug_id INTEGER NOT NULL REFERENCES drug(id),
    allocation_limit INTEGER NOT NULL CHECK (allocation_limit >= 0),
    PRIMARY KEY (pharmacy_id, drug_id)
);

CREATE TABLE prescription (
    id SERIAL PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    pharmacy_id INTEGER NOT NULL REFERENCES pharmacy(id),
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE prescription_drug (
    prescription_id INTEGER NOT NULL REFERENCES prescription(id) ON DELETE CASCADE,
    drug_id INTEGER NOT NULL REFERENCES drug(id),
    dosage VARCHAR(100) NOT NULL,
    PRIMARY KEY (prescription_id, drug_id)
);

CREATE TABLE audit_log (
    id SERIAL PRIMARY KEY,
    prescription_id INTEGER REFERENCES prescription(id),
    patient_id BIGINT,
    pharmacy_id INTEGER,
    drugs_requested TEXT,
    drugs_dispensed TEXT,
    status VARCHAR(50),
    failure_reason TEXT,
    timestamp TIMESTAMP NOT NULL DEFAULT NOW()
);