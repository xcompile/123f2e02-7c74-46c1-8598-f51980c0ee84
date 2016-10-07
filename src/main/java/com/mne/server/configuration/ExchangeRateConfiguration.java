package com.mne.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;


/**
 * exchangerate configuration
 * 
 * component configuraiton
 * 
 * @author Moritz Neuhaeuser<moritz.neuhaeuser@gmail.com>
 * @since(0.1.0)
 *
 */
@Configuration
public class ExchangeRateConfiguration {
    
    @Bean(name="rest")
    public RestTemplate rest(){

        final RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Lists.newArrayList(MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8));
        restTemplate.setMessageConverters(Lists.newArrayList(
                mappingJackson2HttpMessageConverter
                ));

        return restTemplate;
    }

}
