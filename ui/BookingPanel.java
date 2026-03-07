package ui;

import model.Booking;
import pattern.HotelSystem;
import service.HotelService;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class BookingPanel extends JPanel {

    private final HotelService service;
    private final DefaultTableModel tableModel;

    public BookingPanel() {
        this.service = HotelSystem.getInstance().getHotelService();

        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(244, 247, 246));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Booking Management");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(44, 62, 80));
        add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[] { "Room Number", "Customer Name" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(35);
        table.setShowVerticalLines(false);
        table.setSelectionBackground(new Color(41, 128, 185));
        table.setSelectionForeground(Color.WHITE);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(new Color(44, 62, 80));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(100, 35));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        TitledBorder tb = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)), "New Booking");
        tb.setTitleFont(new Font("Segoe UI", Font.BOLD, 14));
        tb.setTitleColor(new Color(44, 62, 80));
        formPanel.setBorder(BorderFactory.createCompoundBorder(tb, BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel roomLabel = new JLabel("Room Number:");
        roomLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTextField roomField = new JTextField(15);
        styleTextField(roomField);

        JLabel customerLabel = new JLabel("Customer Name:");
        customerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTextField customerField = new JTextField(15);
        styleTextField(customerField);

        JButton bookBtn = new JButton("Book Room");
        styleButton(bookBtn, new Color(41, 128, 185)); // Royal Blue

        JButton refreshBtn = new JButton("Refresh");
        styleButton(refreshBtn, new Color(44, 62, 80)); // Charcoal

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(roomLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(roomField, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        formPanel.add(customerLabel, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        formPanel.add(customerField, gbc);
        gbc.gridx = 4;
        gbc.gridy = 0;
        formPanel.add(bookBtn, gbc);
        gbc.gridx = 5;
        gbc.gridy = 0;
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
            tableModel.addRow(new Object[] {
                    b.getRoomNumber(),
                    b.getCustomerName()
            });
        }
    }

    private void styleTextField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(6, 6, 6, 6)));
    }

    private void styleButton(JButton btn, Color bgColor) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(bgColor);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
    }
}
