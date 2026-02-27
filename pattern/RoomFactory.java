package pattern;

//factory
import model.Room;

public class RoomFactory {

    public static Room createRoom(int number, String type) {
        return new Room(number, type, true);
    }
}