package com.restauranthub.models;

public class Receipt {
    public String buildReceiptText(String customerName, double amount) {
        StringBuilder builder = new StringBuilder();
        builder.append("OFFICIAL RECEIPT\n");
        builder.append("Customer: ").append(customerName).append("\n");
        builder.append("Total Paid: $").append(String.format("%.2f", amount)).append("\n");
        builder.append("Thank you for dining at RestaurantHub!");
        return builder.toString();
    }

    public void printReceipt(String customerName, double amount) {
        System.out.println("\n   ========================================");
        System.out.println("   " + buildReceiptText(customerName, amount).replace("\n", "\n   "));
        System.out.println("   ========================================\n");
    }
}
