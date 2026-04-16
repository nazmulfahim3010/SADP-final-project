package com.restauranthub.behavioral.strategy;

public class HappyHourPricing implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice) {
        // 20% discount
        System.out.println("   [Strategy] 🎉 Applying Happy Hour Pricing (20% Off!).");
        return basePrice * 0.80;
    }
}
