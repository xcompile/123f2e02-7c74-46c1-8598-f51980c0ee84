package com.mne;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.mne.client.CurrencyMonitorClient;
import com.mne.server.configuration.ExchangeRateConfiguration;


/**
 * currency monitor application
 * 
 * server application for {@link CurrencyMonitorClient}
 * 
 * @author Moritz Neuhaeuser<moritz.neuhaeuser@gmail.com>
 * @since(0.1.0)
 *
 */
@SpringBootApplication
@Import(
        {
            ExchangeRateConfiguration.class
        }		
)
public class CurrencyMonitorApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(CurrencyMonitorApplication.class, args);
    }
}
