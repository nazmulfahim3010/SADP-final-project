package service;

import model.Booking;
import model.Room;

import java.io.*;
import java.util.*;

public class FileService {

    private static final String ROOM_FILE = "rooms.csv";
    private static final String BOOKING_FILE = "bookings.csv";

    public void saveRooms(List<Room> rooms) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ROOM_FILE))) {
            for (Room r : rooms) {
                pw.println(r.getNumber() + "," + r.getType() + "," + r.isAvailable());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Room> loadRooms() {
        List<Room> rooms = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ROOM_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    rooms.add(new Room(
                            Integer.parseInt(data[0]),
                            data[1],
                            Boolean.parseBoolean(data[2])));
                }
            }
        } catch (IOException e) {
            System.out.println("No existing room file found.");
        }
        return rooms;
    }

    public void saveBookings(List<Booking> bookings) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(BOOKING_FILE))) {
            for (Booking b : bookings) {
                pw.println(b.getRoomNumber() + "," + b.getCustomerName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Booking> loadBookings() {
        List<Booking> bookings = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(BOOKING_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2) {
                    bookings.add(new Booking(
                            Integer.parseInt(data[0]),
                            data[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("No existing booking file found.");
        }
        return bookings;
    }
}