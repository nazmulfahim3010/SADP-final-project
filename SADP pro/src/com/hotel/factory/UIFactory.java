package com.hotel.factory;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class UIFactory {
    // Theme Colors
    public static final Color BG_DARK = new Color(26, 26, 46);
    public static final Color BG_LIGHT = new Color(22, 33, 62);
    public static final Color ACCENT_GOLD = new Color(212, 175, 55);
    public static final Color TEXT_PRIMARY = Color.WHITE;
    public static final Color TEXT_SECONDARY = new Color(162, 162, 208);

    public static JButton createModernButton(String text, Color baseColor, Runnable action) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();

                ButtonModel model = getModel();
                if (model.isPressed()) {
                    g2.translate(1, 1);
                    g2.setColor(baseColor.darker().darker());
                } else if (model.isRollover()) {
                    g2.setPaint(new GradientPaint(0, 0, baseColor.brighter(), 0, h, baseColor));
                } else {
                    g2.setPaint(new GradientPaint(0, 0, baseColor, 0, h, baseColor.darker()));
                }

                g2.fillRoundRect(0, 0, w - (model.isPressed() ? 1 : 0), h - (model.isPressed() ? 1 : 0), 12, 12);

                g2.setColor(TEXT_PRIMARY);
                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                Rectangle2D r = fm.getStringBounds(getText(), g2);
                int x = (w - (int) r.getWidth()) / 2;
                int y = (h - (int) r.getHeight()) / 2 + fm.getAscent();
                g2.drawString(getText(), x, y);
                g2.dispose();
            }
        };

        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> action.run());
        btn.setPreferredSize(new Dimension(160, 42));
        return btn;
    }

    public static JButton createSidebarButton(String text, Runnable action) {
        return new SidebarButton(text, action);
    }

    public static class SidebarButton extends JButton {
        private boolean selected = false;
        private float alpha = 0.0f;
        private Timer timer;

        public SidebarButton(String text, Runnable action) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Fix alignment and sizing for BoxLayout
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
            setPreferredSize(new Dimension(220, 50));

            setFont(new Font("Segoe UI", Font.BOLD, 15));
            setForeground(TEXT_PRIMARY);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    animateAlpha(0.15f);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    animateAlpha(0.0f);
                }
            });
            addActionListener(e -> action.run());
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
            repaint();
        }

        private void animateAlpha(float target) {
            if (timer != null && timer.isRunning())
                timer.stop();
            timer = new Timer(20, e -> {
                if (Math.abs(alpha - target) < 0.01f) {
                    alpha = target;
                    timer.stop();
                } else {
                    alpha += (target - alpha) * 0.2f;
                }
                repaint();
            });
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();

            if (selected) {
                g2.setPaint(new GradientPaint(0, 0, ACCENT_GOLD, w, 0, ACCENT_GOLD.darker()));
                g2.fillRoundRect(10, 5, w - 20, h - 10, 10, 10);
                g2.setColor(BG_DARK);
            } else {
                if (alpha > 0) {
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                    g2.setColor(Color.WHITE);
                    g2.fillRoundRect(10, 5, w - 20, h - 10, 10, 10);
                }
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
                g2.setColor(TEXT_PRIMARY);
            }

            FontMetrics fm = g2.getFontMetrics();
            Rectangle2D r = fm.getStringBounds(getText(), g2);
            int x = (w - (int) r.getWidth()) / 2;
            int y = (h - (int) r.getHeight()) / 2 + fm.getAscent();
            g2.drawString(getText(), x, y);
            g2.dispose();
        }
    }

    public static JPanel createRoundedPanel(int radius, Color color) {
        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
                g2.dispose();
            }
        };
        p.setOpaque(false);
        return p;
    }

    public static void styleTable(JTable table) {
        table.setRowHeight(35);
        table.setBackground(BG_LIGHT);
        table.setForeground(TEXT_PRIMARY);
        table.setGridColor(BG_DARK);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setSelectionBackground(ACCENT_GOLD);
        table.setSelectionForeground(BG_DARK);
        table.setShowVerticalLines(false);

        JTableHeader header = table.getTableHeader();
        header.setBackground(BG_DARK);
        header.setForeground(ACCENT_GOLD);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setPreferredSize(new Dimension(0, 40));

        ((DefaultTableCellRenderer) table.getDefaultRenderer(Object.class)).setOpaque(false);
    }

    public static JTextField createStyledTextField() {
        JTextField tf = new JTextField();
        tf.setBackground(BG_DARK);
        tf.setForeground(TEXT_PRIMARY);
        tf.setCaretColor(ACCENT_GOLD);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(TEXT_SECONDARY, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        return tf;
    }
}
