package com.restauranthub.behavioral.command;

public interface OrderCommand {
    void execute();
    void undo();
}
