package com.hotel.strategy;

public class StandardPricing implements PricingStrategy {
    private static final long serialVersionUID = 1L;
    @Override public double calculatePrice(double basePrice, long nights) { return basePrice * nights; }
}

/**
 * DESIGN PATTERN: STRATEGY (CONCRETE ALGORITHM)
 * 
 * WHY:
 * Implements the default pricing logic where total = base price * nights.
 */
