# Challenge: Pharma Supply Chain and Prescription Fulfillment System

## Scenario

You are tasked with building a Pharma Supply Chain and Prescription Fulfillment System for a pharmaceutical company. The system will manage the inventory of drugs, prescriptions, and pharmacies, as well as the logistics of fulfilling patient prescriptions.

## Requirements

### Business Rules and Logic

#### 1. Drugs
- Each drug has details such as name, manufacturer, batch number, expiry date, and stock.
- Drugs are only dispensed if they are within their expiry date.

#### 2. Pharmacies
- Pharmacies can only dispense specific drugs they are contracted for.
- Pharmacies must not dispense more than their allocated amount of a specific drug.

#### 3. Prescriptions
- Prescriptions include multiple drugs, with a specific dosage for each drug.
- A prescription is valid only if all requested drugs are available and within the pharmacy's allocation limits.
- If any requested drug is unavailable, the prescription must fail with an appropriate error message.

#### 4. Fulfillment
- When a prescription is fulfilled, the stock for the dispensed drugs must be updated.
- If stock is insufficient for any drug in the prescription, the entire prescription must fail.

#### 5. Audit Logs
- Log each prescription attempt (successful or failed) with details like:
  - Prescription ID
  - Patient ID
  - Pharmacy ID
  - Drugs requested
  - Drugs dispensed
  - Failure reasons

### API Requirements

1. Add a Drug: Add a drug to the inventory, specifying its details and stock.
2. List Pharmacies: Retrieve all pharmacies and their contracted drugs.
3. Create a Prescription: Submit a prescription for a patient at a specific pharmacy.
4. Fulfill a Prescription: Process the prescription and update stock levels accordingly.
5. Get Audit Logs: Retrieve audit logs with filters for patient, pharmacy, or success/failure.

## Candidate Tasks

1. Design the Database Schema
2. Develop the APIs
3. Implement Business Logic
4. Write Unit Tests
5. Set Up Linting
6. Prepare Clear Documentation

## Technical Requirements

- Design a relational database schema in DB2 to support the described use case.
- Create RESTful APIs using Spring Boot.
- Implement business rules: drug expiry, stock validation, allocation limits, concurrency.
- Provide unit tests using JUnit or similar.
- Use a linting tool like Checkstyle.
- Prepare a detailed README file.

## README Checklist

1. Project Overview
2. Prerequisites
3. Setup Instructions
4. API Documentation
5. Testing Instructions
6. Linting Instructions
7. Assumptions

## Advanced Business Rules

### 1. Concurrent Fulfillment
- Handle concurrent requests using transactions or optimistic locking.

### 2. Partial Fulfillment
- If any drug in a prescription cannot be fulfilled, return a detailed error response.

### 3. Audit Logs
- Every attempt must be logged with all relevant metadata.

## Evaluation Criteria

- Data Design: Integrity, constraints, relational modeling
- Code Quality: Clean, modular, maintainable
- Error Handling: Graceful and informative
- Testing: Positive and negative coverage
- Performance: Concurrency-aware implementation

## Submission Instructions

1. Create a public GitHub repository
2. Push all code
3. Include a detailed README with all required sections
4. Ensure all unit tests and linting pass