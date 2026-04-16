package com.restauranthub.controller;

import com.restauranthub.creational.factory.FoodItem;
import com.restauranthub.structural.decorator.FoodItemComponent;

public class DecoratedFoodItemAdapter implements FoodItem {
    private final FoodItemComponent component;

    public DecoratedFoodItemAdapter(FoodItemComponent component) {
        this.component = component;
    }

    @Override
    public void prepare() {
        // Decoration changes price/description only; base preparation is handled earlier.
    }

    @Override
    public String getName() {
        return component.getDescription();
    }

    @Override
    public double getPrice() {
        return component.getCost();
    }
}
