package com.hotel.strategy;

public class OffSeasonDiscount implements PricingStrategy {
    private static final long serialVersionUID = 1L;
    @Override public double calculatePrice(double basePrice, long nights) { return (basePrice * nights) * 0.85; } // 15% discount
}

/**
 * DESIGN PATTERN: STRATEGY (CONCRETE ALGORITHM)
 * 
 * WHY:
 * Implements a specific pricing logic for off-season discounts (15% off).
 */
