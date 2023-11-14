package com.yonder.tss.controller;

import com.yonder.tss.client.Client;
import com.yonder.tss.data.ForecastData;
import com.yonder.tss.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/weather")
public class WeatherController {
    private Client<ForecastData> client;

    public WeatherController(Client<ForecastData> client) {
        this.client = client;
    }

    @GetMapping
    public String hello(@RequestParam(value = "city", required = false) List<String> cities) {
        try {
            System.out.println(client.getData());
        } catch (ResourceNotFoundException e) {
            System.out.println("hello world");
        }
        return "hi";
    }
}
