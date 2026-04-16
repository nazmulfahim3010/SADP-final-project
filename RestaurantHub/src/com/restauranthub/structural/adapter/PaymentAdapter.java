package com.restauranthub.structural.adapter;

/**
 * 📌 1. Pattern Name & Category: Adapter Pattern (Structural)
 * 📌 2. Real-World Purpose: 
 * We have a LegacyPaymentSystem that only accepts amounts in cents and has a completely 
 * different method signature. The PaymentAdapter acts as a bridge, implementing our 
 * ModernPaymentInterface and converting the calls so the legacy system understands them.
 * 
 * 📌 4. How It Works:
 * - Adapter implements ModernPaymentInterface.
 * - It contains an instance of LegacyPaymentSystem.
 * - Inside its pay() method, it converts dollars to cents and redirects the call.
 */
public class PaymentAdapter implements ModernPaymentInterface {
    
    private LegacyPaymentSystem legacyPayment;

    public PaymentAdapter(LegacyPaymentSystem legacyPayment) {
        this.legacyPayment = legacyPayment;
    }

    @Override
    public void pay(String currency, double amount) {
        System.out.println("   [Adapter] 🔄 Adapting modern " + currency + " payment to legacy system...");
        if(currency.equalsIgnoreCase("USD")) {
            // Convert dollars to cents
            double amountInCents = amount * 100;
            legacyPayment.processOldPayment(amountInCents);
        } else {
            System.out.println("   [Adapter] ❌ Unsupported currency for legacy system.");
        }
    }
}
