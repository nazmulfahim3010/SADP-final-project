package pattern;

public class CardPayment implements PaymentStrategy {
    public double pay(double amount) {
        return amount + amount * 0.02;
    }
}