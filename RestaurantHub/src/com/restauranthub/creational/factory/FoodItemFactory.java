package com.restauranthub.creational.factory;

/**
 * 📌 1. Pattern Name & Category: Factory Method Pattern (Creational)
 * 📌 2. Real-World Purpose: 
 * Instead of letting the client code use 'new Pizza()' or 'new Burger()', 
 * the factory centralizes the creation logic. If we add a new item later (like Pasta), 
 * we just update the factory without changing client code.
 * 
 * 📌 4. How It Works:
 * - A createFoodItem() method is provided.
 * - It takes a string to determine the type of food item to create.
 * - It returns the corresponding FoodItem implementation.
 */
public class FoodItemFactory {
    
    public static FoodItem createFoodItem(String type) {
        if (type == null) {
            return null;
        }
        
        switch (type.toLowerCase()) {
            case "pizza":
                System.out.println("   [Factory] 🏭 Creating a new Pizza...");
                return new Pizza();
            case "burger":
                System.out.println("   [Factory] 🏭 Creating a new Burger...");
                return new Burger();
            case "beverage":
                System.out.println("   [Factory] 🏭 Creating a new Beverage...");
                return new Beverage();
            default:
                throw new IllegalArgumentException("Unknown food item type: " + type);
        }
    }
}
