package com.restauranthub.behavioral.command;

import com.restauranthub.creational.builder.Order;

public class PlaceOrderCommand implements OrderCommand {
    private Order order;

    public PlaceOrderCommand(Order order) {
        this.order = order;
    }

    @Override
    public void execute() {
        System.out.println("   [Command] 📝 Executing PlaceOrder action.");
        // Logic to send order to processing would go here
    }

    @Override
    public void undo() {
        System.out.println("   [Command] ↩️ Undoing PlaceOrder action. Order cancelled.");
    }
}
