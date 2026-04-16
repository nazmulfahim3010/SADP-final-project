package com.restauranthub.gui;

import com.restauranthub.behavioral.observer.Observer;

public class GuiObserver implements Observer {
    private final String channelName;
    private final UiLog uiLog;

    public GuiObserver(String channelName, UiLog uiLog) {
        this.channelName = channelName;
        this.uiLog = uiLog;
    }

    @Override
    public void update(String orderId, String status) {
        uiLog.append("[Observer][" + channelName + "] Order " + orderId + " is now " + status);
    }
}
