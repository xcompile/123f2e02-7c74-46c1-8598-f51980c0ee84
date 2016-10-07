package com.mne.dto;

import java.math.BigDecimal;
import java.util.Map;


/**
 * currency rates dto
 * 
 * dto for currency rates result from webservice 
 * 
 * @author Moritz Neuhaeuser<moritz.neuhaeuser@gmail.com>
 * @since(0.1.0)
 *
 */
public class CurrencyRatesDTO {

    private String base;
    private Long timestamp;
    private Map<String,BigDecimal> rates;
    public CurrencyRatesDTO() {
    }
    
    public CurrencyRatesDTO(
            String base, 
            Long timestamp,
            Map<String, BigDecimal> rates) {
        super();
        this.base = base;
        this.timestamp = timestamp;
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }
    public void setBase(String base) {
        this.base = base;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    public void setRates(Map<String, BigDecimal> rates) {
        this.rates = rates;
    }


}
