package com.yonder.tss.client.impl;


import com.yonder.tss.client.Client;
import com.yonder.tss.data.ForecastData;
import com.yonder.tss.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ForecastClient implements Client<ForecastData> {
    private static final String RESOURCE_IDENTIFIER_PATH_VARIABLE = "{resourceIdentifier}";
    private final RestTemplate restTemplate;
    private final String forecastServerUrl;

    public ForecastClient(@Value("${forecast.server-url}") String forecastServerUrl) {
        this.forecastServerUrl = forecastServerUrl;
        this.restTemplate = new RestTemplateBuilder().rootUri(forecastServerUrl).build();
    }

    @Override
    public ForecastData fetchData(String resourceIdentifier) throws ResourceNotFoundException {
        try {
            ResponseEntity<ForecastData> response = restTemplate.getForEntity("/" + RESOURCE_IDENTIFIER_PATH_VARIABLE, ForecastData.class, resourceIdentifier);
            return response.getBody();
        } catch (HttpClientErrorException exception) {
            throw new ResourceNotFoundException(exception.getMessage());
        }
    }
}
