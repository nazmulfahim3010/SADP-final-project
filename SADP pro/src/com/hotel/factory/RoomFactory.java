package com.hotel.factory;

import com.hotel.model.Room;
import com.hotel.model.RoomType;

public class RoomFactory {
    public static Room createRoom(String roomId, RoomType type, double pricePerNight) {
        return new Room(roomId, type, pricePerNight);
    }
}

/**
 * DESIGN PATTERN: FACTORY METHOD
 * 
 * WHY:
 * Provides an interface for creating room objects but allows the 
 * factory to decide which specific room model to instantiate. 
 * This decouples the Room Panel from the internal details of Room creation.
 */
