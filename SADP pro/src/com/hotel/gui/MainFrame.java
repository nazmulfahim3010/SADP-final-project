package com.hotel.gui;

import com.hotel.factory.UIFactory;
import com.hotel.service.DataStore;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private List<UIFactory.SidebarButton> sidebarButtons = new ArrayList<>();

    public MainFrame() {
        super("Grand Horizon Hotel - Dashboard");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        DataStore.getInstance();
        DataStore.loadAll("hoteldata.dat");

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(UIFactory.BG_DARK);
        
        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(250, 0));
        sidebar.setBackground(UIFactory.BG_LIGHT);
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, UIFactory.ACCENT_GOLD.darker()));
        
        JLabel logo = new JLabel("GRAND HORIZON");
        logo.setForeground(UIFactory.ACCENT_GOLD);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBorder(BorderFactory.createEmptyBorder(30, 0, 50, 0));
        sidebar.add(logo);
        
        addSidebarItem(sidebar, "Dashboard", "home");
        addSidebarItem(sidebar, "Customers", "customers");
        addSidebarItem(sidebar, "Rooms", "rooms");
        addSidebarItem(sidebar, "Reservations", "reservations");
        
        // Main Content Area
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setOpaque(false);
        
        cardPanel.add(createHomePanel(), "home");
        cardPanel.add(new CustomerPanel(), "customers");
        cardPanel.add(new RoomPanel(), "rooms");
        cardPanel.add(new ReservationPanel(), "reservations");
        
        root.add(sidebar, BorderLayout.WEST);
        root.add(cardPanel, BorderLayout.CENTER);
        
        setContentPane(root);
        
        // Select first item by default
        if (!sidebarButtons.isEmpty()) {
            sidebarButtons.get(0).setSelected(true);
        }
        
        setVisible(true);
    }

    private void addSidebarItem(JPanel sidebar, String text, String panelName) {
        UIFactory.SidebarButton btn = (UIFactory.SidebarButton) UIFactory.createSidebarButton(text, () -> {
            showPanel(panelName);
            updateSidebarSelection(text);
        });
        sidebar.add(btn);
        sidebar.add(Box.createVerticalStrut(10));
        sidebarButtons.add(btn);
    }

    private void updateSidebarSelection(String selectedText) {
        for (UIFactory.SidebarButton btn : sidebarButtons) {
            btn.setSelected(btn.getText().equals(selectedText));
        }
    }

    private JPanel createHomePanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setOpaque(false);
        JLabel welcome = new JLabel("Welcome to Grand Horizon Management");
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 36));
        welcome.setForeground(UIFactory.TEXT_PRIMARY);
        p.add(welcome);
        return p;
    }

    private void showPanel(String name) {
        cardLayout.show(cardPanel, name);
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch(Exception e) {}
        SwingUtilities.invokeLater(MainFrame::new);
    }
}

/**
 * DESIGN PATTERN: FACADE (in conjunction with UI Layering)
 * 
 * WHY:
 * MainFrame acts as the high-level entry point for the GUI. It coordinates 
 * multiple sub-panels (CustomerPanel, RoomPanel, etc.) and uses CardLayout 
 * to manage view transitions. By isolating these views into separate classes 
 * and using MainFrame as a coordinator, we maintain a clean separation of 
 * concerns while providing a simple, unified interface for the user.
 */
