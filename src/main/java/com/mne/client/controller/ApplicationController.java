package com.mne.client.controller;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import com.mne.client.service.ClientExchangeRateService;
import com.mne.client.ui.MainPanel;
import com.mne.client.ui.presenter.CurrencyRatesPresenter;


/**
 * application controller
 * 
 * page navigation and event communication
 * 
 * @author Moritz Neuhaeuser<moritz.neuhaeuser@gmail.com>
 * @since(0.1.0)
 *
 */
public class ApplicationController {

    private SimpleEventBus eventBus;

    private ClientExchangeRateService currencyRatesService;
    private CurrencyRatesPresenter presenter;

    // We have a very simple application, so no need for nested views
    //	private HasWidgets container;

    @Inject
    public ApplicationController(
            SimpleEventBus eventBus, 
            MainPanel mainPanel,
            ClientExchangeRateService currencyRatesService
            ) {
        this.eventBus = eventBus;
        this.currencyRatesService = currencyRatesService;
        this.presenter = new CurrencyRatesPresenter(
                this.currencyRatesService, 
                this.eventBus, 
                mainPanel);
        bind();
    }
    private void bind() {
        // future implementations, like a graph need event communication to synchronize
    }

    /**
     * go to page
     * 
     * @param page
     */
    public void goTo(HasWidgets page){
        //		this.container = page;
        presenter.go(page);
    }

}
