package ui;

import model.Booking;
import pattern.HotelSystem;
import service.HotelService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        setBackground(new Color(245, 247, 250));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Bookings Management");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(45, 52, 54));
        add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Room Number", "Customer Name"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        table.setRowHeight(35);
        table.setShowVerticalLines(false);
        table.setGridColor(new Color(230, 230, 230));
        table.setSelectionBackground(new Color(216, 191, 216)); // Solid light purple
        table.setSelectionForeground(Color.BLACK);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.setBackground(new Color(236, 240, 241));
        header.setForeground(new Color(45, 52, 54));
        header.setPreferredSize(new Dimension(100, 45));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(223, 228, 234)));
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(223, 228, 234)),
                new EmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel roomLabel = new JLabel("Room Number:");
        roomLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField roomField = new JTextField(10);
        roomField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel customerLabel = new JLabel("Customer Name:");
        customerLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField customerField = new JTextField(15);
        customerField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton bookBtn = createStyledButton("Confirm Booking", new Color(108, 92, 231));
        JButton refreshBtn = createStyledButton("Refresh", new Color(0, 184, 148));

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(roomLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        formPanel.add(roomField, gbc);

        gbc.gridx = 2; gbc.gridy = 0;
        formPanel.add(customerLabel, gbc);
        gbc.gridx = 3; gbc.gridy = 0;
        formPanel.add(customerField, gbc);

        gbc.gridx = 4; gbc.gridy = 0;
        formPanel.add(bookBtn, gbc);
        gbc.gridx = 5; gbc.gridy = 0;
        formPanel.add(refreshBtn, gbc);

        add(formPanel, BorderLayout.SOUTH);

        bookBtn.addActionListener(e -> {
            String roomText = roomField.getText().trim();
            String customerText = customerField.getText().trim();

            if (roomText.isEmpty() || customerText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter room number and customer name.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int roomNumber = Integer.parseInt(roomText);
                boolean result = service.bookRoom(roomNumber, customerText);
                if (result) {
                    JOptionPane.showMessageDialog(this, "Room booked successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadBookings();
                    roomField.setText("");
                    customerField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Room is not available or does not exist.", "Booking Failed", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid room number.", "Format Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        refreshBtn.addActionListener(e -> loadBookings());

        loadBookings();
    }

    private JButton createStyledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(bg);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(160, 40));
        return btn;
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
