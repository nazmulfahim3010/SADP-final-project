package pattern;

public class CashPayment implements PaymentStrategy {
    public double pay(double amount) {
        return amount;
    }
}