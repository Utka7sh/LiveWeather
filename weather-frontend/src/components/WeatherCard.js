import React from 'react';

const WeatherCard = ({ city, weather }) => {
    return (
        <div className="bg-white shadow-md rounded-lg p-6 mt-6">
            <h2 className="text-xl font-bold">{city}</h2>
            <p><strong>Temperature:</strong> {weather.temperature}°C</p>
            <p><strong>Feels Like:</strong> {weather.feelsLike}°C</p>
            <p><strong>Condition:</strong> {weather.mainWeather}</p>
            <p><strong>Updated at:</strong> {new Date(weather.timestamp).toLocaleTimeString()}</p>
        </div>
    );
};

export default WeatherCard;
