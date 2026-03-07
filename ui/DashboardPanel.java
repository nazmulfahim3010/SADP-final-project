package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import pattern.Observer;
import pattern.HotelSystem;

public class DashboardPanel extends JPanel implements Observer {

    private final JLabel totalRoomsLabel;
    private final JLabel availableRoomsLabel;
    private final JLabel totalBookingsLabel;
    private final service.HotelService service;

    public DashboardPanel() {
        service = HotelSystem.getInstance().getHotelService();
        service.addObserver(this);

        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 247, 250)); // Light vibrant background
        setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel title = new JLabel("Dashboard Overview");
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(new Color(45, 52, 54));
        add(title, BorderLayout.NORTH);

        JPanel cardsPanel = new JPanel(new GridLayout(1, 3, 25, 0));
        cardsPanel.setBackground(new Color(245, 247, 250));

        totalRoomsLabel = createValueLabel();
        availableRoomsLabel = createValueLabel();
        totalBookingsLabel = createValueLabel();

        cardsPanel.add(createCard("Total Rooms", totalRoomsLabel, new Color(9, 132, 227))); // Blue
        cardsPanel.add(createCard("Available Rooms", availableRoomsLabel, new Color(0, 184, 148))); // Green
        cardsPanel.add(createCard("Total Bookings", totalBookingsLabel, new Color(108, 92, 231))); // Purple

        // Wrap cards panel to restrict its height, putting it at the top
        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.add(cardsPanel, BorderLayout.NORTH);

        add(centerWrapper, BorderLayout.CENTER);
        update();
    }

    private JPanel createCard(String title, JLabel valueLabel, Color color) {
        JPanel card = new JPanel(new BorderLayout(15, 15)) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Rounded corners
                g2.dispose();
                super.paintComponent(g);
            }
        };
        card.setOpaque(false);
        card.setBorder(new EmptyBorder(25, 20, 25, 20));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(color);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel.setForeground(new Color(45, 52, 54));

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        // Add a subtle bottom border effect mapping to the card's theme color
        JPanel borderPanel = new JPanel();
        borderPanel.setBackground(color);
        borderPanel.setPreferredSize(new Dimension(0, 5));
        card.add(borderPanel, BorderLayout.SOUTH);

        return card;
    }

    private JLabel createValueLabel() {
        JLabel label = new JLabel("0");
        label.setFont(new Font("Segoe UI", Font.BOLD, 48));
        return label;
    }

    @Override
    public void update() {
        totalRoomsLabel.setText(String.valueOf(service.totalRooms()));
        availableRoomsLabel.setText(String.valueOf(service.availableRooms()));
        totalBookingsLabel.setText(String.valueOf(service.getBookings().size()));
    }
}