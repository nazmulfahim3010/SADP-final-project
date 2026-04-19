package com.hotel;

import com.hotel.gui.MainFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        // Set Look and Feel to System default for better base styling
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        // Launch Application
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
