package com.app.kafka;

import com.app.kafka.classes.WeatherEvent;
import com.app.kafka.classes.WeatherHistory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {

    private final List<WeatherEvent> weatherHistory = new ArrayList<>();
    private final WeatherHistoryRepository weatherHistoryRepository;

    public WeatherService(WeatherHistoryRepository weatherHistoryRepository) {
        this.weatherHistoryRepository = weatherHistoryRepository;
    }

    public Optional<WeatherHistory> getLatestHistory() {
        return weatherHistoryRepository.findTopByOrderByTimestampDesc();
    }

    public void updateWeather(WeatherEvent event) {
        weatherHistory.add(event);
    }

    public List<WeatherEvent> getWeatherHistory() {return weatherHistory;}

    public WeatherEvent getLatestWeather() {
        return weatherHistory.getLast();
    }

    public List<WeatherHistory> getHistory(int hours) {

        LocalDateTime start =
                LocalDateTime.now().minusHours(hours);

        return weatherHistoryRepository
                .findByTimestampGreaterThanEqualOrderByTimestampAsc(start);
    }
}