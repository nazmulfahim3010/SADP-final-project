package com.hotel.gui;

import com.hotel.factory.UIFactory;
import javax.swing.*;
import java.awt.*;

public class BaseFrame extends JFrame {
    public BaseFrame(String title, JPanel panel) {
        super(title);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(UIFactory.BG_DARK);
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel header = new JLabel(title);
        header.setFont(new Font("Segoe UI", Font.BOLD, 28));
        header.setForeground(UIFactory.ACCENT_GOLD);
        header.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        container.add(header, BorderLayout.NORTH);
        container.add(panel, BorderLayout.CENTER);
        
        setContentPane(container);
    }
}
