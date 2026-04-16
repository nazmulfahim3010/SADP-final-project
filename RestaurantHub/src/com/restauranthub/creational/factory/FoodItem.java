package com.restauranthub.creational.factory;

/**
 * 📌 1. Pattern Name & Category: Factory Method Pattern (Creational)
 * 📌 2. Real-World Purpose: 
 * We need a standard way to represent all food items, whether it's a Pizza, 
 * Burger, or Beverage. This interface defines the operations that all food 
 * items must support.
 * 
 * 📌 4. How It Works:
 * - This provides the common interface `FoodItem`.
 * - Concrete classes like Pizza, Burger implement it.
 */
public interface FoodItem {
    void prepare();
    String getName();
    double getPrice();
}
