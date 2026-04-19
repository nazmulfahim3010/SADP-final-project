package com.medicarehub.models;

import java.io.Serializable;

/**
 * Represents a Bill in the MediCare Hub system.
 */
public class Bill implements Serializable {
    private String id;
    private String patientId;
    private double amount;
    private boolean isPaid;

    public Bill(String id, String patientId, double amount) {
        this.id = id;
        this.patientId = patientId;
        this.amount = amount;
        this.isPaid = false;
    }

    public String getId() { return id; }
    public String getPatientId() { return patientId; }
    public double getAmount() { return amount; }
    public boolean isPaid() { return isPaid; }

    public void setPaid(boolean paid) { isPaid = paid; }
}

/*
 * ======================================================
 * WHY THIS PATTERN / CLASS?
 * ======================================================
 * Pattern  : Business Model
 * Problem  : Need to track financial transactions for patient care.
 * Usage    : Used in the billing module to handle payments.
 * Without  : Financial data would be untracked or inconsistent, 
 *            risking data loss and accounting errors.
 * ======================================================
 */
