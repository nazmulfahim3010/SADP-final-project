package ui;

import javax.swing.*;
import java.awt.*;
import pattern.*;
import service.HotelService;

public class MainFrame extends JFrame {

    private HotelService service;

    public MainFrame() {

        service = HotelSystem.getInstance().getHotelService();

        // Use a nicer Look & Feel where available
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    SwingUtilities.updateComponentTreeUI(this);
                    break;
                }
            }
        } catch (Exception ignored) {
        }

        setTitle("Hotel Management System");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Top application header
        JLabel headerTitle = new JLabel("Hotel Management System");
        headerTitle.setFont(headerTitle.getFont().deriveFont(Font.BOLD, 24f));
        headerTitle.setForeground(Color.WHITE);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(45, 45, 45));
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        header.add(headerTitle, BorderLayout.WEST);

        add(header, BorderLayout.NORTH);

        // Left navigation with a modern feel
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new GridLayout(0, 1, 5, 5));
        navPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        navPanel.setBackground(new Color(30, 30, 30));

        JButton dashboardBtn = createNavButton("Dashboard");
        JButton roomsBtn = createNavButton("Rooms");
        JButton bookingBtn = createNavButton("Bookings");
        JButton paymentBtn = createNavButton("Payment");

        navPanel.add(dashboardBtn);
        navPanel.add(roomsBtn);
        navPanel.add(bookingBtn);
        navPanel.add(paymentBtn);

        add(navPanel, BorderLayout.WEST);

        // Center content area with card layout
        JPanel contentPanel = new JPanel(new CardLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel dashboardContainer = new JPanel(new BorderLayout());
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
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(60, 63, 65));
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        return button;
    }

    private JPanel createPaymentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Make Payment");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        gbc.gridwidth = 1;

        JLabel methodLabel = new JLabel("Payment Method:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(methodLabel, gbc);

        String[] options = { "Cash", "Card", "Online" };
        JComboBox<String> methodCombo = new JComboBox<>(options);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(methodCombo, gbc);

        JLabel amountLabel = new JLabel("Amount:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(amountLabel, gbc);

        JTextField amountField = new JTextField("1000");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(amountField, gbc);

        JButton payBtn = new JButton("Pay");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(payBtn, gbc);

        JLabel resultLabel = new JLabel(" ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
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

        return panel;
    }
}