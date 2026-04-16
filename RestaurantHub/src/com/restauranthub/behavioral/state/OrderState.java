package com.restauranthub.behavioral.state;

public interface OrderState {
    void next(OrderContext context);
    void prev(OrderContext context);
    void printStatus();
    String getStatusText();
}
