package com.zeotap.weather.repository;

import com.zeotap.weather.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

    // Find the most recent weather data for a specific city
    Optional<Weather> findFirstByCityIgnoreCaseOrderByTimestampDesc(String city);

    // Find all weather data for a specific city (ignoring case sensitivity)
    List<Weather> findByCityIgnoreCase(String city);

    // Find weather data by city and after a specific timestamp
    List<Weather> findByCityAndTimestampAfter(String city, LocalDateTime timestamp);  // Add this method
}