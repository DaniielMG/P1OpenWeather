# P1_WEATHER

##### This project uses Java to obtain climate data from specified locations and stores it in an SQLite database. Use the OpenWeatherMap API to get up-to-date weather information.

## System Requirements
##### ~ Java JDK 8 or higher
##### ~ Apache Maven (optional, if you decide to manage dependencies manually)

## Setting :
#### Clone the repositor
![image](https://github.com/DaniielMG/Practica1AEMET/assets/95304769/760006ba-a6a1-48b6-b854-8cec1cff5625)

### Dependencies:
#### Make sure you have the necessary dependencies. If you don't use Maven, make sure you have the necessary libraries in your project.

### API key:
#### Place your OpenWeatherMap API key in the WeatherMapProvider class. Find the apiKey variable and replace it with your own key

### CSV file:
#### Make sure you have a CSV file called island.csv in the src/main/resources path with the format:
![image](https://github.com/DaniielMG/Practica1AEMET/assets/95304769/55fa41d3-b300-4129-b5a4-e7707065f2f5)

## Use:

### Execution:
#### Runs the Main class to obtain climate data for each location specified in the CSV file.

### Results:
#### The application will run immediately and every 6 hours thereafter, fetching and storing weather data in the database.
