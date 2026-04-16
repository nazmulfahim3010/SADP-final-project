package com.restauranthub.gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class RestaurantFrame extends JFrame {
    private final OrderPanel orderPanel;

    public RestaurantFrame() {
        super("RestaurantHub - Smart Restaurant Ordering & Management System");
        this.orderPanel = new OrderPanel(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(orderPanel);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 245, 238));
        setIconImage(createAppIcon());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                orderPanel.shutdown();
            }
        });
    }

    private Image createAppIcon() {
        BufferedImage image = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D graphics = image.createGraphics();
        graphics.setColor(new Color(255, 112, 67));
        graphics.fillRoundRect(0, 0, 32, 32, 10, 10);
        graphics.setColor(Color.WHITE);
        graphics.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 18));
        graphics.drawString("R", 10, 22);
        graphics.dispose();
        return image;
    }
}
