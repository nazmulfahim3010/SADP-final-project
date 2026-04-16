package com.restauranthub.creational.abstractfactory;

public class PayPalFactory extends PaymentGatewayFactory {

    @Override
    public PaymentProcessor createProcessor() {
        return new PaymentProcessor() {
            @Override
            public void processPayment(double amount) {
                System.out.println("   [PayPal] 🌐 Processing online wallet payment of $" + amount + " via PayPal...");
            }
        };
    }

    @Override
    public TransactionValidator createValidator() {
        return new TransactionValidator() {
            @Override
            public boolean validate() {
                System.out.println("   [PayPal] 🔒 Validating PayPal session token...");
                return true;
            }
        };
    }

    @Override
    public ReceiptGenerator createReceiptGenerator() {
        return new ReceiptGenerator() {
            @Override
            public void generateReceipt() {
                System.out.println("   [PayPal] 🧾 Generating standard PayPal email receipt.");
            }
        };
    }
}
