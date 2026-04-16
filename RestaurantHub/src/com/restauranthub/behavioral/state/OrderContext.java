package com.restauranthub.behavioral.state;

/**
 * 📌 1. Pattern Name & Category: State Pattern (Behavioral)
 * 📌 2. Real-World Purpose: 
 * An order goes through various lifecycle states (Pending -> Confirmed -> Preparing). 
 * Depending on the state, the allowed actions modify. State Pattern avoids huge 
 * switch-case blocks by making the states themselves objects that handle the transitions.
 * 
 * 📌 4. How It Works:
 * - OrderContext (the order itself) holds a reference to an OrderState interface.
 * - Calling next() or prev() on context delegates the call to the state object, 
 *   which updates the context's state reference to the new state.
 */
public class OrderContext {
    private OrderState state;

    public OrderContext() {
        // Default initial state
        state = new PendingState();
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void nextState() {
        state.next(this);
    }

    public void previousState() {
        state.prev(this);
    }

    public void printCurrentStatus() {
        state.printStatus();
    }

    public String getCurrentStatusText() {
        return state.getStatusText();
    }
}
