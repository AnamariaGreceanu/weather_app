package com.app.kafka.classes;

import lombok.Data;

@Data
public class OpenMeteoResponse {

    private Current current;

    @Data
    public static class Current {

        private String time;
        private Double temperature_2m;
        private Double relative_humidity_2m;
        private Double wind_speed_10m;
    }
}