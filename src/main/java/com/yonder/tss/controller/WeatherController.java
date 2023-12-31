package com.yonder.tss.controller;

import com.yonder.tss.data.ForecastData;
import com.yonder.tss.service.DataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/weather")
public class WeatherController {
    private final DataService<ForecastData> dataService;

    public WeatherController(DataService<ForecastData> dataService) {
        this.dataService = dataService;
    }

    @GetMapping
    public List<ForecastData> getForecastData(@RequestParam(value = "cities", required = false) List<String> cities) {
        List<ForecastData> forecastData = dataService.getData(cities);
        dataService.storeData(forecastData);
        return forecastData;
    }


}
