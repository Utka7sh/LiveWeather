import React from 'react';
import { Line } from 'react-chartjs-2';
import { Chart as ChartJS, CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend } from 'chart.js';

// Register the necessary components for Chart.js
ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend);

const WeatherChart = ({ summary }) => {
    console.log("Summary data in WeatherChart:", summary);  // Log the summary data to verify

    const data = {
        labels: summary.map((s) => new Date(s.timestamp).toLocaleDateString()),  // Ensure 'timestamp' field exists in the summary
        datasets: [
            {
                label: 'Temperature (°C)',
                data: summary.map((s) => s.temperature),
                borderColor: 'rgba(75, 192, 192, 1)',
                fill: false,
            },
            {
                label: 'Feels Like (°C)',
                data: summary.map((s) => s.feelsLike),
                borderColor: 'rgba(255, 99, 132, 1)',
                fill: false,
            }
        ]
    };

    return (
        <div className="mt-6">
            <h3 className="text-xl font-bold mb-4">Daily Weather Summary</h3>
            <Line key={JSON.stringify(data)} data={data} />  {/* Added key to force re-render */}
        </div>
    );
};

export default WeatherChart;
