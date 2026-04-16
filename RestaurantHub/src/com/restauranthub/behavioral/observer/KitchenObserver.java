package com.restauranthub.behavioral.observer;

public class KitchenObserver implements Observer {
    @Override
    public void update(String orderId, String status) {
        System.out.println("   [Notification] 👨‍🍳 To Kitchen Display: Order " + orderId + " - Status: " + status);
    }
}
