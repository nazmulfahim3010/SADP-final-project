package ui;

import javax.swing.*;
import java.awt.*;
import pattern.*;
import service.HotelService;

public class MainFrame extends JFrame {

    @SuppressWarnings("unused")
    private HotelService service;

    public MainFrame() {

        service = HotelSystem.getInstance().getHotelService();

        // Use a nicer Look & Feel where available
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        setTitle("Hotel Management System");
        setSize(950, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(244, 247, 246)); // Off-White

        Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);
        UIManager.put("Label.font", mainFont);
        UIManager.put("Button.font", mainFont);
        UIManager.put("TextField.font", mainFont);

        // Top application header
        JLabel headerTitle = new JLabel("Hotel Management System");
        headerTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        headerTitle.setForeground(Color.WHITE);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(44, 62, 80)); // Deep Charcoal
        header.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        header.add(headerTitle, BorderLayout.WEST);

        add(header, BorderLayout.NORTH);

        // Left navigation with a modern feel
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new GridLayout(6, 1, 0, 5));
        navPanel.setPreferredSize(new Dimension(200, 0));
        navPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        navPanel.setBackground(new Color(44, 62, 80)); // Deep Charcoal

        JButton dashboardBtn = createNavButton("\u2302 Dashboard");
        JButton roomsBtn = createNavButton("\uD83D\uDECF Rooms");
        JButton bookingBtn = createNavButton("\uD83D\uDCDD Bookings");
        JButton paymentBtn = createNavButton("\uD83D\uDCB3 Payment");

        navPanel.add(dashboardBtn);
        navPanel.add(roomsBtn);
        navPanel.add(bookingBtn);
        navPanel.add(paymentBtn);

        add(navPanel, BorderLayout.WEST);

        // Center content area with card layout
        JPanel contentPanel = new JPanel(new CardLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(new Color(244, 247, 246));

        JPanel dashboardContainer = new JPanel(new BorderLayout());
        dashboardContainer.setBackground(new Color(244, 247, 246));
        dashboardContainer.add(new DashboardPanel(), BorderLayout.CENTER);

        JPanel paymentPanel = createPaymentPanel();

        contentPanel.add(dashboardContainer, "DASHBOARD");
        contentPanel.add(new RoomPanel(), "ROOMS");
        contentPanel.add(new BookingPanel(), "BOOKINGS");
        contentPanel.add(paymentPanel, "PAYMENT");

        add(contentPanel, BorderLayout.CENTER);

        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();

        dashboardBtn.addActionListener(e -> cardLayout.show(contentPanel, "DASHBOARD"));
        roomsBtn.addActionListener(e -> cardLayout.show(contentPanel, "ROOMS"));
        bookingBtn.addActionListener(e -> cardLayout.show(contentPanel, "BOOKINGS"));
        paymentBtn.addActionListener(e -> cardLayout.show(contentPanel, "PAYMENT"));

        setVisible(true);
    }

    private JButton createNavButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(44, 62, 80));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(52, 73, 94)); // Hover Color
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(44, 62, 80)); // Default Color
            }
        });

        return button;
    }

    private JPanel createPaymentPanel() {
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(244, 247, 246));

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Make Payment");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        gbc.gridwidth = 1;

        JLabel methodLabel = new JLabel("Payment Method:");
        methodLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(methodLabel, gbc);

        String[] options = { "Cash", "Card", "Online" };
        JComboBox<String> methodCombo = new JComboBox<>(options);
        methodCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        methodCombo.setBackground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(methodCombo, gbc);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(amountLabel, gbc);

        JTextField amountField = new JTextField("1000");
        amountField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        amountField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(6, 6, 6, 6)));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(amountField, gbc);

        JButton payBtn = new JButton("Pay Securely");
        payBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        payBtn.setForeground(Color.WHITE);
        payBtn.setBackground(new Color(39, 174, 96)); // Emerald Green
        payBtn.setFocusPainted(false);
        payBtn.setBorderPainted(false);
        payBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        payBtn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        panel.add(payBtn, gbc);

        JLabel resultLabel = new JLabel(" ");
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        resultLabel.setForeground(new Color(41, 128, 185));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(resultLabel, gbc);

        payBtn.addActionListener(e -> {
            String choice = (String) methodCombo.getSelectedItem();
            String amountText = amountField.getText().trim();

            if (choice == null || amountText.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please select a method and enter amount.");
                return;
            }

            double baseAmount;
            try {
                baseAmount = Double.parseDouble(amountText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Invalid amount.");
                return;
            }

            PaymentStrategy strategy = null;

            if ("Cash".equals(choice)) {
                strategy = new CashPayment();
            } else if ("Card".equals(choice)) {
                strategy = new CardPayment();
            } else if ("Online".equals(choice)) {
                strategy = new OnlinePayment();
            }

            if (strategy == null) {
                JOptionPane.showMessageDialog(panel, "Invalid payment method.");
                return;
            }

            double finalAmount = strategy.pay(baseAmount);
            resultLabel.setText("Final Amount to Pay: " + finalAmount);
        });

        wrapper.add(panel);
        return wrapper;
    }
}