package com.hotel.payment;

public class BkashAdapter implements PaymentProcessor {
    private ExternalBkashAPI api = new ExternalBkashAPI();
    @Override public boolean processPayment(double amount) {
        return api.makeTransfer(amount);
    }
}

/**
 * DESIGN PATTERN: ADAPTER (IMPLEMENTATION)
 * 
 * WHY:
 * Concrete implementation that converts the specific interface of 
 * ExternalBkashAPI into the generic PaymentProcessor interface 
 * required by our ReservationFacade.
 */
