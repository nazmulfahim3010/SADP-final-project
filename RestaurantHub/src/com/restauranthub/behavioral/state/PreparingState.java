package com.restauranthub.behavioral.state;

public class PreparingState implements OrderState {

    @Override
    public void next(OrderContext context) {
        System.out.println("   [State] This is the final state in this demo. Order is Ready!");
    }

    @Override
    public void prev(OrderContext context) {
        context.setState(new ConfirmedState());
    }

    @Override
    public void printStatus() {
        System.out.println("   [State] Current State: " + getStatusText());
    }

    @Override
    public String getStatusText() {
        return "Preparing in Kitchen";
    }
}
