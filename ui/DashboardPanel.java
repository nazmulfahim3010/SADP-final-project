package ui;

import javax.swing.*;
import pattern.Observer;
import pattern.HotelSystem;

public class DashboardPanel extends JPanel implements Observer {

    private JLabel label;
    private service.HotelService service;

    public DashboardPanel() {

        service = HotelSystem.getInstance().getHotelService();
        service.addObserver(this);

        label = new JLabel();
        add(label);
        update();
    }

    public void update() {
        label.setText("Total Rooms: " + service.totalRooms() +
                " | Available Rooms: " + service.availableRooms());
    }
}