package com.restauranthub.behavioral.command;

import com.restauranthub.creational.builder.Order;

public class CancelOrderCommand implements OrderCommand {
    private Order order;

    public CancelOrderCommand(Order order) {
        this.order = order;
    }

    @Override
    public void execute() {
        System.out.println("   [Command] 🗑️ Executing CancelOrder action.");
        // Logic to stop processing would go here
    }

    @Override
    public void undo() {
        System.out.println("   [Command] ↩️ Undoing CancelOrder action. Order reinstated.");
    }
}
