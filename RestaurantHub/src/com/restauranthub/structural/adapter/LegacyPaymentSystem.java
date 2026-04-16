package com.restauranthub.structural.adapter;

public class LegacyPaymentSystem {
    public void processOldPayment(double amountInCents) {
        System.out.println("   [LegacySystem] 💾 Processing payment of " + amountInCents + " cents through old bank gateway.");
    }
}
