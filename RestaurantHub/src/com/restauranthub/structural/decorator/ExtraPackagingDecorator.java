package com.restauranthub.structural.decorator;

public class ExtraPackagingDecorator extends ToppingDecorator {

    public ExtraPackagingDecorator(FoodItemComponent newFoodItem) {
        super(newFoodItem);
    }

    @Override
    public String getDescription() {
        return tempFoodItem.getDescription() + " + Premium Packaging 🎁";
    }

    @Override
    public double getCost() {
        return tempFoodItem.getCost() + 2.00;
    }
}
