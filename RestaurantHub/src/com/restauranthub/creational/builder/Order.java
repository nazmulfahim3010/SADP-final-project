package com.restauranthub.creational.builder;

import com.restauranthub.creational.factory.FoodItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private List<FoodItem> items;
    private String orderType; // Dine-in, Takeaway, Delivery
    private String specialInstructions;

    public Order(List<FoodItem> items, String orderType, String specialInstructions) {
        this.items = items;
        this.orderType = orderType;
        this.specialInstructions = specialInstructions;
    }

    public void showOrderDetails() {
        System.out.println("\n   " + buildOrderSummary().replace("\n", "\n   "));
    }

    public List<FoodItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public String getOrderType() {
        return orderType;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public String buildOrderSummary() {
        StringBuilder builder = new StringBuilder();
        builder.append("[Order Details]\n");
        builder.append("Type: ").append(orderType).append("\n");
        builder.append("Instructions: ")
                .append(specialInstructions == null || specialInstructions.isEmpty() ? "None" : specialInstructions)
                .append("\n");
        builder.append("Items:\n");
        for (FoodItem item : items) {
            builder.append("- ")
                    .append(item.getName())
                    .append(" ($")
                    .append(String.format("%.2f", item.getPrice()))
                    .append(")\n");
        }
        builder.append("Total: $").append(String.format("%.2f", getTotalPrice()));
        return builder.toString();
    }

    public double getTotalPrice() {
        double total = 0;
        for(FoodItem item : items) {
            total += item.getPrice();
        }
        return total;
    }
}
