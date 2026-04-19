package com.hotel.gui;

import com.hotel.factory.UIFactory;
import com.hotel.factory.RoomFactory;
import com.hotel.model.Room;
import com.hotel.model.RoomType;
import com.hotel.observer.DataObserver;
import com.hotel.service.DataStore;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RoomPanel extends JPanel implements DataObserver {
    private JTextField txtId = UIFactory.createStyledTextField();
    private JTextField txtPrice = UIFactory.createStyledTextField();
    private JComboBox<RoomType> cbType = new JComboBox<>(RoomType.values());
    private DefaultTableModel model = new DefaultTableModel(new Object[]{"Room ID", "Type", "Price", "Status"}, 0);
    private JTable table = new JTable(model);

    public RoomPanel() {
        setLayout(new BorderLayout());
        setOpaque(false);
        DataStore.getInstance().addObserver(this);

        JPanel form = new JPanel(new GridLayout(3, 2, 15, 15));
        form.setOpaque(false);
        form.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        addLabelledField(form, "Room ID:", txtId);
        addLabelledField(form, "Room Type:", cbType);
        addLabelledField(form, "Price per Night:", txtPrice);

        UIFactory.styleTable(table);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel btns = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        btns.setOpaque(false);
        btns.add(UIFactory.createModernButton("Register Room", UIFactory.ACCENT_GOLD, () -> {
            try {
                Room r = RoomFactory.createRoom(txtId.getText(), (RoomType) cbType.getSelectedItem(), Double.parseDouble(txtPrice.getText()));
                DataStore.getInstance().addRoom(r);
                txtId.setText(""); txtPrice.setText("");
            } catch (Exception e) { JOptionPane.showMessageDialog(this, "Invalid Data"); }
        }));

        add(form, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(btns, BorderLayout.SOUTH);
        onDataChanged();
    }

    private void addLabelledField(JPanel p, String label, JComponent c) {
        JLabel l = new JLabel(label);
        l.setForeground(UIFactory.TEXT_SECONDARY);
        l.setFont(new Font("Segoe UI", Font.BOLD, 14));
        p.add(l);
        p.add(c);
        if (c instanceof JComboBox) {
            c.setBackground(UIFactory.BG_DARK);
            c.setForeground(UIFactory.TEXT_PRIMARY);
        }
    }

    @Override public void onDataChanged() {
        model.setRowCount(0);
        for(Room r : DataStore.getInstance().getRooms()) 
            model.addRow(new Object[]{r.getRoomId(), r.getType(), "$" + r.getPricePerNight(), r.getStatus()});
    }
}

/**
 * DESIGN PATTERN: OBSERVER (CONCRETE OBSERVER)
 * 
 * WHY:
 * Observes the DataStore for any updates to the room inventory. 
 * This ensures the JTable is always synced when rooms are added or 
 * when their status changes (e.g., from AVAILABLE to BOOKED).
 */
