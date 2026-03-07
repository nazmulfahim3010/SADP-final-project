package ui;

import model.Room;
import pattern.HotelSystem;
import service.HotelService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        setBackground(new Color(245, 247, 250));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Rooms Management");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(45, 52, 54));
        add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Room Number", "Type", "Available"}, 0) {
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
        table.setSelectionBackground(new Color(173, 216, 230)); // Solid light blue
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

        JLabel numberLabel = new JLabel("Room Number:");
        numberLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField numberField = new JTextField(10);
        numberField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JLabel typeLabel = new JLabel("Room Type:");
        typeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        String[] types = {"Single", "Double", "Suite", "Deluxe"};
        JComboBox<String> typeCombo = new JComboBox<>(types);
        typeCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        typeCombo.setBackground(Color.WHITE);

        JButton addRoomBtn = createStyledButton("Add Room", new Color(9, 132, 227));
        JButton refreshBtn = createStyledButton("Refresh", new Color(0, 184, 148));

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(numberLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        formPanel.add(numberField, gbc);

        gbc.gridx = 2; gbc.gridy = 0;
        formPanel.add(typeLabel, gbc);
        gbc.gridx = 3; gbc.gridy = 0;
        formPanel.add(typeCombo, gbc);

        gbc.gridx = 4; gbc.gridy = 0;
        formPanel.add(addRoomBtn, gbc);
        gbc.gridx = 5; gbc.gridy = 0;
        formPanel.add(refreshBtn, gbc);

        add(formPanel, BorderLayout.SOUTH);

        addRoomBtn.addActionListener(e -> {
            String numberText = numberField.getText().trim();
            String typeText = (String) typeCombo.getSelectedItem();

            if (numberText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a valid room number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int number = Integer.parseInt(numberText);
                service.addRoom(number, typeText);
                loadRooms();
                numberField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid room number.", "Format Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        refreshBtn.addActionListener(e -> loadRooms());

        loadRooms();
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
        btn.setPreferredSize(new Dimension(120, 40));
        return btn;
    }

    private void loadRooms() {
        tableModel.setRowCount(0);
        List<Room> rooms = service.getRooms();
        for (Room r : rooms) {
            tableModel.addRow(new Object[]{
                    r.getNumber(),
                    r.getType(),
                    r.isAvailable() ? "Yes" : "No"
            });
        }
    }
}
