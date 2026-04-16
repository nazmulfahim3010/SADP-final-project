package com.restauranthub.structural.facade;

/**
 * 📌 1. Pattern Name & Category: Facade Pattern (Structural)
 * 📌 2. Real-World Purpose: 
 * Completing an order requires coordinating payments, updating inventory, and 
 * notifying the kitchen. The Facade provides a single, simple method 'processOrder()' 
 * so the client doesn't need to manually orchestrate all these complex subsystems.
 * 
 * 📌 4. How It Works:
 * - OrderProcessingFacade encapsulates PaymentService, InventoryService, and KitchenNotificationService.
 * - Calling processOrder() on the Facade executes methods on all these services in the correct order.
 */
public class OrderProcessingFacade {
    private PaymentService paymentService;
    private InventoryService inventoryService;
    private KitchenNotificationService kitchenService;

    public OrderProcessingFacade() {
        this.paymentService = new PaymentService();
        this.inventoryService = new InventoryService();
        this.kitchenService = new KitchenNotificationService();
    }

    public void processOrder(double totalAmount) {
        System.out.println("\n   [Facade] 🎭 Starting complete order processing flow...");
        boolean paymentSuccess = paymentService.chargeCustomer(totalAmount);
        
        if (paymentSuccess) {
            inventoryService.deductIngredients();
            kitchenService.notifyKitchen();
            System.out.println("   [Facade] ✅ Order completely processed successfully.");
        } else {
            System.out.println("   [Facade] ❌ Order processing failed due to payment issue.");
        }
    }
}
