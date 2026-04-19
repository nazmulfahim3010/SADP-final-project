package com.hotel.model;

import java.io.Serializable;

public interface BookingComponent extends Serializable {
    double getTotalPrice();
    void displayBookingDetails();
}

/**
 * DESIGN PATTERN: COMPOSITE (COMPONENT INTERFACE)
 * 
 * WHY:
 * Defines a shared interface for both individual bookings (Leaf) and 
 * composite groups of bookings. This allows the system to treat 
 * simple and complex booking structures uniformly.
 */
