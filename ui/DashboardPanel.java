package ui;

import javax.swing.*;
import java.awt.*;
import pattern.Observer;
import pattern.HotelSystem;
import service.HotelService;

public class DashboardPanel extends JPanel implements Observer {

    private JLabel totalRoomsLabel;
    private JLabel availableRoomsLabel;
    private HotelService service;

    public DashboardPanel() {
        service = HotelSystem.getInstance().getHotelService();
        service.addObserver(this);

        setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
        setBackground(new Color(244, 247, 246));

        JPanel totalCard = createCard("Total Rooms");
        totalRoomsLabel = new JLabel("0", SwingConstants.CENTER);
        totalRoomsLabel.setFont(new Font("Segoe UI", Font.BOLD, 48));
        totalRoomsLabel.setForeground(new Color(41, 128, 185)); // Royal Blue
        totalCard.add(totalRoomsLabel, BorderLayout.CENTER);

        JPanel availableCard = createCard("Available Rooms");
        availableRoomsLabel = new JLabel("0", SwingConstants.CENTER);
        availableRoomsLabel.setFont(new Font("Segoe UI", Font.BOLD, 48));
        availableRoomsLabel.setForeground(new Color(41, 128, 185)); // Royal Blue
        availableCard.add(availableRoomsLabel, BorderLayout.CENTER);

        add(totalCard);
        add(availableCard);
        update();
    }

    private JPanel createCard(String titleText) {
        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(250, 150));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        JLabel title = new JLabel(titleText, SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        title.setForeground(Color.GRAY);
        card.add(title, BorderLayout.SOUTH);

        return card;
    }

    @Override
    public void update() {
        totalRoomsLabel.setText(String.valueOf(service.totalRooms()));
        availableRoomsLabel.setText(String.valueOf(service.availableRooms()));
    }
}