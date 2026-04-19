package com.hotel.model;

public enum RoomType {
    SINGLE, DOUBLE, SUITE;
    @Override public String toString() {
        switch (this) {
            case SINGLE: return "Single Room";
            case DOUBLE: return "Double Room";
            case SUITE:  return "VIP Room";
            default: return super.toString();
        }
    }
}
