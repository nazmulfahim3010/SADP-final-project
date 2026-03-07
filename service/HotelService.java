package service;

import model.Room;
import model.Booking;
import pattern.*;

import java.util.*;

public class HotelService {

    private List<Room> rooms;
    private List<Booking> bookings = new ArrayList<>();
    private FileService fileService = new FileService();
    private BookingNotifier notifier = new BookingNotifier();

    public HotelService() {
        rooms = fileService.loadRooms();
        bookings = fileService.loadBookings();
    }

    public void addObserver(pattern.Observer o) {
        notifier.addObserver(o);
    }

    public void addRoom(int number, String type) {
        Room room = RoomFactory.createRoom(number, type);
        rooms.add(room);
        fileService.saveRooms(rooms);
    }

    public boolean bookRoom(int number, String customer) {
        for (Room r : rooms) {
            if (r.getNumber() == number && r.isAvailable()) {
                r.setAvailable(false);
                bookings.add(new Booking(number, customer));
                fileService.saveRooms(rooms);
                fileService.saveBookings(bookings);
                notifier.notifyObservers();
                return true;
            }
        }
        return false;
    }

    public int totalRooms() {
        return rooms.size();
    }

    public int availableRooms() {
        int count = 0;
        for (Room r : rooms) {
            if (r.isAvailable())
                count++;
        }
        return count;
    }

    public List<Room> getRooms() {
        return Collections.unmodifiableList(rooms);
    }

    public List<Booking> getBookings() {
        return Collections.unmodifiableList(bookings);
    }
}