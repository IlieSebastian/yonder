package com.yonder.tss.service.impl;

import com.yonder.tss.client.Client;
import com.yonder.tss.data.ForecastData;
import com.yonder.tss.exception.ResourceNotFoundException;
import com.yonder.tss.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ForecastDataService implements DataService {
    private final Logger logger = LoggerFactory.getLogger(ForecastDataService.class);
    private final Client<ForecastData> forecastClient;

    public ForecastDataService(Client<ForecastData> forecastClient) {
        this.forecastClient = forecastClient;
    }

    @Override
    public <R> R getData(String resourceIdentifier) {
        try {
            ForecastData forecastData = forecastClient.fetchData(resourceIdentifier);
        } catch (ResourceNotFoundException e) {
            logger.warn(String.format("Couldn't find forecast data for city [%s]", resourceIdentifier));
        }
        return null;
    }
}
