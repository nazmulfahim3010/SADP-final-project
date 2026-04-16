package com.restauranthub.behavioral.strategy;

/**
 * 📌 1. Pattern Name & Category: Strategy Pattern (Behavioral)
 * 📌 2. Real-World Purpose: 
 * The restaurant has different pricing logic depending on the time of day 
 * (Regular, Happy Hour, Holiday). Instead of using complex if-else statements, 
 * we encapsulate the pricing algorithms into interchangeable Strategy objects.
 * 
 * 📌 4. How It Works:
 * - PricingContext holds a reference to a PricingStrategy.
 * - The strategy can be changed dynamically at runtime using setStrategy().
 * - When calculateFinalPrice() is called, it delegates to the active strategy.
 */
public class PricingContext {
    private PricingStrategy strategy;

    public PricingContext(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculateFinalPrice(double basePrice) {
        return strategy.calculatePrice(basePrice);
    }
}
