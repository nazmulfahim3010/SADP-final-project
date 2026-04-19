package com.medicarehub.main;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import com.medicarehub.gui.UITheme;

/**
 * Main entry point for the MediCare Hub Application.
 * Follows strict university SADP rules for GUI initialization.
 */
public class MediCareApp {
    
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater for all GUI creation as per project rules
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("MediCare Hub — Patient Management System");
            frame.setSize(1100, 720);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null); // Center on screen
            frame.getContentPane().setBackground(UITheme.BG_LIGHT);
            
            JLabel welcomeLabel = new JLabel("Welcome to MediCare Hub (SADP Project)", SwingConstants.CENTER);
            welcomeLabel.setFont(UITheme.FONT_BOLD_15);
            welcomeLabel.setForeground(UITheme.PRIMARY);
            
            frame.add(welcomeLabel, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}

/*
 * ======================================================
 * WHY THIS PATTERN / CLASS?
 * ======================================================
 * Pattern  : Application Driver (EntryPoint)
 * Problem  : Need a safe way to launch the Swing event dispatch thread.
 * Usage    : Launches the main JFrame and initializes global settings.
 * Without  : Launching GUI from the main thread can lead to race conditions 
 *            and UI freezing.
 * ======================================================
 */
