package com.app.kafka;

import com.app.kafka.classes.OpenMeteoResponse;
import com.app.kafka.classes.WeatherEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;

@Component
public class WeatherProducer {

    private final RestClient restClient;

    private final KafkaTemplate<String, WeatherEvent> kafkaTemplate;

    public WeatherProducer(
            RestClient restClient,
            KafkaTemplate<String, WeatherEvent> kafkaTemplate) {

        this.restClient = restClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 10000)
    public void getWeather() {
        OpenMeteoResponse response = restClient.get()
                .uri("/v1/forecast?latitude=44.4268&longitude=26.1025&current=temperature_2m,relative_humidity_2m,wind_speed_10m")
                .retrieve()
                .body(OpenMeteoResponse.class);
        OpenMeteoResponse.Current current = response.getCurrent();
        WeatherEvent event = new WeatherEvent(
                current.getTemperature_2m(),
                current.getRelative_humidity_2m(),
                current.getWind_speed_10m(),
                LocalDateTime.now()
        );
        kafkaTemplate.send("weather", event);
        System.out.println(event);
    }
}