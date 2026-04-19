package com.hotel.payment;

public interface PaymentProcessor {
    boolean processPayment(double amount);
}

/**
 * DESIGN PATTERN: ADAPTER (TARGET INTERFACE)
 * 
 * WHY:
 * Defines the standardized interface that the rest of the application uses 
 * for payment processing. This allows the system to support any payment 
 * gateway (bKash, Nagad, Stripe) by simply creating a new adapter.
 */
