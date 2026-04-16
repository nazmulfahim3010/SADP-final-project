package com.restauranthub.gui;

import com.restauranthub.controller.RestaurantController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class OrderPanel extends JPanel implements UiLog {
    private static final Color APP_BACKGROUND = new Color(255, 245, 238);
    private static final Color CARD_BACKGROUND = new Color(255, 255, 255);
    private static final Color HEADER_BACKGROUND = new Color(255, 204, 188);
    private static final Color INFO_BACKGROUND = new Color(232, 245, 233);
    private static final Color MENU_BACKGROUND = new Color(255, 248, 225);
    private static final Color SUMMARY_BACKGROUND = new Color(232, 234, 246);
    private static final Color LOG_BACKGROUND = new Color(237, 247, 255);
    private static final Color ACCENT_ORANGE = new Color(255, 112, 67);
    private static final Color ACCENT_GREEN = new Color(76, 175, 80);
    private static final Color ACCENT_BLUE = new Color(66, 165, 245);
    private static final Color ACCENT_PURPLE = new Color(126, 87, 194);
    private static final Color ACCENT_PINK = new Color(236, 64, 122);
    private static final Color TEXT_DARK = new Color(51, 51, 51);

    private final RestaurantController controller;
    private final ReceiptDialog receiptDialog;
    private final JTextField customerNameField;
    private final JTextField phoneField;
    private final JComboBox<String> menuComboBox;
    private final JCheckBox cheeseCheckBox;
    private final JCheckBox packagingCheckBox;
    private final JComboBox<String> orderTypeComboBox;
    private final JComboBox<String> strategyComboBox;
    private final JComboBox<String> gatewayComboBox;
    private final JTextField instructionsField;
    private final JTextArea menuArea;
    private final JTextArea orderSummaryArea;
    private final JTextArea logArea;
    private final JLabel orderIdLabel;
    private final JLabel statusLabel;
    private final JLabel finalPriceLabel;

    public OrderPanel(java.awt.Frame owner) {
        this.controller = new RestaurantController(this);
        this.receiptDialog = new ReceiptDialog(owner);

        setLayout(new BorderLayout(12, 12));
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        setBackground(APP_BACKGROUND);

        JLabel titleLabel = new JLabel("RestaurantHub Ordering Dashboard");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(TEXT_DARK);

        JLabel subtitleLabel = new JLabel("Build colorful orders and watch the design patterns work together.");
        subtitleLabel.setForeground(new Color(94, 53, 177));
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(HEADER_BACKGROUND);
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 171, 145), 2),
                BorderFactory.createEmptyBorder(14, 16, 14, 16)));
        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.add(subtitleLabel, BorderLayout.SOUTH);

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        styleCardPanel(formPanel, "Order Details", CARD_BACKGROUND);

        customerNameField = new JTextField();
        phoneField = new JTextField();
        menuComboBox = new JComboBox<>();
        cheeseCheckBox = new JCheckBox("Extra Cheese");
        packagingCheckBox = new JCheckBox("Premium Packaging");
        orderTypeComboBox = new JComboBox<>(new String[] {"Dine-in", "Takeaway", "Delivery"});
        strategyComboBox = new JComboBox<>(new String[] {"Regular", "Happy Hour"});
        gatewayComboBox = new JComboBox<>(new String[] {"Stripe", "PayPal"});
        instructionsField = new JTextField();

        styleInput(customerNameField);
        styleInput(phoneField);
        styleInput(instructionsField);
        styleCombo(menuComboBox);
        styleCombo(orderTypeComboBox);
        styleCombo(strategyComboBox);
        styleCombo(gatewayComboBox);
        styleCheckBox(cheeseCheckBox);
        styleCheckBox(packagingCheckBox);

        populateMenu(controller.getMenuItems());

        formPanel.add(createFormLabel("Customer Name"));
        formPanel.add(customerNameField);
        formPanel.add(createFormLabel("Phone Number"));
        formPanel.add(phoneField);
        formPanel.add(createFormLabel("Food Item"));
        formPanel.add(menuComboBox);
        formPanel.add(createFormLabel("Order Type"));
        formPanel.add(orderTypeComboBox);
        formPanel.add(createFormLabel("Pricing Strategy"));
        formPanel.add(strategyComboBox);
        formPanel.add(createFormLabel("Payment Gateway"));
        formPanel.add(gatewayComboBox);
        formPanel.add(createFormLabel("Special Instructions"));
        formPanel.add(instructionsField);
        formPanel.add(cheeseCheckBox);
        formPanel.add(packagingCheckBox);

        JPanel infoPanel = new JPanel(new GridLayout(0, 1, 6, 6));
        styleCardPanel(infoPanel, "Workflow Status", INFO_BACKGROUND);
        orderIdLabel = new JLabel("Order ID: N/A");
        statusLabel = new JLabel("State: Not Started");
        finalPriceLabel = new JLabel("Final Price: $0.00");
        styleStatusLabel(orderIdLabel, new Color(230, 81, 0));
        styleStatusLabel(statusLabel, new Color(46, 125, 50));
        styleStatusLabel(finalPriceLabel, new Color(25, 118, 210));
        infoPanel.add(orderIdLabel);
        infoPanel.add(statusLabel);
        infoPanel.add(finalPriceLabel);

        JPanel topPanel = new JPanel(new BorderLayout(12, 12));
        topPanel.setOpaque(false);
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(infoPanel, BorderLayout.EAST);

        menuArea = createTextArea("Menu", MENU_BACKGROUND);
        orderSummaryArea = createTextArea("Order Summary", SUMMARY_BACKGROUND);
        logArea = createTextArea("Pattern Activity Log", LOG_BACKGROUND);

        menuArea.setText(controller.getMenuText());
        orderSummaryArea.setText("No order built yet.");

        JPanel centerPanel = new JPanel(new GridLayout(1, 3, 12, 12));
        centerPanel.setOpaque(false);
        centerPanel.add(wrapArea("Available Menu", menuArea));
        centerPanel.add(wrapArea("Order Summary", orderSummaryArea));
        centerPanel.add(wrapArea("Pattern Activity Log", logArea));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
        buttonPanel.setOpaque(false);
        JButton buildOrderButton = new JButton("Build Order");
        JButton processOrderButton = new JButton("Process Order");
        JButton advanceStateButton = new JButton("Advance State");
        JButton previousStateButton = new JButton("Previous State");
        JButton proxyDemoButton = new JButton("Run Proxy Demo");

        styleButton(buildOrderButton, ACCENT_ORANGE);
        styleButton(processOrderButton, ACCENT_GREEN);
        styleButton(advanceStateButton, ACCENT_BLUE);
        styleButton(previousStateButton, ACCENT_PURPLE);
        styleButton(proxyDemoButton, ACCENT_PINK);

        buildOrderButton.addActionListener(event -> onBuildOrder());
        processOrderButton.addActionListener(event -> onProcessOrder());
        advanceStateButton.addActionListener(event -> onAdvanceState());
        previousStateButton.addActionListener(event -> onPreviousState());
        proxyDemoButton.addActionListener(event -> onProxyDemo());

        buttonPanel.add(buildOrderButton);
        buttonPanel.add(processOrderButton);
        buttonPanel.add(advanceStateButton);
        buttonPanel.add(previousStateButton);
        buttonPanel.add(proxyDemoButton);

        JPanel northPanel = new JPanel(new BorderLayout(0, 12));
        northPanel.setOpaque(false);
        northPanel.add(headerPanel, BorderLayout.NORTH);
        northPanel.add(topPanel, BorderLayout.CENTER);

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        controller.initializeSystem();
        append("GUI ready. Create an order to begin the workflow.");
    }

    public void shutdown() {
        controller.shutdown();
    }

    @Override
    public void append(String message) {
        logArea.append(message + System.lineSeparator());
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    private void onBuildOrder() {
        try {
            controller.buildOrder(
                    customerNameField.getText(),
                    phoneField.getText(),
                    (String) menuComboBox.getSelectedItem(),
                    cheeseCheckBox.isSelected(),
                    packagingCheckBox.isSelected(),
                    (String) orderTypeComboBox.getSelectedItem(),
                    instructionsField.getText(),
                    (String) strategyComboBox.getSelectedItem());
            refreshView();
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private void onProcessOrder() {
        try {
            String receiptText = controller.processOrder((String) gatewayComboBox.getSelectedItem());
            refreshView();
            receiptDialog.showReceipt(receiptText);
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private void onAdvanceState() {
        try {
            controller.advanceOrderState();
            refreshView();
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private void onPreviousState() {
        try {
            controller.moveToPreviousState();
            refreshView();
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private void onProxyDemo() {
        controller.runAdminDemo();
    }

    private void refreshView() {
        orderSummaryArea.setText(controller.getCurrentOrderSummary());
        orderIdLabel.setText("Order ID: " + controller.getCurrentOrderId());
        statusLabel.setText("State: " + controller.getCurrentStatus());
        finalPriceLabel.setText("Final Price: $" + String.format("%.2f", controller.getFinalPrice()));
    }

    private void populateMenu(List<String> items) {
        for (String item : items) {
            menuComboBox.addItem(item);
        }
    }

    private JTextArea createTextArea(String name, Color background) {
        JTextArea area = new JTextArea(14, 24);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setName(name);
        area.setBackground(background);
        area.setForeground(TEXT_DARK);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));
        area.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return area;
    }

    private JPanel wrapArea(String title, JTextArea area) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(CARD_BACKGROUND);
        panel.setBorder(createSectionBorder(title));
        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(area.getBackground());
        panel.add(scrollPane);
        return panel;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "RestaurantHub", JOptionPane.ERROR_MESSAGE);
    }

    private void styleCardPanel(JPanel panel, String title, Color background) {
        panel.setBackground(background);
        panel.setBorder(createSectionBorder(title));
    }

    private TitledBorder createSectionBorder(String title) {
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(255, 183, 77), 2),
                title);
        border.setTitleColor(new Color(109, 76, 65));
        border.setTitleFont(new Font("SansSerif", Font.BOLD, 15));
        return border;
    }

    private JLabel createFormLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(TEXT_DARK);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        return label;
    }

    private void styleStatusLabel(JLabel label, Color foreground) {
        label.setForeground(foreground);
        label.setFont(new Font("SansSerif", Font.BOLD, 15));
    }

    private void styleInput(JTextField field) {
        field.setBackground(Color.WHITE);
        field.setForeground(TEXT_DARK);
        field.setCaretColor(TEXT_DARK);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 204, 128), 2),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)));
    }

    private void styleCombo(JComboBox<String> comboBox) {
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(TEXT_DARK);
    }

    private void styleCheckBox(JCheckBox checkBox) {
        checkBox.setOpaque(true);
        checkBox.setBackground(CARD_BACKGROUND);
        checkBox.setForeground(TEXT_DARK);
        checkBox.setFont(new Font("SansSerif", Font.BOLD, 13));
    }

    private void styleButton(JButton button, Color background) {
        button.setBackground(background);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 13));
        button.setPreferredSize(new Dimension(150, 36));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(background.darker(), 2),
                BorderFactory.createEmptyBorder(6, 12, 6, 12)));
    }
}
