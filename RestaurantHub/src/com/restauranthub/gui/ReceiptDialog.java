package com.restauranthub.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ReceiptDialog extends JDialog {
    private final JTextArea receiptArea;

    public ReceiptDialog(java.awt.Frame owner) {
        super(owner, "Receipt", Dialog.ModalityType.APPLICATION_MODAL);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(255, 248, 225));

        receiptArea = new JTextArea(12, 32);
        receiptArea.setEditable(false);
        receiptArea.setLineWrap(true);
        receiptArea.setWrapStyleWord(true);
        receiptArea.setBackground(new Color(255, 253, 231));
        receiptArea.setForeground(new Color(62, 39, 35));
        receiptArea.setFont(new Font("Serif", Font.PLAIN, 16));
        receiptArea.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));

        JButton closeButton = new JButton("Close");
        closeButton.setBackground(new Color(255, 112, 67));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setFont(new Font("SansSerif", Font.BOLD, 13));
        closeButton.addActionListener(event -> setVisible(false));

        JScrollPane scrollPane = new JScrollPane(receiptArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(255, 183, 77), 2));
        scrollPane.getViewport().setBackground(receiptArea.getBackground());

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(255, 224, 178));
        footerPanel.add(closeButton);

        add(scrollPane, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(owner);
    }

    public void showReceipt(String receiptText) {
        receiptArea.setText(receiptText);
        receiptArea.setCaretPosition(0);
        setVisible(true);
    }
}
