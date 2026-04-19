package com.hotel.strategy;

import java.io.Serializable;

public interface PricingStrategy extends Serializable {
    double calculatePrice(double basePrice, long nights);
}

/**
 * DESIGN PATTERN: STRATEGY (INTERFACE)
 * 
 * WHY:
 * Defines a family of algorithms for price calculation and makes them 
 * interchangeable. This allows adding new pricing rules (seasonal, 
 * holiday, corporate) without modifying the Reservation's core logic.
 */
