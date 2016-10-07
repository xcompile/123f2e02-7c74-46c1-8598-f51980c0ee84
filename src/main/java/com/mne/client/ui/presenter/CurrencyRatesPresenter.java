package com.mne.client.ui.presenter;

import java.util.Date;
import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.github.gwtbootstrap.datepicker.client.ui.DateBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.mne.client.CurrencyMonitorInjector;
import com.mne.client.resource.Messages;
import com.mne.client.service.ClientExchangeRateService;
import com.mne.client.ui.component.CurrencySuggestion;
import com.mne.client.ui.component.NotificationPanel;
import com.mne.client.ui.event.CurrencyRateUpdatedEvent;
import com.mne.client.ui.event.CurrencySelectedEvent;
import com.mne.client.ui.event.CurrencyUpdatedEvent;
import com.mne.common.model.Currency;
import com.mne.common.model.CurrencyRate;

public class CurrencyRatesPresenter implements Presenter {

    private final CurrencyMonitorInjector injector = GWT.create(CurrencyMonitorInjector.class);

    private static final String DEFAULT_SELECTION = "USD";

    private Messages messages;
    
    private Display display;
    
    public interface Display{
        public void setCurrencies(List<Currency> currencies);
        public void setCurrencyRatesTable(List<CurrencyRate> currencyRates);
        public SuggestBox  getCurrencyComponent();
        public Widget asWidget();
        public DateBox getHistoricDate();
    }
    private ClientExchangeRateService currencyRatesService;
    private EventBus eventBus;
    
    
    
    
    public CurrencyRatesPresenter(
            ClientExchangeRateService currencyRatesService, 
            EventBus eventBus, 
            CurrencyRatesPresenter.Display mainPanel) {
        super();
        this.currencyRatesService = currencyRatesService;
        this.eventBus = eventBus;
        this.display = mainPanel;
        this.messages = injector.getMessages();

        

    }
    public void go(final HasWidgets container) {
        bind();
        container.add(display.asWidget());

        updateCurrencies();
        
    }
    public void bind() {
        //binding
        display.getCurrencyComponent().addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {
            
            @Override
            public void onSelection(SelectionEvent<Suggestion> event) {
                final CurrencySuggestion item = (CurrencySuggestion) event.getSelectedItem();
                eventBus.fireEvent(new CurrencySelectedEvent(item.getCurrency().getCode()));
            }
        });
    
        display.getHistoricDate().addValueChangeHandler(new ValueChangeHandler<Date>() {
            
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                updateCurrencyRates(DEFAULT_SELECTION,event.getValue());
            }
        });
        
        CurrencyUpdatedEvent.register(eventBus, new CurrencyUpdatedEvent.Handler() {
            
            @Override
            public void dispatch(CurrencyUpdatedEvent event) {
                display.getCurrencyComponent().setText(DEFAULT_SELECTION);
                updateCurrencyRates(DEFAULT_SELECTION,display.getHistoricDate().getValue());
            }
        });
    
        CurrencySelectedEvent.register(eventBus, new CurrencySelectedEvent.Handler() {
            
            @Override
            public void dispatch(CurrencySelectedEvent event) {
                updateCurrencyRates(event.getCode(), display.getHistoricDate().getValue());
            }
        });
        
        
    }
    public Display getView(){
        return display;
      }
    
    private void updateCurrencyRates(String code, Date date){
        String strDate = null;
        if (null != date) {

            strDate = DateTimeFormat.getFormat("yyyy-MM-dd").format(
                    date
                    );
        }
        currencyRatesService.rates(code,strDate, new MethodCallback<List<CurrencyRate>>(){

            @Override
            public void onFailure(Method method, Throwable exception) {
                final NotificationPanel notificationPanel = new NotificationPanel(
                        messages.remoteCallFailed(), 
                        true, 
                        true
                );
                notificationPanel.show();
            }

            @Override
            public void onSuccess(Method method, List<CurrencyRate> response) {
                display.setCurrencyRatesTable(response);
                eventBus.fireEvent(new CurrencyRateUpdatedEvent());
            }
            
        });
    }
    
    private void updateCurrencies(){
        currencyRatesService.currencies(new MethodCallback<List<Currency>>(){

            @Override
            public void onFailure(Method method, Throwable exception) {
                final NotificationPanel notificationPanel = new NotificationPanel(
                        messages.remoteCallFailed(), 
                        true, 
                        true
                );
                notificationPanel.show();

            }

            @Override
            public void onSuccess(Method method, List<Currency> response) {
                display.setCurrencies(response);
                eventBus.fireEvent(new CurrencyUpdatedEvent());
            }
            
        });
    }
}
