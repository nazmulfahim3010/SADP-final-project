package com.restauranthub.structural.decorator;

public class CheeseDecorator extends ToppingDecorator {

    public CheeseDecorator(FoodItemComponent newFoodItem) {
        super(newFoodItem);
    }

    @Override
    public String getDescription() {
        return tempFoodItem.getDescription() + " + Extra Cheese 🧀";
    }

    @Override
    public double getCost() {
        return tempFoodItem.getCost() + 1.50;
    }
}
