import axios from 'axios';

const API_URL = 'http://localhost:8080/api/weather';  // Backend API URL

// Fetch the current weather for a specific city
export const getCurrentWeather = async (city) => {
    return await axios.get(`${API_URL}/current`, { params: { city } });
};

// Fetch weather data for the selected city (daily summary)
export const getDailySummary = async (city) => {
    return await axios.get(`${API_URL}/daily-summary`, { params: { city } });
};

// Trigger backend to fetch and save weather data for the selected city
export const fetchWeatherForCity = async (city) => {
    try {
        return await axios.get(`${API_URL}/test-fetch`, { params: { city } });
    } catch (error) {
        console.error("Error fetching weather for city:", error);
        throw error;
    }
};
// Check for weather alerts
export const checkWeatherAlerts = async (city, tempThreshold, consecutiveUpdates) => {
    return await axios.get(`${API_URL}/check-alerts`, {
        params: { city, tempThreshold, consecutiveUpdates }
    });
};
