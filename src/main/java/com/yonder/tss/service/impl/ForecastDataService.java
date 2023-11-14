package com.yonder.tss.service.impl;

import com.yonder.tss.client.Client;
import com.yonder.tss.data.ForecastData;
import com.yonder.tss.exception.ResourceNotFoundException;
import com.yonder.tss.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ForecastDataService implements DataService<ForecastData> {
    private final Logger logger = LoggerFactory.getLogger(ForecastDataService.class);
    private final Client<ForecastData> forecastClient;
    private final List<String> validCities;

    public ForecastDataService(Client<ForecastData> forecastClient, @Value("${forecast.valid-cities}") List<String> validCities) {
        this.forecastClient = forecastClient;
        this.validCities = validCities;
    }

    @Override
    public List<ForecastData> getData(List<String> cities) {
        return cities.stream()
                .filter(this::isValidCity)
                .map(this::findCity)
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(ForecastData::getName))
                .collect(Collectors.toList());

    }

    private boolean isValidCity(String city) {
        return validCities.contains(city);
    }

    private ForecastData findCity(String city) {
        try {
            ForecastData data = forecastClient.fetchData(city);
            data.setName(city);
            return data;
        } catch (ResourceNotFoundException e) {
            logger.warn(String.format("Couldn't find forecast data for city [%s]", city));
            return null;
        }
    }
}
