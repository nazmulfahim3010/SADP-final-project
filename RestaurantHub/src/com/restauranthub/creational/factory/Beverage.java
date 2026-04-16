package com.restauranthub.creational.factory;

public class Beverage implements FoodItem {
    @Override
    public void prepare() {
        System.out.println("   [Kitchen] 🥤 Pouring beverage, adding ice cubes...");
    }

    @Override
    public String getName() {
        return "Beverage";
    }

    @Override
    public double getPrice() {
        return 2.99;
    }
}
