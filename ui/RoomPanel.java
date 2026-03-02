package ui;

import model.Room;
import pattern.HotelSystem;
import service.HotelService;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class RoomPanel extends JPanel {

    private final HotelService service;
    private final DefaultTableModel tableModel;

    public RoomPanel() {
        this.service = HotelSystem.getInstance().getHotelService();

        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(244, 247, 246));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Room Management");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(44, 62, 80));
        add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[] { "Number", "Type", "Available" }, 0) {
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
                BorderFactory.createLineBorder(new Color(220, 220, 220)), "Add New Room");
        tb.setTitleFont(new Font("Segoe UI", Font.BOLD, 14));
        tb.setTitleColor(new Color(44, 62, 80));
        formPanel.setBorder(BorderFactory.createCompoundBorder(tb, BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel numberLabel = new JLabel("Room Number:");
        numberLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTextField numberField = new JTextField(15);
        styleTextField(numberField);

        JLabel typeLabel = new JLabel("Room Type:");
        typeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTextField typeField = new JTextField(15);
        styleTextField(typeField);

        JButton addRoomBtn = new JButton("Add Room");
        styleButton(addRoomBtn, new Color(41, 128, 185)); // Royal Blue

        JButton refreshBtn = new JButton("Refresh");
        styleButton(refreshBtn, new Color(44, 62, 80)); // Charcoal

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(numberLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(numberField, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        formPanel.add(typeLabel, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        formPanel.add(typeField, gbc);
        gbc.gridx = 4;
        gbc.gridy = 0;
        formPanel.add(addRoomBtn, gbc);
        gbc.gridx = 5;
        gbc.gridy = 0;
        formPanel.add(refreshBtn, gbc);

        add(formPanel, BorderLayout.SOUTH);

        addRoomBtn.addActionListener(e -> {
            String numberText = numberField.getText().trim();
            String typeText = typeField.getText().trim();

            if (numberText.isEmpty() || typeText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter room number and type.");
                return;
            }

            try {
                int number = Integer.parseInt(numberText);
                service.addRoom(number, typeText);
                loadRooms();
                numberField.setText("");
                typeField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid room number.");
            }
        });

        refreshBtn.addActionListener(e -> loadRooms());

        loadRooms();
    }

    private void loadRooms() {
        tableModel.setRowCount(0);
        List<Room> rooms = service.getRooms();
        for (Room r : rooms) {
            tableModel.addRow(new Object[] {
                    r.getNumber(),
                    r.getType(),
                    r.isAvailable() ? "Yes" : "No"
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
