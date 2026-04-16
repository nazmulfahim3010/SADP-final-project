package com.restauranthub.behavioral.state;

public class PendingState implements OrderState {

    @Override
    public void next(OrderContext context) {
        context.setState(new ConfirmedState());
    }

    @Override
    public void prev(OrderContext context) {
        System.out.println("   [State] The order is in its root state (Pending). Cannot go back.");
    }

    @Override
    public void printStatus() {
        System.out.println("   [State] Current State: " + getStatusText());
    }

    @Override
    public String getStatusText() {
        return "Pending Payment";
    }
}
