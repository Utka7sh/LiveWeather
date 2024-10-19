package com.zeotap.weather.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeotap.weather.model.Weather;
import com.zeotap.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    private final String API_KEY = "";
    private final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    public void fetchAndSaveWeather(String city) {
        try {
            // Construct the API URL
            String url = String.format(BASE_URL, city, API_KEY);
            RestTemplate restTemplate = new RestTemplate();

            // Log the URL being called
            System.out.println("Calling OpenWeatherMap API for city: " + city);
            System.out.println("API URL: " + url);

            // Fetch the data from the API
            String result = restTemplate.getForObject(url, String.class);
            System.out.println("Fetched weather data for " + city + ": " + result);

            // Parse the JSON response
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode weatherData = objectMapper.readTree(result);

            // OPTIONAL: Adjust this threshold if you want to re-fetch more frequently
            LocalDateTime now = LocalDateTime.now();
            List<Weather> recentWeather = weatherRepository.findByCityAndTimestampAfter(city, now.minus(1, ChronoUnit.MINUTES));

            if (recentWeather.isEmpty()) {  // Fetch new data only if no recent data exists
                // Extract relevant data from the response
                Weather weather = new Weather();
                weather.setCity(weatherData.get("name").asText());
                weather.setTemperature(weatherData.get("main").get("temp").asDouble());
                weather.setFeelsLike(weatherData.get("main").get("feels_like").asDouble());
                weather.setMainWeather(weatherData.get("weather").get(0).get("main").asText());
                weather.setTimestamp(now);

                // Save the weather data to the database
                weatherRepository.save(weather);
                System.out.println("Weather data saved for " + city);
            } else {
                System.out.println("Recent weather data for " + city + " already exists, skipping save.");
            }
        } catch (Exception e) {
            System.err.println("Error fetching or saving weather data for " + city);
            e.printStackTrace();
        }
    }
}
