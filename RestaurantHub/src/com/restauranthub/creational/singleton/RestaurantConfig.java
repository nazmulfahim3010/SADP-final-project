package com.restauranthub.creational.singleton;

/**
 * 📌 1. Pattern Name & Category: Singleton Pattern (Creational)
 * 📌 2. Real-World Purpose: 
 * The system needs a single source of truth for global configuration settings 
 * (like the restaurant's name, business hours, and operational status).
 * 
 * 📌 4. How It Works:
 * - Private constructor with default configurations.
 * - public static getInstance() method.
 */
public class RestaurantConfig {

    private static RestaurantConfig instance;
    
    private String restaurantName;
    private String operatingHours;
    private boolean isAcceptingOrders;

    private RestaurantConfig() {
        // Default settings
        this.restaurantName = "RestaurantHub";
        this.operatingHours = "10:00 AM - 11:00 PM";
        this.isAcceptingOrders = true;
        System.out.println("   [System] RestaurantConfig initialized. ⚙️");
    }

    public static RestaurantConfig getInstance() {
        if (instance == null) {
            instance = new RestaurantConfig();
        }
        return instance;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    public boolean isAcceptingOrders() {
        return isAcceptingOrders;
    }

    public void setAcceptingOrders(boolean acceptingOrders) {
        this.isAcceptingOrders = acceptingOrders;
    }
}
