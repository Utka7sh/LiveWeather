package com.zeotap.weather.scheduler;

import com.zeotap.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherScheduler {

    @Autowired
    private WeatherService weatherService;

    // List of cities to fetch weather for
    private final String[] cities = {"Delhi", "Mumbai", "Chennai", "Bengaluru", "Kolkata", "Hyderabad"};

    // Fetch weather every 1 minute (60,000 milliseconds)
    @Scheduled(fixedRate = 60000)
    public void scheduleWeatherUpdates() {
        for (String city : cities) {
            weatherService.fetchAndSaveWeather(city);
        }
    }
}
