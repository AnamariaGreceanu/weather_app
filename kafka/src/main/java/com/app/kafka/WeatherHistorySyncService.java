package com.app.kafka;

import com.app.kafka.classes.OpenMeteoHistoricalResponse;
import com.app.kafka.classes.WeatherHistory;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherHistorySyncService {

    private final WeatherHistoryRepository repository;
    private final RestClient restClient;

    public WeatherHistorySyncService(
            WeatherHistoryRepository repository,
            RestClient restClient
    ) {
        this.repository = repository;
        this.restClient = restClient;
    }
    @PostConstruct
    public void initialize() {
        sync();
    }
    public void sync() {

        LocalDateTime start = calculateStart();
        LocalDateTime now = LocalDateTime.now();

        long hours = Duration.between(start, now).toHours();

        if (hours <= 0) {
            System.out.println("Weather history is already up to date.");
            return;
        }

        OpenMeteoHistoricalResponse response =
                getHistoricalWeather(hours);

        List<WeatherHistory> history =
                mapToHistory(response);

        List<WeatherHistory> newRecords = history.stream()
                .filter(record ->
                        record.getTimestamp().isAfter(start)
                )
                .filter(record ->
                        !record.getTimestamp().isAfter(now)
                )
                .toList();

        if (!newRecords.isEmpty()) {
            repository.saveAll(newRecords);
        }

        System.out.println(
                "Weather history sync: "
                        + newRecords.size()
                        + " new records."
        );
    }
    private LocalDateTime calculateStart() {

        Optional<WeatherHistory> lastRecord =
                repository.findTopByOrderByTimestampDesc();

        if (lastRecord.isEmpty()) {
            return LocalDateTime.now().minusHours(48);
        }

        return lastRecord.get().getTimestamp();
    }

    private OpenMeteoHistoricalResponse getHistoricalWeather(
            long hours
    ) {

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/forecast")
                        .queryParam("latitude", 44.4268)
                        .queryParam("longitude", 26.1025)
                        .queryParam(
                                "hourly",
                                "temperature_2m,relative_humidity_2m,wind_speed_10m"
                        )
                        .queryParam("past_hours", hours)
                        .queryParam("forecast_days", 1)
                        .queryParam("timezone", "Europe/Bucharest")
                        .build())
                .retrieve()
                .body(OpenMeteoHistoricalResponse.class);
    }
    private List<WeatherHistory> mapToHistory(
            OpenMeteoHistoricalResponse response
    ) {

        List<WeatherHistory> history = new ArrayList<>();

        var hourly = response.getHourly();

        for (int i = 0; i < hourly.getTime().size(); i++) {

            WeatherHistory record = new WeatherHistory();

            record.setTimestamp(
                    LocalDateTime.parse(
                            hourly.getTime().get(i)
                    )
            );

            record.setTemperature(
                    hourly.getTemperature_2m().get(i)
            );

            record.setHumidity(
                    hourly.getRelative_humidity_2m().get(i)
            );

            record.setWindSpeed(
                    hourly.getWind_speed_10m().get(i)
            );

            history.add(record);
        }

        return history;
    }
}