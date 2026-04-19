package com.hotel.gui;

import com.hotel.factory.UIFactory;
import com.hotel.model.Customer;
import com.hotel.model.Room;
import com.hotel.model.Reservation;
import com.hotel.observer.DataObserver;
import com.hotel.service.DataStore;
import com.hotel.service.ReservationFacade;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

public class ReservationPanel extends JPanel implements DataObserver {
    private JComboBox<Customer> cbCustomer = new JComboBox<>();
    private JComboBox<Room> cbRoom = new JComboBox<>();
    private DefaultTableModel model = new DefaultTableModel(new Object[]{"Res ID", "Customer", "Room", "Status"}, 0);
    private JTable table = new JTable(model);
    private ReservationFacade facade = new ReservationFacade();

    public ReservationPanel() {
        setLayout(new BorderLayout());
        setOpaque(false);
        DataStore.getInstance().addObserver(this);

        JPanel form = new JPanel(new GridLayout(2, 2, 15, 15));
        form.setOpaque(false);
        form.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        addLabelledField(form, "Select Customer:", cbCustomer);
        addLabelledField(form, "Select Room:", cbRoom);

        UIFactory.styleTable(table);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel btns = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        btns.setOpaque(false);
        btns.add(UIFactory.createModernButton("Book (bKash)", new Color(236, 64, 122), () -> {
            boolean success = facade.bookRoom((Customer)cbCustomer.getSelectedItem(), (Room)cbRoom.getSelectedItem(), LocalDate.now(), LocalDate.now().plusDays(1));
            if(success) JOptionPane.showMessageDialog(this, "Paid & Booked!");
        }));
        btns.add(UIFactory.createModernButton("Confirm Check-In", new Color(34, 167, 240), () -> {
            int r = table.getSelectedRow();
            if(r != -1) facade.confirmBooking(model.getValueAt(r, 0).toString());
        }));

        add(form, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(btns, BorderLayout.SOUTH);
        onDataChanged();
    }

    private void addLabelledField(JPanel p, String label, JComboBox c) {
        JLabel l = new JLabel(label);
        l.setForeground(UIFactory.TEXT_SECONDARY);
        l.setFont(new Font("Segoe UI", Font.BOLD, 14));
        p.add(l);
        p.add(c);
        c.setBackground(UIFactory.BG_DARK);
        c.setForeground(UIFactory.TEXT_PRIMARY);
    }

    @Override public void onDataChanged() {
        cbCustomer.removeAllItems(); cbRoom.removeAllItems(); model.setRowCount(0);
        for(Customer c : DataStore.getInstance().getCustomers()) cbCustomer.addItem(c);
        for(Room r : DataStore.getInstance().getRooms()) if(r.getStatus() == Room.RoomStatus.AVAILABLE) cbRoom.addItem(r);
        for(Reservation res : DataStore.getInstance().getPendingReservations()) 
            model.addRow(new Object[]{res.getReservationId(), res.getCustomer().getName(), res.getRoom().getRoomId(), "PENDING"});
        for(Reservation res : DataStore.getInstance().getConfirmedReservations()) 
            model.addRow(new Object[]{res.getReservationId(), res.getCustomer().getName(), res.getRoom().getRoomId(), "CONFIRMED"});
    }
}

/**
 * DESIGN PATTERN: OBSERVER (CONCRETE OBSERVER)
 * 
 * WHY:
 * Observes changes to customers and rooms to keep its selection dropdowns 
 * updated, and observes reservation updates to refresh the bookings table.
 */
