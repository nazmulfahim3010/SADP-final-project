package service;

import model.Room;

import java.io.*;
import java.util.*;

public class FileService {

    private static final String ROOM_FILE = "rooms.csv";

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
                rooms.add(new Room(
                        Integer.parseInt(data[0]),
                        data[1],
                        Boolean.parseBoolean(data[2])));
            }
        } catch (IOException e) {
            System.out.println("No existing room file found.");
        }
        return rooms;
    }
}