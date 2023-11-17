# P1_WEATHER
![image](https://github.com/DaniielMG/Practica1AEMET/assets/95304769/566edebc-110d-406e-a850-74e4e050cb71)

### Subject : Data science application development
### Course : Second year
### Degree : Degree in engineering and data science
### School : School of engineering and mathematics
### University : ULPGC - University of las Palmas of Gran Canaria.
##### This project uses Java to obtain climate data from specified locations and stores it in an SQLite database. Use the OpenWeatherMap API to get up-to-date weather information.

## System Requirements
##### ~ Java JDK 8 or higher
![image](https://github.com/DaniielMG/Practica1AEMET/assets/95304769/aa064826-5339-489f-90e5-b9f6891674db)
##### ~ Apache Maven (optional, if you decide to manage dependencies manually)
![image](https://github.com/DaniielMG/Practica1AEMET/assets/95304769/18895728-d20c-441a-ab80-91775cbbd080)


## Setting :
#### Clone the repositor
![image](https://github.com/DaniielMG/Practica1AEMET/assets/95304769/a27e1ee4-723d-4696-9286-b3737551e331)
### Dependencies:
#### Make sure you have the necessary dependencies. If you don't use Maven, make sure you have the necessary libraries in your project.
### API key:
#### Place your OpenWeatherMap API key in the WeatherMapProvider class. Find the apiKey variable and replace it with your own key
### CSV file:
#### Make sure you have a CSV file called island.csv in the src/main/resources path with the format:
![image](https://github.com/DaniielMG/Practica1AEMET/assets/95304769/8e320d50-49eb-4e26-99a4-3da5e7b04dc2)

## Use:
### Execution:
#### Runs the Main class to obtain climate data for each location specified in the CSV file.

## Results:
#### The application will run immediately and every 6 hours thereafter, fetching and storing weather data in the database.

## Resources used:

### development environments :intelliJ IDEA Community.
### control tools version : Git.
### documentation tools : 
#### - https://chat.openai.com.
#### - https://stackoverflow.com/questions/26236028/jdbc-connect-to-sql-server
#### - https://alvinalexander.com/java/edu/pj/jdbc/jdbc0002/
#### - Notes and examples of the subject itself
#### - https://www.youtube.com/watch?v=j0UdOx1tRzg&list=PLmCsXDGbJHdiba-L72pn5-ykgeskxW3Y2
#### - https://api.openweathermap.org/data/2.5/forecast?appid={AapiKey}&lat={lat}&lon={lon}

## Diagram:
![image](https://github.com/DaniielMG/Practica1AEMET/assets/95304769/1078be83-2dc0-43b2-8c22-a7875ce9d29b)



## Author
#### Daniel Medina González.
