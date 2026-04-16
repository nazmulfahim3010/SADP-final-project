package com.restauranthub.creational.singleton;

/**
 * 📌 1. Pattern Name & Category: Singleton Pattern (Creational)
 * 📌 2. Real-World Purpose: 
 * In RestaurantHub, we need exactly ONE database connection manager to prevent 
 * resource wastage and connection conflicts. The Singleton pattern ensures that 
 * all parts of the application use the same database connection instance.
 * 
 * 📌 4. How It Works:
 * - A private static instance of DatabaseConnection is held inside the class.
 * - The constructor is made private so no other class can instantiate it using 'new'.
 * - A public static method getInstance() returns the single instance, creating it 
 *   if it doesn't exist yet.
 */
public class DatabaseConnection {

    // The single instance of the class
    private static DatabaseConnection instance;
    private boolean isConnected;

    // Private constructor prevents instantiation from other classes
    private DatabaseConnection() {
        System.out.println("   [System] DatabaseConnection instance created. 🔌");
        this.isConnected = false;
    }

    // Public method to provide access to the instance
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void connect() {
        if (!isConnected) {
            isConnected = true;
            System.out.println("   [Database] Connected to the main restaurant database. 🟢");
        } else {
            System.out.println("   [Database] Already connected! ⚡");
        }
    }

    public void disconnect() {
        if (isConnected) {
            isConnected = false;
            System.out.println("   [Database] Disconnected from the database. 🔴");
        }
    }
}
