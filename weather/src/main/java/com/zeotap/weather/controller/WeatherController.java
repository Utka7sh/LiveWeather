package com.zeotap.weather.controller;

import com.zeotap.weather.model.Weather;
import com.zeotap.weather.repository.WeatherRepository;
import com.zeotap.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherRepository weatherRepository;

    // API to get the current weather for a specific city
    @GetMapping("/api/weather/current")
    public Weather getCurrentWeather(@RequestParam String city) {
        return weatherRepository.findFirstByCityIgnoreCaseOrderByTimestampDesc(city)
                .orElse(null);  // Return the most recent weather data for the city or null if not found
    }

    // API to get weather data for a specific city (or all cities if no city is specified)
    @GetMapping("/api/weather/daily-summary")
    public List<Weather> getDailyWeatherSummary(@RequestParam(required = false) String city) {  // Optional city param
        if (city != null && !city.isEmpty()) {
            return weatherRepository.findByCityIgnoreCase(city);  // Fetch data for a specific city
        } else {
            return weatherRepository.findAll();  // Fetch data for all cities
        }
    }

    // Test endpoint to manually fetch weather data
    @GetMapping("/api/test-fetch")
    public String testFetch(@RequestParam String city) {
        weatherService.fetchAndSaveWeather(city);  // Use weatherService to fetch and save weather
        return "Weather fetch completed for " + city;
    }
}
