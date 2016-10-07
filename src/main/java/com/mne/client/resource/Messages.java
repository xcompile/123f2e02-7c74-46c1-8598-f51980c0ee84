package com.mne.client.resource;

/**
 * I18N messages
 * 
 * I18N keys
 * 
 * @author Moritz Neuhaeuser<moritz.neuhaeuser@gmail.com>
 * @since(0.1.0)
 *
 */
public interface Messages extends com.google.gwt.i18n.client.Messages {

    @Key("currency")
    String currency();

    @Key("currencyRate")
    String currencyRate();

    
    @Key("currencyMonitor")
    String currencyMonitor();


    @Key("currencyMonitorSimple")
    String currencyMonitorSimple();


    @Key("typeCurrency")
    String typeCurrency();
    

    @Key("remoteCallFailed")
    String remoteCallFailed();
    
    @Key("chooseCurrency")
    String chooseCurrency();
    

    @Key("chooseDate")
    String chooseDate();
}
