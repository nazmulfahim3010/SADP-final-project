package com.hotel.service;

import com.hotel.model.*;
import com.hotel.observer.DataObserver;
import com.hotel.memento.DataStoreMemento;
import java.io.*;
import java.util.*;

public class DataStore implements Serializable {
    private static final long serialVersionUID = 1L;
    private static DataStore instance; // Singleton

    private List<Room> rooms = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Reservation> pendingReservations = new ArrayList<>();
    private List<Reservation> confirmedReservations = new ArrayList<>();
    private transient List<DataObserver> observers = new ArrayList<>();
    private transient Stack<DataStoreMemento> undoStack = new Stack<>(); // Memento history

    private DataStore() {}

    public static DataStore getInstance() {
        if (instance == null) { instance = new DataStore(); }
        return instance;
    }

    public void addObserver(DataObserver o) { 
        if (observers == null) observers = new ArrayList<>(); 
        observers.add(o); 
    }
    
    public void notifyObservers() {
        if (observers != null) { for (DataObserver o : observers) o.onDataChanged(); }
    }

    public void saveCustomerState() { undoStack.push(new DataStoreMemento(this.customers)); }
    public void undoCustomerChange() {
        if (!undoStack.isEmpty()) { this.customers = undoStack.pop().getSavedCustomers(); notifyObservers(); autoSave(); }
    }

    public List<Room> getRooms() { return rooms; }
    public List<Customer> getCustomers() { return customers; }
    public List<Reservation> getPendingReservations() { return pendingReservations; }
    public List<Reservation> getConfirmedReservations() { return confirmedReservations; }

    public void addRoom(Room r) { rooms.add(r); notifyObservers(); autoSave(); }
    public void addCustomer(Customer c) { saveCustomerState(); customers.add(c); notifyObservers(); autoSave(); }
    public void removeCustomer(Customer c) { saveCustomerState(); customers.remove(c); notifyObservers(); autoSave(); }
    
    public void addPendingReservation(Reservation r) { pendingReservations.add(r); notifyObservers(); autoSave(); }
    public void addConfirmedReservation(Reservation r) { confirmedReservations.add(r); notifyObservers(); autoSave(); }

    private void autoSave() {
        try { saveAll("hoteldata.dat"); } catch (Exception ignored) {}
    }

    public void saveAll(String file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) { oos.writeObject(this); }
    }

    public static void loadAll(String file) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            DataStore loaded = (DataStore) ois.readObject();
            if (loaded != null) {
                instance.rooms = loaded.rooms; 
                instance.customers = loaded.customers;
                instance.pendingReservations = loaded.pendingReservations; 
                instance.confirmedReservations = loaded.confirmedReservations;
            }
        } catch (Exception ignored) {}
    }
}

/**
 * DESIGN PATTERN: SINGLETON & OBSERVER (SUBJECT)
 * 
 * WHY:
 * 1. SINGLETON: Ensures that there is only one instance of the database (DataStore) 
 *    throughout the application's lifecycle, providing a global point of access.
 * 2. OBSERVER (SUBJECT): DataStore maintains a list of observers (GUI panels). 
 *    When data changes, it notifies all observers automatically, ensuring the 
 *    UI stays in sync with the model without tight coupling.
 */
