package com.restauranthub.models;

import java.util.Arrays;
import java.util.List;

public class FoodMenu {
    public List<String> getAvailableItems() {
        return Arrays.asList("Pizza", "Burger", "Beverage");
    }

    public double getPriceForItem(String itemName) {
        if (itemName == null) {
            return 0.0;
        }

        switch (itemName.toLowerCase()) {
            case "pizza":
                return 12.99;
            case "burger":
                return 8.99;
            case "beverage":
                return 2.99;
            default:
                return 0.0;
        }
    }

    public String buildMenuText() {
        StringBuilder builder = new StringBuilder();
        builder.append("Today's Specials:\n");
        for (String item : getAvailableItems()) {
            builder.append("- ")
                    .append(item)
                    .append(" ($")
                    .append(String.format("%.2f", getPriceForItem(item)))
                    .append(")\n");
        }
        return builder.toString().trim();
    }

    public void displayMenu() {
        System.out.println("   [Menu] " + buildMenuText().replace("\n", "\n   "));
    }
}
