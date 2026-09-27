package com.app.kafka;

import com.app.kafka.classes.WeatherHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface WeatherHistoryRepository
        extends JpaRepository<WeatherHistory, Long> {
    Optional<WeatherHistory> findTopByOrderByTimestampDesc();
//     metoda pentru a lua istoricul începând de la o anumită oră:
    List<WeatherHistory> findByTimestampGreaterThanEqualOrderByTimestampAsc(
            LocalDateTime timestamp
    );
}