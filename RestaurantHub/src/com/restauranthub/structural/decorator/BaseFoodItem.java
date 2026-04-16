package com.restauranthub.structural.decorator;

import com.restauranthub.creational.factory.FoodItem;

public class BaseFoodItem implements FoodItemComponent {
    
    private FoodItem wrappedItem;

    public BaseFoodItem(FoodItem wrappedItem) {
        this.wrappedItem = wrappedItem;
    }

    @Override
    public String getDescription() {
        return wrappedItem.getName();
    }

    @Override
    public double getCost() {
        return wrappedItem.getPrice();
    }
}
