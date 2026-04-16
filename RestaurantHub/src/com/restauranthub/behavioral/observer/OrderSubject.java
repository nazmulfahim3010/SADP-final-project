package com.restauranthub.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 📌 1. Pattern Name & Category: Observer Pattern (Behavioral)
 * 📌 2. Real-World Purpose: 
 * When an order's status changes (e.g., from "Preparing" to "Ready"), multiple 
 * parties need to know: the customer (via app notification) and the kitchen 
 * (to update their display screen). The Observer pattern manages this subscription 
 * centrally.
 * 
 * 📌 4. How It Works:
 * - OrderSubject maintains a list of Observers (Customer, Kitchen).
 * - When setStatus() is called, it updates its internal state and calls notifyObservers().
 * - Each registered observer receives the update() method call.
 */
public class OrderSubject implements Subject {

    private List<Observer> observers;
    private String orderId;
    private String status;

    public OrderSubject(String orderId) {
        this.observers = new ArrayList<>();
        this.orderId = orderId;
        this.status = "Pending";
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(orderId, status);
        }
    }

    public void setStatus(String status) {
        this.status = status;
        System.out.println("\n   [System] 🔄 Order Status changed to: " + status);
        notifyObservers();
    }

    public String getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }
}
