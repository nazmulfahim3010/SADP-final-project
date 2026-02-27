package ui;

import model.Room;
import pattern.HotelSystem;
import service.HotelService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RoomPanel extends JPanel {

    private final HotelService service;
    private final DefaultTableModel tableModel;

    public RoomPanel() {
        this.service = HotelSystem.getInstance().getHotelService();

        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Rooms");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 22f));
        add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Number", "Type", "Available"}, 0) {
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

        JLabel numberLabel = new JLabel("Room Number:");
        JTextField numberField = new JTextField();
        JLabel typeLabel = new JLabel("Room Type:");
        JTextField typeField = new JTextField();
        JButton addRoomBtn = new JButton("Add Room");
        JButton refreshBtn = new JButton("Refresh");

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(numberLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(numberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(typeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(typeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(addRoomBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
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
            tableModel.addRow(new Object[]{
                    r.getNumber(),
                    r.getType(),
                    r.isAvailable() ? "Yes" : "No"
            });
        }
    }
}

