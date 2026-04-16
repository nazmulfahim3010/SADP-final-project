package com.restauranthub.structural.proxy;

public class RealAdminOperations implements AdminOperations {

    @Override
    public void removeMenuItem(String itemName) {
        System.out.println("   [Admin] 🗑️ Menu item '" + itemName + "' has been permanently removed.");
    }

    @Override
    public void changeRestaurantHours(String newHours) {
        System.out.println("   [Admin] ⏰ Restaurant operating hours changed to: " + newHours);
    }
}
