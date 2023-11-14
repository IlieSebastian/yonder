package com.yonder.tss.client;


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
    private final RestTemplate restTemplate;
    private final String forecastServerUrl;

    public ForecastClient(@Value("${forecast.server.url}") String forecastServerUrl) {
        this.forecastServerUrl = forecastServerUrl;
        this.restTemplate = new RestTemplateBuilder().rootUri(forecastServerUrl).build();
    }

    @Override
    public ResponseEntity<ForecastData> getData() throws ResourceNotFoundException {
        try {
            return restTemplate.getForEntity("/", ForecastData.class);
        } catch (HttpClientErrorException exception) {
            throw new ResourceNotFoundException(exception.getMessage());
        }
    }
}
