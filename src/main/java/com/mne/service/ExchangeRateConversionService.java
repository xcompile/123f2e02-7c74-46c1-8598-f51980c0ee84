package com.mne.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;


/**
 * exchange rate conversion service
 * 
 * exchange rate conversion tools
 * 
 * @author Moritz Neuhaeuser<moritz.neuhaeuser@gmail.com>
 * @since(0.1.0)
 *
 */
@Service
public class ExchangeRateConversionService {


    /**
     * switch the base currency of any exchange rate table
     * 
     * @param targetCurrencyBase	target currency
     * @param exchangeRates			exchangerate table
     * @return
     */
    public Map<String, BigDecimal> convertExhangeRateTableToNewTargetCurrency(
            final String targetCurrencyBase, 
            Map<String,BigDecimal> exchangeRates
            ) {
        if (!exchangeRates.containsKey(targetCurrencyBase.toUpperCase())) {
            throw new IllegalArgumentException("could not find rate for base currency " + targetCurrencyBase.toUpperCase());
        }
        final BigDecimal targetRateToExistingRate = exchangeRates.get(targetCurrencyBase.toUpperCase());
        final Map<String,BigDecimal> convertedRates = Maps.newConcurrentMap();
        exchangeRates.entrySet().stream().forEach(cr -> 
        convertedRates.put(
                cr.getKey(),
                BigDecimal.ONE.multiply(cr.getValue())
                .multiply(BigDecimal.ONE
                        .divide(targetRateToExistingRate,100,RoundingMode.HALF_UP))
                ));
        return convertedRates;
    }


}
