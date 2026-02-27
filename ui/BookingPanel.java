package ui;

import model.Booking;
import pattern.HotelSystem;
import service.HotelService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BookingPanel extends JPanel {

    private final HotelService service;
    private final DefaultTableModel tableModel;

    public BookingPanel() {
        this.service = HotelSystem.getInstance().getHotelService();

        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Bookings");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 22f));
        add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Room Number", "Customer Name"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel roomLabel = new JLabel("Room Number:");
        JTextField roomField = new JTextField();
        JLabel customerLabel = new JLabel("Customer Name:");
        JTextField customerField = new JTextField();
        JButton bookBtn = new JButton("Book Room");
        JButton refreshBtn = new JButton("Refresh");

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(roomLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(roomField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(customerLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(customerField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(bookBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(refreshBtn, gbc);

        add(formPanel, BorderLayout.SOUTH);

        bookBtn.addActionListener(e -> {
            String roomText = roomField.getText().trim();
            String customerText = customerField.getText().trim();

            if (roomText.isEmpty() || customerText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter room number and customer name.");
                return;
            }

            try {
                int roomNumber = Integer.parseInt(roomText);
                boolean result = service.bookRoom(roomNumber, customerText);
                if (result) {
                    JOptionPane.showMessageDialog(this, "Room booked successfully.");
                    loadBookings();
                } else {
                    JOptionPane.showMessageDialog(this, "Room is not available or does not exist.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid room number.");
            }
        });

        refreshBtn.addActionListener(e -> loadBookings());

        loadBookings();
    }

    private void loadBookings() {
        tableModel.setRowCount(0);
        List<Booking> bookings = service.getBookings();
        for (Booking b : bookings) {
            tableModel.addRow(new Object[]{
                    b.getRoomNumber(),
                    b.getCustomerName()
            });
        }
    }
}

