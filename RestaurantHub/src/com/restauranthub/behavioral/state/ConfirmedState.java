package com.restauranthub.behavioral.state;

public class ConfirmedState implements OrderState {

    @Override
    public void next(OrderContext context) {
        context.setState(new PreparingState());
    }

    @Override
    public void prev(OrderContext context) {
        context.setState(new PendingState());
    }

    @Override
    public void printStatus() {
        System.out.println("   [State] Current State: " + getStatusText());
    }

    @Override
    public String getStatusText() {
        return "Confirmed and Paid";
    }
}
