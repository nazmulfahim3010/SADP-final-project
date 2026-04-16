package com.restauranthub.behavioral.observer;

public class CustomerObserver implements Observer {
    private String customerName;

    public CustomerObserver(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public void update(String orderId, String status) {
        System.out.println("   [Notification] 📱 To " + customerName + ": Your order " + orderId + " is now " + status);
    }
}
