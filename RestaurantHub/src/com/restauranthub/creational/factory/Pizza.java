package com.restauranthub.creational.factory;

public class Pizza implements FoodItem {
    @Override
    public void prepare() {
        System.out.println("   [Kitchen] 🍕 Kneading dough, adding sauce and cheese, baking pizza...");
    }

    @Override
    public String getName() {
        return "Pizza";
    }

    @Override
    public double getPrice() {
        return 12.99;
    }
}
