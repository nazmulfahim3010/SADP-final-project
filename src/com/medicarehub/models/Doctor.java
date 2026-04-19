package com.medicarehub.models;

import java.io.Serializable;

/**
 * Represents a Doctor in the MediCare Hub system.
 */
public class Doctor implements Serializable {
    private String id;
    private String name;
    private String specialization;

    public Doctor(String id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }

    @Override
    public String toString() {
        return name + " (" + specialization + ")";
    }
}

/*
 * ======================================================
 * WHY THIS PATTERN / CLASS?
 * ======================================================
 * Pattern  : Entity Model
 * Problem  : Need to represent medical staff in the system.
 * Usage    : Used in appointment booking and doctor management.
 * Without  : Managing doctor data would require hardcoded logic or loose 
 *            variables, making the system difficult to scale.
 * ======================================================
 */
