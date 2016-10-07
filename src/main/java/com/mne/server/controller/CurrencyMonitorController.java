package com.mne.server.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.mne.common.model.Currency;
import com.mne.common.model.CurrencyRate;
import com.mne.dto.CurrencyRatesDTO;
import com.mne.server.service.ExchangeRateService;

/**
 * REST Controller Currency Monitor
 * 
 * provides rates and other information about currencies
 * 
 * @author Moritz Neuhaeuser<moritz.neuhaeuser@gmail.com>
 * @since(0.1.0)
 *
 */
@RestController
@RequestMapping("/api")
public class CurrencyMonitorController {
    
    @Autowired
    ExchangeRateService exchangeRateService;
    
    @RequestMapping(path="/rates",method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE})
    public List<CurrencyRate> currencyRates(
            @RequestParam(name="base",required=false,defaultValue="USD") String baseCurrency,
            @RequestParam(name="date",required=false) String strDate

            ){
        

        
        final LocalDate today = LocalDate.now();
        LocalDate date = today; 
        if (StringUtils.isNotEmpty(strDate)) {
            date = LocalDate.parse(strDate,DateTimeFormatter.ISO_DATE);
        }
        if (date.isBefore(today)) {
            // before today -> historic rates
            return convert(exchangeRateService.historic(baseCurrency,strDate));
        } else {
            // today (future) -> latest rates
            return convert(exchangeRateService.latest(baseCurrency));
        }
    }
    
    private List<CurrencyRate> convert(CurrencyRatesDTO latest) {
        final List<CurrencyRate> result = Lists.newArrayList();
        List<Currency> currencies = currencies();
        for (Entry<String,BigDecimal> entry: latest.getRates().entrySet()) {
            result.add(
                    new CurrencyRate(currencies.stream()
                            .filter(cur -> StringUtils.equalsIgnoreCase(entry.getKey(),cur.getCode()))
                            .findFirst()
                            .orElse(new Currency(entry.getKey(), "No name")), 
                            entry.getValue()));
            
        }
        return result;
        
    }

    @RequestMapping(path="/currencies",method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE})
    public List<Currency> currencies(){
        return exchangeRateService.currencies();
    }

}
