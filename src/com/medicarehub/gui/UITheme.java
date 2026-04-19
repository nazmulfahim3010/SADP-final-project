package com.medicarehub.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

/**
 * Centralized UI Theme for MediCare Hub.
 * Adheres to strict university SADP course design rules.
 */
public class UITheme {
    // Exact Hex Values
    public static final Color PRIMARY     = Color.decode("#2196F3");
    public static final Color SECONDARY   = Color.decode("#4CAF50");
    public static final Color ACCENT      = Color.decode("#FF9800");
    public static final Color DANGER      = Color.decode("#F44336");
    public static final Color BG_LIGHT    = Color.decode("#F5F5F5");
    public static final Color PANEL_WHITE = Color.decode("#FFFFFF");
    public static final Color ROW_ALT     = Color.decode("#E3F2FD");
    public static final Color HEADER_DARK = Color.decode("#1565C0");

    // Fonts
    public static final Font FONT_BOLD_13 = new Font("SansSerif", Font.BOLD, 13);
    public static final Font FONT_BOLD_15 = new Font("SansSerif", Font.BOLD, 15);

    // Common Border
    public static final LineBorder CARD_BORDER = new LineBorder(Color.decode("#E0E0E0"), 1, true);

    /**
     * Styles a button based on the project rules.
     */
    public static void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(FONT_BOLD_13);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private UITheme() {}
}

/*
 * ======================================================
 * WHY THIS PATTERN / CLASS?
 * ======================================================
 * Pattern  : Constant Interface / Design System
 * Problem  : Ensuring UI consistency across multiple frames and panels.
 * Usage    : Used by all GUI components to fetch colors, fonts, and borders.
 * Without  : Color codes would be hardcoded everywhere, making UI changes 
 *            error-prone and inconsistent.
 * ======================================================
 */
