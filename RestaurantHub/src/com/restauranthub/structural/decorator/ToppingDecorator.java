package com.restauranthub.structural.decorator;

/**
 * 📌 1. Pattern Name & Category: Decorator Pattern (Structural)
 * 📌 2. Real-World Purpose: 
 * We need to dynamically add customizations (extra cheese, special packaging) 
 * to FoodItems without modifying their base classes. This prevents class explosion 
 * (e.g., PizzaWithCheese, BurgerWithCheeseAndBacon).
 * 
 * 📌 4. How It Works:
 * - Decorator implements FoodItemComponent and holds an instance of it.
 * - Subclasses (CheeseDecorator) extend this and override methods to add 
 *   their specific description and cost to the existing ones.
 */
public abstract class ToppingDecorator implements FoodItemComponent {
    protected FoodItemComponent tempFoodItem;

    public ToppingDecorator(FoodItemComponent newFoodItem) {
        tempFoodItem = newFoodItem;
    }

    public String getDescription() {
        return tempFoodItem.getDescription();
    }

    public double getCost() {
        return tempFoodItem.getCost();
    }
}
