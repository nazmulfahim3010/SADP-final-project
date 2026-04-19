package com.medicarehub.models;

import java.io.Serializable;

/**
 * Represents a Patient in the MediCare Hub system.
 */
public class Patient implements Serializable {
    private String id;
    private String name;
    private int age;
    private String gender;
    private String contact;
    private String medicalHistory;

    public Patient(String id, String name, int age, String gender, String contact, String medicalHistory) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
        this.medicalHistory = medicalHistory;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getContact() { return contact; }
    public String getMedicalHistory() { return medicalHistory; }

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
}

/*
 * ======================================================
 * WHY THIS PATTERN / CLASS?
 * ======================================================
 * Pattern  : Data Transfer Object (DTO)
 * Problem  : Need a structured way to store and pass patient information.
 * Usage    : Used throughout the app to represent patient entities.
 * Without  : Patient data would be passed as loose strings/integers, 
 *            leading to unorganized and error-prone code.
 * ======================================================
 */
