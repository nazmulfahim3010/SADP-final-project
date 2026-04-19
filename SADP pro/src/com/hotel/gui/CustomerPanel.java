package com.hotel.gui;

import com.hotel.factory.UIFactory;
import com.hotel.model.Customer;
import com.hotel.observer.DataObserver;
import com.hotel.service.DataStore;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomerPanel extends JPanel implements DataObserver {
    private JTextField txtId = UIFactory.createStyledTextField();
    private JTextField txtName = UIFactory.createStyledTextField();
    private JTextField txtPhone = UIFactory.createStyledTextField();
    private JTextField txtEmail = UIFactory.createStyledTextField();
    private DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Name", "Phone", "Email"}, 0);
    private JTable table = new JTable(model);

    public CustomerPanel() {
        setLayout(new BorderLayout());
        setOpaque(false);
        DataStore.getInstance().addObserver(this);

        JPanel form = new JPanel(new GridLayout(4, 2, 15, 15));
        form.setOpaque(false);
        form.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        addLabelledField(form, "Customer ID:", txtId);
        addLabelledField(form, "Full Name:", txtName);
        addLabelledField(form, "Phone Number:", txtPhone);
        addLabelledField(form, "Email Address:", txtEmail);
        
        UIFactory.styleTable(table);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel btns = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        btns.setOpaque(false);
        btns.add(UIFactory.createModernButton("Add Customer", new Color(46, 204, 113), () -> {
            DataStore.getInstance().addCustomer(new Customer(txtId.getText(), txtName.getText(), txtPhone.getText(), txtEmail.getText()));
            clearFields();
        }));
        btns.add(UIFactory.createModernButton("Remove Selected", new Color(231, 76, 60), () -> {
            int r = table.getSelectedRow();
            if(r != -1) DataStore.getInstance().removeCustomer(DataStore.getInstance().getCustomers().get(r));
        }));
        btns.add(UIFactory.createModernButton("Undo Last", new Color(52, 152, 219), () -> DataStore.getInstance().undoCustomerChange()));

        add(form, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(btns, BorderLayout.SOUTH);
        onDataChanged();
    }

    private void addLabelledField(JPanel p, String label, JTextField tf) {
        JLabel l = new JLabel(label);
        l.setForeground(UIFactory.TEXT_SECONDARY);
        l.setFont(new Font("Segoe UI", Font.BOLD, 14));
        p.add(l);
        p.add(tf);
    }

    private void clearFields() {
        txtId.setText(""); txtName.setText(""); txtPhone.setText(""); txtEmail.setText("");
    }

    @Override public void onDataChanged() {
        model.setRowCount(0);
        for(Customer c : DataStore.getInstance().getCustomers()) 
            model.addRow(new Object[]{c.getCustomerId(), c.getName(), c.getPhone(), c.getEmail()});
    }
}

/**
 * DESIGN PATTERN: OBSERVER (CONCRETE OBSERVER)
 * 
 * WHY:
 * This panel observes the DataStore. Whenever a customer is added or 
 * removed, the DataStore notifies this panel, which then automatically 
 * refreshes the JTable to show the updated list.
 */
