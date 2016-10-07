package com.mne.client.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import com.mne.common.model.Currency;
import com.mne.common.model.CurrencyRate;
import com.mne.server.service.ExchangeRateService;

/**
 * client exchangerate service
 * 
 * stub to exchange requests with {@link ExchangeRateService}
 * 
 * @author Moritz Neuhaeuser<moritz.neuhaeuser@gmail.com>
 * @since(0.1.0)
 *
 */
public interface ClientExchangeRateService extends RestService{

    
    @GET
    @Path("rates")
    public void rates(
            @QueryParam("base") String base,
            @QueryParam("date") String date, MethodCallback<List<CurrencyRate>> callback);

    @GET
    @Path("currencies")
    public void currencies(MethodCallback<List<Currency>> methodCallback);
}
