package com.hotel.memento;

import com.hotel.model.Customer;
import java.util.*;

public class DataStoreMemento {
    private final List<Customer> backupCustomers;
    public DataStoreMemento(List<Customer> customers) { this.backupCustomers = new ArrayList<>(customers); }
    public List<Customer> getSavedCustomers() { return backupCustomers; }
}

/**
 * DESIGN PATTERN: MEMENTO
 * 
 * WHY:
 * Captures and externalizes an object's internal state so that it can 
 * be restored later without violating encapsulation. Here, it is 
 * used to implement the "Undo" feature for customer management.
 */
