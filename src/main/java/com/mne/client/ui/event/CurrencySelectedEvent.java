package com.mne.client.ui.event;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * currency selected event
 * 
 * event fires when currency is selected in result list
 * 
 * @author Moritz Neuhaeuser<moritz.neuhaeuser@gmail.com>
 * @since(0.1.0)
 *
 */
public class CurrencySelectedEvent extends Event<CurrencySelectedEvent.Handler> {

    public static interface Handler {
        public void dispatch(CurrencySelectedEvent event);
    }
    private static final Type<CurrencySelectedEvent.Handler> TYPE = new Type<CurrencySelectedEvent.Handler>();
    private final String code;
    
    /**
     * 
     * @param code	selected currency ALPHA3 code
     */
    public CurrencySelectedEvent(String code){
        this.code = code;
    }
    
    public String getCode() {
        return code;
    }

    public static HandlerRegistration register(EventBus eventBus,
        CurrencySelectedEvent.Handler handler) {
      return eventBus.addHandler(TYPE, handler);
    }   

    @Override
    public Type<CurrencySelectedEvent.Handler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(CurrencySelectedEvent.Handler handler) {
        handler.dispatch(this);
        
    }
}
