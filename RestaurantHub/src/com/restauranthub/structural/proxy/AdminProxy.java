package com.restauranthub.structural.proxy;

/**
 * 📌 1. Pattern Name & Category: Proxy Pattern (Structural)
 * 📌 2. Real-World Purpose: 
 * Certain operations like removing food items or changing hours should only 
 * be done by an authenticated admin. The Proxy controls access to the 
 * RealAdminOperations object by checking credentials before allowing the action.
 * 
 * 📌 4. How It Works:
 * - AdminProxy implements the AdminOperations interface.
 * - It requires a role/password to execute methods.
 * - If authorized, it passes the call to RealAdminOperations; otherwise, it denies access.
 */
public class AdminProxy implements AdminOperations {

    private RealAdminOperations realAdminOperations;
    private String userRole;

    public AdminProxy(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public void removeMenuItem(String itemName) {
        if ("ADMIN".equalsIgnoreCase(userRole)) {
            lazyInitialize();
            System.out.println("   [Proxy] 🔓 Access Granted.");
            realAdminOperations.removeMenuItem(itemName);
        } else {
            System.out.println("   [Proxy] 🚫 Access Denied: User role '" + userRole + "' cannot remove items.");
        }
    }

    @Override
    public void changeRestaurantHours(String newHours) {
        if ("ADMIN".equalsIgnoreCase(userRole)) {
            lazyInitialize();
            System.out.println("   [Proxy] 🔓 Access Granted.");
            realAdminOperations.changeRestaurantHours(newHours);
        } else {
            System.out.println("   [Proxy] 🚫 Access Denied: User role '" + userRole + "' cannot change hours.");
        }
    }

    private void lazyInitialize() {
        if (realAdminOperations == null) {
            realAdminOperations = new RealAdminOperations();
        }
    }
}
