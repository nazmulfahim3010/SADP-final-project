package com.restauranthub.creational.abstractfactory;

/**
 * 📌 1. Pattern Name & Category: Abstract Factory Pattern (Creational)
 * 📌 2. Real-World Purpose: 
 * We need to create families of related objects. When using Stripe, we need a 
 * StripeProcessor, StripeValidator, and StripeReceiptGenerator. The Abstract 
 * Factory ensures that these objects are created together and are compatible.
 * 
 * 📌 4. How It Works:
 * - This abstract class defines methods to create a family of products:
 *   PaymentProcessor, TransactionValidator, and ReceiptGenerator.
 * - Concrete factories (StripeFactory, PayPalFactory) implement these methods.
 */
public abstract class PaymentGatewayFactory {
    public abstract PaymentProcessor createProcessor();
    public abstract TransactionValidator createValidator();
    public abstract ReceiptGenerator createReceiptGenerator();
}
