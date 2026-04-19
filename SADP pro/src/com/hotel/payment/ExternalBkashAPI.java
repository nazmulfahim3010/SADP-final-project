package com.hotel.payment;

public class ExternalBkashAPI {
    public boolean makeTransfer(double bdtAmount) {
        System.out.println("Processing BDT " + bdtAmount + " via bKash...");
        return true; // Assume success
    }
}

/**
 * DESIGN PATTERN: ADAPTER (ADAPTEE)
 * 
 * WHY:
 * This represents a third-party or legacy API that has an incompatible 
 * interface with our system. We wrap this class using the BkashAdapter 
 * to make it usable within our PaymentProcessor structure.
 */
