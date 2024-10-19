import React, { useState, useEffect } from 'react';
import { getCurrentWeather, getDailySummary } from '../services/weatherService';
import WeatherCard from '../components/WeatherCard';
import WeatherChart from '../components/WeatherChart';

const HomePage = () => {
    const [weatherData, setWeatherData] = useState(null);
    const [city, setCity] = useState('Delhi');
    const [summary, setSummary] = useState(null);  // Set default value as null

    useEffect(() => {
        const fetchData = async () => {
            try {
                const weatherResponse = await getCurrentWeather(city);
                setWeatherData(weatherResponse.data);

                const summaryResponse = await getDailySummary(city);  // Fetch summary for the selected city
                setSummary(summaryResponse.data);
            } catch (error) {
                console.error('Error fetching weather data:', error);
            }
        };

        fetchData();  // Call the fetchData function
    }, [city]);  // Fetch new data every time the city changes

    return (
        <div className="container mx-auto p-4">
            <h1 className="text-3xl font-bold text-center">Real-Time Weather Monitoring</h1>
            <div className="flex justify-center mt-4">
                <select
                    className="p-2 border rounded"
                    value={city}
                    onChange={(e) => setCity(e.target.value)}  // Update selected city
                >
                    <option value="Delhi">Delhi</option>
                    <option value="Mumbai">Mumbai</option>
                    <option value="Chennai">Chennai</option>
                    <option value="Bengaluru">Bengaluru</option>  {/* Corrected city name */}
                    <option value="Hyderabad">Hyderabad</option>
                </select>
            </div>

            {weatherData && (
                <WeatherCard
                    city={city}
                    weather={weatherData}
                />
            )}

            {summary ? (
                <WeatherChart summary={summary} />  // Display chart only if summary is available
            ) : (
                <p>Loading daily summary...</p>  // Show loading message if summary is not yet available
            )}
        </div>
    );
};

export default HomePage;
