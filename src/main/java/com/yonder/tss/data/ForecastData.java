package com.yonder.tss.data;

import java.util.List;

public class ForecastData extends ForecastBasicData {
    private String description;
    private List<ForecastDailyData> dailyForecast;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
