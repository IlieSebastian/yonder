package com.yonder.tss.service.impl;

import com.opencsv.CSVWriter;
import com.yonder.tss.client.Client;
import com.yonder.tss.data.ForecastData;
import com.yonder.tss.exception.ResourceNotFoundException;
import com.yonder.tss.service.DataService;
import com.yonder.tss.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.yonder.tss.constants.CSVConstants.FORECAST_CSV_HEADER;

@Service
public class ForecastDataService implements DataService<ForecastData> {
    private final Logger logger = LoggerFactory.getLogger(ForecastDataService.class);
    private final Client<ForecastData> forecastClient;
    private final List<String> validCities;
    private final String csvFileName;

    public ForecastDataService(Client<ForecastData> forecastClient,
                               @Value("${forecast.valid-cities}") List<String> validCities,
                               @Value("${forecast.csv-name}") String csvFileName) {
        this.forecastClient = forecastClient;
        this.validCities = validCities;
        this.csvFileName = csvFileName;
    }

    @Override
    public List<ForecastData> getData(List<String> cities) {
        return cities.stream()
                .filter(validCities::contains)
                .sorted(String::compareTo)
                .map(this::findCity)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public void storeData(List<ForecastData> forecastData) {
        try (CSVWriter csvWriter = FileUtils.createCSVWriter(csvFileName)) {
            csvWriter.writeNext(FORECAST_CSV_HEADER);

            forecastData.stream()
                    .map(this::convertToCSVRow)
                    .forEach(csvWriter::writeNext);
        } catch (IOException e) {
            logger.error("An error occurred while writing forecast data into csv");
        }
    }

    private ForecastData findCity(String city) {
        try {
            ForecastData data = forecastClient.fetchData(city);
            data.setName(city);
            return data;
        } catch (ResourceNotFoundException e) {
            logger.warn(String.format("Forecast data for city [%s] couldn't be found", city));
        }
        return null;
    }

    private String[] convertToCSVRow(ForecastData forecastData) {
        String[] csvRow = new String[3];
        csvRow[0] = forecastData.getName();
        csvRow[1] = forecastData.getTemperature();
        csvRow[2] = forecastData.getWind();
        return csvRow;
    }

}
