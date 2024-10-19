# LiveWeatherApp

A real-time weather monitoring application that fetches weather data from OpenWeatherMap API, processes daily weather summaries, and triggers alerts based on user-defined thresholds. This project consists of a Spring Boot backend and a React frontend.

<br/>
<img src="https://github.com/user-attachments/assets/4190c280-2923-4137-8bc5-a0dcf5226184" height="180"><br/>
  <img src="https://github.com/user-attachments/assets/7d52ab2e-ca58-4021-8080-80dbaa9d76f6" width="350" height="180"><br/>




## Features

1. **Daily Weather Summary**:
   - Roll up weather data for each day.
   - Calculate daily aggregates:
     - Average temperature
     - Maximum temperature
     - Minimum temperature
     - Dominant weather condition
   - Store the daily summaries for further analysis.

2. **Alerting Thresholds**:
   - Define user-configurable thresholds for temperature or specific weather conditions.
   - Continuously track the latest weather data and compare it with thresholds.
   - Trigger alerts if the threshold is breached for consecutive updates.

3. **Visualizations**:
   - Display daily weather summaries, historical trends, and triggered alerts using React and Chart.js.

## Tech Stack

### Backend (Spring Boot)
- **Java**: Core programming language.
- **Spring Boot**: Used for building the RESTful API.
- **Maven**: For project build and dependency management.
- **H2 Database**: In-memory database for storing weather data and daily summaries.
- **Spring Data JPA**: For interaction with the database.
- **OpenWeatherMap API**: External API to fetch live weather data.

### Frontend (React)
- **React.js**: For building the UI.
- **Chart.js**: For visualizing weather trends.
- **Axios**: To handle API requests.
- **Tailwind CSS**: For styling the components.

## Installation

### Prerequisites
- **Java 17+**
- NPM
- **Maven**
- **OpenWeatherMap API Key**: Obtain your free API key from [OpenWeatherMap](https://openweathermap.org/).

### Backend Setup
Update Database Configuration:
In ```src/main/resources/application.properties``` , update your database credentials:
```
spring.datasource.url=jdbc:mysql://localhost:3306/weatherdb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```
* Run in terminal:<br/>
(in "weather" folder)
```
mvn clean install
```
* Run the Backend:
```
mvn spring-boot:run
```
_The backend will be accessible at_ ```[http://localhost:8080/api](http://localhost:8080/api/weather/daily-summary)```.
<br/><br/>

### Frontend setup (React)
* Navigate to ```weather-frontend```.
* Install Dependencies
```
npm install
```
* Start the Frontend
```
npm start
```
_The frontend will be accessible at_ ```http://localhost:3000```.

### API Endpoints
#### Weather API
* GET /api/weather/current?city={city}: Fetch the current weather data for a specific city.
* GET /api/weather/daily-summary?city={city}: Fetch the daily weather summary for a city.
* GET /api/weather/check-alerts?city={city}&tempThreshold={threshold}&consecutiveUpdates={n}: Check if a temperature threshold has been breached.

#### Scheduled Weather Fetching
* Weather data is automatically fetched every minute for predefined cities (Delhi, Mumbai, Chennai, Bengaluru, Kolkata, Hyderabad).

#### Visualizations
* Weather Summary: Displays daily temperature trends, including max, min, and average temperatures.
* Alert Notifications: Shows alerts when user-defined thresholds are breached.

## Contact

- [Mail](mailto:utka7sh@gmail.com)
- [LinkedIn](https://www.linkedin.com/in/utkarshupadhyays/)
- [Instagram](https://www.instagram.com/1_utkarsh_1/)
