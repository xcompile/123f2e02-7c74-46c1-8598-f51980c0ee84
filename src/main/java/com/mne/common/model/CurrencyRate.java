package com.mne.common.model;

import java.math.BigDecimal;


/**
 * currency rate
 * 
 * model for currency rate
 * 
 * @author Moritz Neuhaeuser<moritz.neuhaeuser@gmail.com>
 * @since(0.1.0)
 *
 */
public class CurrencyRate {

    private Currency currency;
    private BigDecimal rate;
    

    public CurrencyRate() {
        super();
    }
    
    public CurrencyRate(Currency currency, BigDecimal rate) {
        super();
        this.currency = currency;
        this.rate = rate;
    }
    public Currency getCurrency() {
        return currency;
    }
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    public BigDecimal getRate() {
        return rate;
    }
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return currency.getCode() 
               + " - "
               + currency.getName();
    }
    
    
}
