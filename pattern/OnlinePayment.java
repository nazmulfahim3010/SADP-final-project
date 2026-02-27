package pattern;

public class OnlinePayment implements PaymentStrategy {
    public double pay(double amount) {
        return amount + amount * 0.03;
    }
}