package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import pattern.*;
import service.HotelService;

public class MainFrame extends JFrame {

    public MainFrame() {
        HotelService service = HotelSystem.getInstance().getHotelService();

        // Use cross-platform Look & Feel so custom button backgrounds are respected on Windows
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        setTitle("Hotel Management System");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 247, 250));

        // Sidebar Panel
        JPanel sidebarWrapper = new JPanel(new BorderLayout());
        sidebarWrapper.setBackground(new Color(30, 39, 46));
        sidebarWrapper.setPreferredSize(new Dimension(250, getHeight()));

        // Sidebar Header
        JPanel sidebarHeader = new JPanel(new BorderLayout());
        sidebarHeader.setBackground(new Color(23, 32, 38));
        sidebarHeader.setBorder(new EmptyBorder(30, 20, 30, 20));
        
        JLabel brandName = new JLabel("<html><div style='text-align:center;'>Hotel<br>Manager</div></html>");
        brandName.setFont(new Font("Segoe UI", Font.BOLD, 28));
        brandName.setForeground(new Color(236, 240, 241));
        brandName.setHorizontalAlignment(SwingConstants.CENTER);
        sidebarHeader.add(brandName, BorderLayout.CENTER);
        
        sidebarWrapper.add(sidebarHeader, BorderLayout.NORTH);

        // Sidebar Navigation
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new GridLayout(0, 1, 0, 10));
        navPanel.setBackground(new Color(30, 39, 46));
        navPanel.setBorder(new EmptyBorder(20, 15, 20, 15));

        JButton dashboardBtn = createNavButton("Dashboard", true);
        JButton roomsBtn = createNavButton("Room Management", false);
        JButton bookingBtn = createNavButton("Bookings", false);
        JButton paymentBtn = createNavButton("Payment", false);

        navPanel.add(dashboardBtn);
        navPanel.add(roomsBtn);
        navPanel.add(bookingBtn);
        navPanel.add(paymentBtn);

        sidebarWrapper.add(navPanel, BorderLayout.CENTER);
        add(sidebarWrapper, BorderLayout.WEST);

        // Center content area with card layout
        JPanel contentPanel = new JPanel(new CardLayout());
        contentPanel.setBackground(new Color(245, 247, 250));
        
        JPanel paymentPanel = createPaymentPanel();

        contentPanel.add(new DashboardPanel(), "DASHBOARD");
        contentPanel.add(new RoomPanel(), "ROOMS");
        contentPanel.add(new BookingPanel(), "BOOKINGS");
        contentPanel.add(paymentPanel, "PAYMENT");

        add(contentPanel, BorderLayout.CENTER);

        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();

        JButton[] navButtons = {dashboardBtn, roomsBtn, bookingBtn, paymentBtn};

        dashboardBtn.addActionListener(e -> {
            cardLayout.show(contentPanel, "DASHBOARD");
            setActiveButton(dashboardBtn, navButtons);
        });
        roomsBtn.addActionListener(e -> {
            cardLayout.show(contentPanel, "ROOMS");
            setActiveButton(roomsBtn, navButtons);
        });
        bookingBtn.addActionListener(e -> {
            cardLayout.show(contentPanel, "BOOKINGS");
            setActiveButton(bookingBtn, navButtons);
        });
        paymentBtn.addActionListener(e -> {
            cardLayout.show(contentPanel, "PAYMENT");
            setActiveButton(paymentBtn, navButtons);
        });

        setVisible(true);
    }

    private void setActiveButton(JButton activeBtn, JButton[] allButtons) {
        for (JButton btn : allButtons) {
            if (btn == activeBtn) {
                btn.setBackground(new Color(9, 132, 227)); // Active blue color
                btn.setForeground(Color.WHITE);
            } else {
                btn.setBackground(new Color(30, 39, 46)); // Default dark
                btn.setForeground(new Color(189, 195, 199)); // Grey text
            }
        }
    }

    private JButton createNavButton(String text, boolean isActive) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setMargin(new Insets(10, 20, 10, 20));

        if (isActive) {
            button.setBackground(new Color(9, 132, 227));
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(new Color(30, 39, 46));
            button.setForeground(new Color(189, 195, 199));
        }

        // Hover Effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (button.getBackground().equals(new Color(30, 39, 46))) {
                    button.setBackground(new Color(45, 52, 54));
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button.getBackground().equals(new Color(45, 52, 54))) {
                    button.setBackground(new Color(30, 39, 46));
                }
            }
        });

        return button;
    }

    private JPanel createPaymentPanel() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(245, 247, 250));
        wrapper.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel headerTitle = new JLabel("Process Payment");
        headerTitle.setFont(new Font("Segoe UI", Font.BOLD, 32));
        headerTitle.setForeground(new Color(45, 52, 54));
        headerTitle.setBorder(new EmptyBorder(0, 0, 30, 0));
        wrapper.add(headerTitle, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(223, 228, 234)),
                new EmptyBorder(40, 40, 40, 40)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel methodLabel = new JLabel("Payment Method:");
        methodLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(methodLabel, gbc);

        String[] options = { "Cash", "Card (2% Fee)", "Online (3% Fee)" };
        JComboBox<String> methodCombo = new JComboBox<>(options);
        methodCombo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        methodCombo.setBackground(Color.WHITE);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(methodCombo, gbc);

        JLabel amountLabel = new JLabel("Base Amount ($):");
        amountLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(amountLabel, gbc);

        JTextField amountField = new JTextField("1000", 15);
        amountField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(amountField, gbc);

        JButton payBtn = new JButton("Calculate & Pay");
        payBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        payBtn.setForeground(Color.WHITE);
        payBtn.setBackground(new Color(9, 132, 227));
        payBtn.setFocusPainted(false);
        payBtn.setBorderPainted(false);
        payBtn.setOpaque(true);
        payBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        payBtn.setPreferredSize(new Dimension(200, 45));
        
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(payBtn, gbc);

        JLabel resultLabel = new JLabel("Total Amount to Pay: $0.00");
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        resultLabel.setForeground(new Color(0, 184, 148));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(resultLabel, gbc);

        payBtn.addActionListener(e -> {
            String choice = (String) methodCombo.getSelectedItem();
            String amountText = amountField.getText().trim();

            if (choice == null || amountText.isEmpty()) {
                JOptionPane.showMessageDialog(wrapper, "Please select a method and enter amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double baseAmount;
            try {
                baseAmount = Double.parseDouble(amountText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(wrapper, "Invalid amount.", "Format Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            PaymentStrategy strategy = null;

            if (choice.startsWith("Cash")) {
                strategy = new CashPayment();
            } else if (choice.startsWith("Card")) {
                strategy = new CardPayment();
            } else if (choice.startsWith("Online")) {
                strategy = new OnlinePayment();
            }

            if (strategy == null) {
                JOptionPane.showMessageDialog(wrapper, "Invalid payment method.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double finalAmount = strategy.pay(baseAmount);
            resultLabel.setText(String.format("Total Amount to Pay: $%.2f", finalAmount));
        });

        // Add the white panel to a wrapper that pushes it to the top
        JPanel alignmentWrapper = new JPanel(new BorderLayout());
        alignmentWrapper.setBackground(new Color(245, 247, 250));
        alignmentWrapper.add(panel, BorderLayout.NORTH);
        
        wrapper.add(alignmentWrapper, BorderLayout.CENTER);

        return wrapper;
    }
}