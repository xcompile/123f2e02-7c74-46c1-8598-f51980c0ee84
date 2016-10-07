package com.mne.client;

import org.fusesource.restygwt.client.Defaults;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.mne.client.controller.ApplicationController;

/**
 * currency monitor client entry point
 * 
 * initialize the application and delegate to controller
 * 
 * @author Moritz Neuhaeuser<moritz.neuhaeuser@gmail.com>
 * @since(0.1.0)
 *
 */
public class CurrencyMonitorClient implements EntryPoint {

    private static final String API_BASE = "/api";
    
    private final CurrencyMonitorInjector injector = GWT.create(CurrencyMonitorInjector.class);

    @Override
    public void onModuleLoad() {
        setupRemoteService();

        // Event/Controlling
        final ApplicationController eventController = injector.getEventController();
        
        eventController.goTo(RootLayoutPanel.get());

    }
    
    private void setupRemoteService() {
        Defaults.setServiceRoot(GWT.getHostPageBaseURL() + API_BASE);
    }

}
