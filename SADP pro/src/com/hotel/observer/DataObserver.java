package com.hotel.observer;

public interface DataObserver {
    void onDataChanged();
}

/**
 * DESIGN PATTERN: OBSERVER (INTERFACE)
 * 
 * WHY:
 * Provides a common contract for any component that needs to react to 
 * changes in the central DataStore. This allows the UI (Panels) to 
 * remain independent of the specific business logic implementations.
 */
