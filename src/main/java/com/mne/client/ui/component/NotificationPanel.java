package com.mne.client.ui.component;

import com.github.gwtbootstrap.client.ui.AlertBlock;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.PopupPanel;

public class NotificationPanel extends PopupPanel{

    public NotificationPanel(
            String text, 
            boolean autoHide,
            boolean modal
            ) {
        super(autoHide, modal);
        setWidget(new AlertBlock(text));
    }

    void show(int delayMilliseconds) {
        show();
        Timer t = new Timer() {
            @Override
            public void run() {
                NotificationPanel.this.hide();
            }
        };

        // Schedule the timer to close the popup in 3 seconds.
        t.schedule(3000);
    }
}
