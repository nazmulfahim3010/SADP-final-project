package com.restauranthub.behavioral.command;

import java.util.Stack;

/**
 * 📌 1. Pattern Name & Category: Command Pattern (Behavioral)
 * 📌 2. Real-World Purpose: 
 * We need to be able to queue, execute, and undo order actions. 
 * By encapsulating "PlaceOrder" or "CancelOrder" as command objects, 
 * the Invoker can simply execute them and keep a history to allow 'undoing' 
 * a mistakenly placed order.
 * 
 * 📌 4. How It Works:
 * - OrderInvoker keeps a history Stack of executed OrderCommands.
 * - executeCommand() calls the command's execute() method and pushes it to history.
 * - undoLastCommand() pops the top command off the history and calls undo().
 */
public class OrderInvoker {
    private Stack<OrderCommand> commandHistory;

    public OrderInvoker() {
        commandHistory = new Stack<>();
    }

    public void executeCommand(OrderCommand command) {
        command.execute();
        commandHistory.push(command);
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            OrderCommand lastCommand = commandHistory.pop();
            lastCommand.undo();
        } else {
            System.out.println("   [Invoker] ❌ No commands to undo.");
        }
    }
}
