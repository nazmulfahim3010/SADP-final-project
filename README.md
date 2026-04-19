# MediCare Hub — Hospital Patient Management System

MediCare Hub is a production-quality Hospital Patient Management System built with **Java Swing**. The project focuses on clean software architecture and demonstrates the practical application of **10 Software Architecture and Design Patterns (SADP)**.

## Project Vision
To provide a consolidated, professional, and "Clean Stream" experience for hospital staff, replacing manual record-keeping with a modern, pattern-driven digital solution.

## Architecture & Design Patterns
The system is built using a modular architecture where every core feature is mapped to a specific design pattern to ensure scalability and maintainability.

### 1. Creational Patterns
- **Singleton**: `HospitalDatabase` (Global data access) and `AppConfig`.
- **Factory**: `StaffFactory` (Dynamic creation of medical staff).
- **Composite**: `MedicalRecord` (Tree structure for complex patient data).

### 2. Structural Patterns
- **Adapter**: `ReportAdapter` (Translating data for legacy CSV/PDF systems).
- **Facade**: `HospitalFacade` (Simplifying complex multi-step admission processes).

### 3. Behavioral Patterns
- **Iterator**: `PatientCollection` (Standardized transversal over patient records).
- **Observer**: `AppointmentSubject` (Triggering logs/notifications on status changes).
- **Strategy**: `BillingContext` (Interchangeable billing algorithms).
- **Command**: `PaymentCommand` (Encapsulating payment requests).
- **Memento**: `OperationHistory` (Snapshotting state for Undo functionality).

---

## Current Status: Phase 1 (Foundation) Complete ✅

### Implemented Components:
- **Project Hierarchy**: Structured directories for `models`, `gui`, `creational`, `structural`, and `behavioral`.
- **Foundation Models**:
    - `Patient`: Core DTO for patient information.
    - `Doctor`: Base model for medical professionals.
    - `Appointment`: State-managed booking entity.
    - `Bill`: Ledger entity for financial tracking.
- **UI Design System**: 
    - `UITheme`: Centralized constants for modern HSL/Blue aesthetics, fonts, and borders.

### "Why This Pattern?" Philosophy
Every implementation file in this project includes a mandatory trailing comment block explaining the architectural rationale behind the chosen pattern, ensuring deep understanding of *why* as well as *how*.

---

## How to Run
To compile and run the project from the root directory:

**1. Compile:**
```bash
javac -d bin src/com/medicarehub/**/*.java
```

**2. Run:**
```bash
java -cp bin com.medicarehub.main.MediCareApp
```

## Current Roadmap
- **Phase 2**: Creational Patterns (Next)
- **Phase 3**: Structural Patterns
- **Phase 4**: Behavioral Patterns
- **Phase 5**: GUI Desktop Experience
- **Phase 6**: Final Integration & Sample Data
