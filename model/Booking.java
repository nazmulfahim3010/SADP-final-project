package model;

public class Booking {
    private int roomNumber;
    private String customerName;

    public Booking(int roomNumber, String customerName) {
        this.roomNumber = roomNumber;
        this.customerName = customerName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCustomerName() {
        return customerName;
    }
}