package com.hotel.factory;

import com.hotel.model.Room;
import com.hotel.model.RoomType;

public class RoomFactory {
    public static Room createRoom(String roomId, RoomType type, double pricePerNight) {
        return new Room(roomId, type, pricePerNight);
    }
}
