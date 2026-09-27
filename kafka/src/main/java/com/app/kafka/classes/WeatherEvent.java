package com.app.kafka.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherEvent {
    private Double temperature;
    private Double humidity;
    private Double windSpeed;
    private LocalDateTime timestamp;

}
