package com.app.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestClient;

@SpringBootApplication
@EnableScheduling
public class KafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

	// Spring Boot picks up any NewTopic beans and hands them to a KafkaAdmin, which on startup asks the broker to create them.
	// The admin client uses an idempotent "create if not exists" call under the hood, so if greetings already exists,
	// the broker just shrugs and moves on.

	@Bean
	RestClient restClient() {
		return RestClient.builder()
				.baseUrl("https://api.open-meteo.com")
				.build();
	}

	@Bean
	NewTopic weatherTopic() {
		return TopicBuilder
				.name("weather")
				.partitions(1)
				.replicas(1)
				.build();
	}

//	@Bean
//	ApplicationRunner runner(WeatherProducer weatherProducer) {
//		return args -> weatherProducer.getWeather();
//	}

}
