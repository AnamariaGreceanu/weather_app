package com.app.kafka;

import com.app.kafka.classes.WeatherEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WeatherConsumer {
    private final WeatherService weatherService;
    private final SimpMessagingTemplate messagingTemplate;

    public WeatherConsumer(
            WeatherService weatherService,
            SimpMessagingTemplate messagingTemplate) {

        this.weatherService = weatherService;
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topics = "weather", groupId = "weather-consumer")
    public void consume(WeatherEvent event) {
        System.out.println("Received: " + event);

        weatherService.updateWeather(event);
        System.out.println("Sending WebSocket: " + event);

//        Trimite acest event tuturor clienților conectați la /topic/weather.
        messagingTemplate.convertAndSend("/topic/weather", event);
    }
}