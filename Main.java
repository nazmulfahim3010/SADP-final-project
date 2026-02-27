import ui.MainFrame;
import pattern.HotelSystem;

public class Main {
    public static void main(String[] args) {
        HotelSystem.getInstance(); // Singleton initialized
        new MainFrame();
    }
}

/*
 * HotelManagementSystem/
 * │
 * ├── Main.java
 * │
 * ├── model/
 * │ ├── Room.java
 * │ ├── Customer.java
 * │ └── Booking.java
 * │
 * ├── service/
 * │ ├── HotelService.java
 * │ └── FileService.java
 * │
 * ├── pattern/
 * │ ├── HotelSystem.java (Singleton)
 * │ ├── RoomFactory.java (Factory)
 * │ ├── PaymentStrategy.java (Strategy Interface)
 * │ ├── CashPayment.java
 * │ ├── CardPayment.java
 * │ ├── OnlinePayment.java
 * │ ├── Observer.java
 * │ └── BookingNotifier.java (Observer Subject)
 * │
 * ├── ui/
 * │ ├── DashboardPanel.java
 * │ ├── RoomPanel.java
 * │ ├── BookingPanel.java
 * │ └── MainFrame.java
 * │
 * └── data/
 * ├── rooms.csv
 * ├── customers.csv
 * └── bookings.csv
 */