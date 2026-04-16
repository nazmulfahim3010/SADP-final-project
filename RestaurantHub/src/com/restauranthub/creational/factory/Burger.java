package com.restauranthub.creational.factory;

public class Burger implements FoodItem {
    @Override
    public void prepare() {
        System.out.println("   [Kitchen] 🍔 Grilling patty, toasting buns, adding lettuce...");
    }

    @Override
    public String getName() {
        return "Burger";
    }

    @Override
    public double getPrice() {
        return 8.99;
    }
}
