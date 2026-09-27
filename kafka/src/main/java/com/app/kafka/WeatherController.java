package com.app.kafka;

import com.app.kafka.classes.WeatherEvent;
import com.app.kafka.classes.WeatherHistory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/api/weather")
    public WeatherEvent getWeather() {
        return weatherService.getLatestWeather();
    }


    @GetMapping("/api/weather/history")
    public List<WeatherHistory> getHistory(
            @RequestParam(defaultValue = "3") int hours
    ) {
        return weatherService.getHistory(hours);
    }
}