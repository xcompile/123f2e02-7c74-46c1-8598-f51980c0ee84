package com.mne.client.ui.component;

import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.mne.common.model.Currency;

/**
 * currency suggestion
 * 
 * custom implementation for {@link Suggestion} for {@link Currency}
 * 
 * @author Moritz Neuhaeuser<moritz.neuhaeuser@gmail.com>
 * @since(0.1.0)
 *
 */
public class CurrencySuggestion implements Suggestion {

    private final Currency currency;
    public CurrencySuggestion(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String getDisplayString(){
        return currency.getCode() + " - " + currency.getName();
    }

    @Override
    public String getReplacementString() {
        return currency.getCode() + " - " + currency.getName();
    }

    public Currency getCurrency() {
        return currency;
    }



}
