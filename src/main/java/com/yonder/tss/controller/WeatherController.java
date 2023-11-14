package com.yonder.tss.controller;

import com.yonder.tss.service.DataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/weather")
public class WeatherController {
    private DataService dataService;

    public WeatherController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping
    public String hello(@RequestParam(value = "city", required = false) List<String> cities) {
        dataService.getData("Cluj-Napocea");
        return "hi";
    }
}
