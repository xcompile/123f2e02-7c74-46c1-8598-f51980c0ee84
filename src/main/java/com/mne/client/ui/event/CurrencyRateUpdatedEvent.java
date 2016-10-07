package com.mne.client.ui.event;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * currency rate updated event
 * 
 * event fires after currency rates list was updated
 * 
 * @author Moritz Neuhaeuser<moritz.neuhaeuser@gmail.com>
 * @since(0.1.0)
 *
 */
public class CurrencyRateUpdatedEvent extends Event<CurrencyRateUpdatedEvent.Handler> {

    public static interface Handler {
        public void dispatch(CurrencyRateUpdatedEvent event);
    }
    private static final Type<CurrencyRateUpdatedEvent.Handler> TYPE = new Type<CurrencyRateUpdatedEvent.Handler>();
    
    public CurrencyRateUpdatedEvent(){
        
    }
    
    public static HandlerRegistration register(EventBus eventBus,
        CurrencyRateUpdatedEvent.Handler handler) {
      return eventBus.addHandler(TYPE, handler);
    }   

    @Override
    public Type<CurrencyRateUpdatedEvent.Handler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(CurrencyRateUpdatedEvent.Handler handler) {
        handler.dispatch(this);
        
    }
}
