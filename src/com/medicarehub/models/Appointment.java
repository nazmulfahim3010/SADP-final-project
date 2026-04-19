package com.medicarehub.models;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents an Appointment in the MediCare Hub system.
 */
public class Appointment implements Serializable {
    private String id;
    private String patientId;
    private String doctorId;
    private LocalDateTime dateTime;
    private String status;

    public Appointment(String id, String patientId, String doctorId, LocalDateTime dateTime) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.dateTime = dateTime;
        this.status = "Scheduled";
    }

    public String getId() { return id; }
    public String getPatientId() { return patientId; }
    public String getDoctorId() { return doctorId; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}

/*
 * ======================================================
 * WHY THIS PATTERN / CLASS?
 * ======================================================
 * Pattern  : Transactional Model
 * Problem  : Need to track the relationship between patients and doctors over time.
 * Usage    : Core entity for the appointment scheduling sub-system.
 * Without  : Relationship tracking would be static or decoupled, 
 *            making it impossible to manage schedules effectively.
 * ======================================================
 */
