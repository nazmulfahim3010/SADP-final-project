package com.restauranthub.structural.facade;

public class PaymentService {
    public boolean chargeCustomer(double amount) {
        System.out.println("   [Facade-Payment] 💵 Charging customer $" + String.format("%.2f", amount));
        return true; // assume success
    }
}
