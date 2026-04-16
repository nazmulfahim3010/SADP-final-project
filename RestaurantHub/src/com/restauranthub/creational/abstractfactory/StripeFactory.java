package com.restauranthub.creational.abstractfactory;

public class StripeFactory extends PaymentGatewayFactory {

    @Override
    public PaymentProcessor createProcessor() {
        return new PaymentProcessor() {
            @Override
            public void processPayment(double amount) {
                System.out.println("   [Stripe] 💳 Processing card payment of $" + amount + " via Stripe...");
            }
        };
    }

    @Override
    public TransactionValidator createValidator() {
        return new TransactionValidator() {
            @Override
            public boolean validate() {
                System.out.println("   [Stripe] 🔒 Validating Stripe transaction details...");
                return true;
            }
        };
    }

    @Override
    public ReceiptGenerator createReceiptGenerator() {
        return new ReceiptGenerator() {
            @Override
            public void generateReceipt() {
                System.out.println("   [Stripe] 🧾 Generating beautiful Stripe digital receipt.");
            }
        };
    }
}
