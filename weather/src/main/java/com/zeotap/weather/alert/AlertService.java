package com.zeotap.weather.alert;

import com.zeotap.weather.model.Weather;
import com.zeotap.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    @Autowired
    private WeatherRepository weatherRepository;

    public void checkForAlerts(double tempThreshold) {
        List<Weather> weatherData = weatherRepository.findAll();
        for (Weather weather : weatherData) {
            if (weather.getTemperature() > tempThreshold) {
                System.out.println("ALERT: Temperature in " + weather.getCity() + " exceeded " + tempThreshold + "°C");
            }
        }
    }
}
