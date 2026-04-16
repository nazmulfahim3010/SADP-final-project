package com.restauranthub.behavioral.strategy;

public class RegularPricing implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice) {
        // No discount
        System.out.println("   [Strategy] 📊 Applying Regular Pricing (No discount).");
        return basePrice;
    }
}
