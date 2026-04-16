package com.restauranthub.creational.builder;

import com.restauranthub.creational.factory.FoodItem;
import java.util.ArrayList;
import java.util.List;

/**
 * 📌 1. Pattern Name & Category: Builder Pattern (Creational)
 * 📌 2. Real-World Purpose: 
 * Constructing a complex order step-by-step. Instead of a huge constructor 
 * with all possible parameters (items, type, instructions, etc.), the Builder 
 * allows us to add items and configurations gradually before calling build().
 * 
 * 📌 4. How It Works:
 * - OrderBuilder holds the state during construction.
 * - Methods like addItem(), setOrderType() modify the builder and return 'this' for chaining.
 * - The build() method finally creates the immutable Order object.
 */
public class OrderBuilder {
    private List<FoodItem> items = new ArrayList<>();
    private String orderType = "Dine-in";
    private String specialInstructions = "";

    public OrderBuilder addItem(FoodItem item) {
        if(item != null) {
            this.items.add(item);
            System.out.println("   [Builder] ➕ Added " + item.getName() + " to order.");
        }
        return this;
    }

    public OrderBuilder setOrderType(String type) {
        this.orderType = type;
        return this;
    }

    public OrderBuilder setSpecialInstructions(String instructions) {
        this.specialInstructions = instructions;
        return this;
    }

    public Order build() {
        System.out.println("   [Builder] 🏗️ Building final order...");
        return new Order(items, orderType, specialInstructions);
    }
}
