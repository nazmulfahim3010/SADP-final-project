package pattern;

import model.Room;

import java.io.*;
import java.util.*;

/**
 * Singleton Pattern implementation.
 * perfectly handles File I/O operations centrally to avoid concurrent file lock
 * issues.
 */
public class FileManager {

    // volatile keyword ensures that multiple threads handle the instance correctly
    private static volatile FileManager instance;
    private static final String ROOM_FILE = "rooms.csv";

    // Private constructor preventing instantiation from other classes
    private FileManager() {
    }

    // Double-checked locking for perfect thread-safe Singleton
    public static FileManager getInstance() {
        if (instance == null) {
            synchronized (FileManager.class) {
                if (instance == null) {
                    instance = new FileManager();
                }
            }
        }
        return instance;
    }

    // Synchronized file writing to prevent concurrent overwrite
    public synchronized void saveRooms(List<Room> rooms) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ROOM_FILE))) {
            for (Room r : rooms) {
                pw.println(r.getNumber() + "," + r.getType() + "," + r.isAvailable());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Synchronized file reading
    public synchronized List<Room> loadRooms() {
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
