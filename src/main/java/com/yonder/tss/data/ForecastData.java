package com.yonder.tss.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForecastData extends ForecastBasicData {
    private String description;

    private String name;

    @JsonProperty("forecast")
    private List<ForecastDailyData> dailyForecast;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ForecastDailyData> getDailyForecast() {
        return dailyForecast;
    }

    public void setDailyForecast(List<ForecastDailyData> dailyForecast) {
        this.dailyForecast = dailyForecast;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
