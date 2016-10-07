package com.mne.client;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.Ginjector;
import com.mne.client.controller.ApplicationController;
import com.mne.client.resource.Messages;
import com.mne.client.service.ClientExchangeRateService;
import com.mne.client.ui.MainPanel;

/**
 * currency monitor injector 
 * 
 * can be used to inject common services
 * 
 * @author Moritz Neuhaeuser<moritz.neuhaeuser@gmail.com>
 * @since(0.1.0)
 *
 */
public interface CurrencyMonitorInjector extends Ginjector {

    // Resourcebundle
    public Messages getMessages();
    
    // Event/Controlling
    public SimpleEventBus getEventBus();
    public ApplicationController getEventController();
    public ClientExchangeRateService getCurrencyRatesService();
    
    // UI
    public MainPanel getMainPanel(); 


}
