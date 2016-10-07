package com.mne.client;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.mne.client.controller.ApplicationController;
import com.mne.client.resource.Messages;
import com.mne.client.service.ClientExchangeRateService;
import com.mne.client.ui.MainPanel;

/**
 * currency monitor module
 * 
 * guice/gin module configuration
 * 
 * @author Moritz Neuhaeuser<moritz.neuhaeuser@gmail.com>
 * @since(0.1.0)
 *
 */
public class CurrencyMonitoryModule extends AbstractGinModule {
    @Override
    protected void configure() {
        // Resourcebundle
        bind(Messages.class).in(Singleton.class);

        // Event/Controlling
        bind(SimpleEventBus.class).in(Singleton.class);
        bind(ApplicationController.class).in(Singleton.class);
        bind(ClientExchangeRateService.class).in(Singleton.class);

        // UI
        bind(MainPanel.class).in(Singleton.class);	}
}
