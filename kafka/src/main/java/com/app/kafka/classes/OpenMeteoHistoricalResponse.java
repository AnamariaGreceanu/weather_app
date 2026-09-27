package com.app.kafka.classes;

import lombok.Data;

import java.util.List;

@Data
public class OpenMeteoHistoricalResponse {

    private Hourly hourly;

    @Data
    public static class Hourly {
        private List<String> time;
        private List<Double> temperature_2m;
        private List<Double> relative_humidity_2m;
        private List<Double> wind_speed_10m;
    }
}