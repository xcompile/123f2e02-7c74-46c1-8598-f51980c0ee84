package com.mne.client.ui.event;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * currency rate updated event
 * 
 *  event fires after currency list was updated
 * 
 * @author Moritz Neuhaeuser<moritz.neuhaeuser@gmail.com>
 * @since(0.1.0)
 *
 */
public class CurrencyUpdatedEvent extends Event<CurrencyUpdatedEvent.Handler> {

    public static interface Handler {
        public void dispatch(CurrencyUpdatedEvent event);
    }
    private static final Type<CurrencyUpdatedEvent.Handler> TYPE = new Type<CurrencyUpdatedEvent.Handler>();
    
    public CurrencyUpdatedEvent(){
        
    }

    public static HandlerRegistration register(EventBus eventBus,
        CurrencyUpdatedEvent.Handler handler) {
      return eventBus.addHandler(TYPE, handler);
    }   

    @Override
    public Type<CurrencyUpdatedEvent.Handler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(CurrencyUpdatedEvent.Handler handler) {
        handler.dispatch(this);
        
    }
}
