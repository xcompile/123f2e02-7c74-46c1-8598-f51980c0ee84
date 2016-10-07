package com.mne.server.service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.mne.common.model.Currency;
import com.mne.dto.CurrencyRatesDTO;
import com.mne.service.ExchangeRateConversionService;


/**
 * exchangerate service
 * 
 * rest service providing currency information
 * 
 * @author Moritz Neuhaeuser<moritz.neuhaeuser@gmail.com>
 * @since(0.1.0)
 *
 */
@Service
public class ExchangeRateService {
    private static final Logger LOG = LoggerFactory.getLogger(ExchangeRateService.class);

    private static final String DEFAULT_BASE_CURRENCY = "USD";

    @Autowired
    @Qualifier("rest")
    private RestTemplate restTemplate;

    @Value("${rest.api.url}")
    String restApiBaseUrl;

    @Value("${rest.api.apiKey}")
    String restApiKey;
    @Autowired
    private ExchangeRateConversionService exchangeRateConversionService;

    /**
     * latest exchange rates
     * 
     * @param baseCurrency	base currency
     * 
     * @return currency rates table
     */
    public CurrencyRatesDTO latest(
            String baseCurrency){
        final UriComponentsBuilder builder = createBuilderOpenExchange();
        builder
        .pathSegment("latest.json")
        .queryParam("base", Optional.ofNullable("USD").orElse(DEFAULT_BASE_CURRENCY));

        final CurrencyRatesDTO originalRates = restTemplate.getForObject(
                builder.build().encode().toUri(), 
                CurrencyRatesDTO.class);

        if(!StringUtils.equalsIgnoreCase(baseCurrency,"USD")){
            LOG.warn("due to api limitation only USD rates are available, manually convert to {}",baseCurrency);

            Map<String, BigDecimal> conversionToTargetBaseCurrency = exchangeRateConversionService.convertExhangeRateTableToNewTargetCurrency(
                    baseCurrency, 
                    originalRates.getRates());
            return new CurrencyRatesDTO(
                    baseCurrency,
                    originalRates.getTimestamp().longValue(),
                    conversionToTargetBaseCurrency
                    );
        }
        return originalRates;

    }


    public CurrencyRatesDTO historic(String baseCurrency, String date) {
        final UriComponentsBuilder builder = createBuilderOpenExchange();
                 
         
        builder
        .pathSegment("historical")
        .pathSegment(date + ".json")
        .queryParam("base", Optional.ofNullable("USD").orElse(DEFAULT_BASE_CURRENCY));

        final CurrencyRatesDTO originalRates = restTemplate.getForObject(
                builder.build().encode().toUri(), 
                CurrencyRatesDTO.class);

        if(!StringUtils.equalsIgnoreCase(baseCurrency,"USD")){
            LOG.warn("due to api limitation only USD rates are available, manually convert to {}",baseCurrency);

            Map<String, BigDecimal> conversionToTargetBaseCurrency = exchangeRateConversionService.convertExhangeRateTableToNewTargetCurrency(
                    baseCurrency, 
                    originalRates.getRates());
            return new CurrencyRatesDTO(
                    baseCurrency,
                    originalRates.getTimestamp().longValue(),
                    conversionToTargetBaseCurrency
                    );
        }
        return originalRates;
    }


    /**
     * all currencies
     * 
     * @return list of all currencies
     */
    public List<Currency> currencies(){
        final UriComponentsBuilder builder = createBuilderOpenExchange();
        builder
        .pathSegment("currencies.json");

        final Map<String,String> response = restTemplate.getForObject(
                builder.build().encode().toUri(), 
                Map.class);
        return response.entrySet()
                .stream()
                .map(entry ->  new Currency(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

    }

    protected UriComponentsBuilder createBuilderOpenExchange(){
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(restApiBaseUrl)
                .queryParam("app_id", restApiKey);
        return builder;


        //	protected UriComponentsBuilder createBuilderFixer(){
        //		UriComponentsBuilder builder = UriComponentsBuilder
        //				.fromHttpUrl("http://api.fixer.io/");
        //		return builder;
        //	}
    }
}
