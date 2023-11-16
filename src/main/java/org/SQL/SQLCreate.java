package org.SQL;

import org.Model.Weather;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.Control.WeatherMapProvider.isProximaSemana;
import static org.SQL.SQLInsert.insert;

public class SQLCreate {
	public static void fetchWeatherData(double lat, double lon) {
	String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?lat=" + lat + "&lon=" + lon + "&appid=ceed62c2ca44d4b31950b46d7b614d33";
	List<Weather> weatherDataList = new ArrayList<>();
	String databasePath = "src\\main\\resources\\DataBase.db";

	SQLConnect connect = new SQLConnect();

	try (Connection conn = connect.connect(databasePath)) {
		Statement statement = conn.createStatement();
		statement.execute("CREATE TABLE IF NOT EXISTS WeatherData (" +
				"id INTEGER PRIMARY KEY AUTOINCREMENT," +
				"municipality TEXT NOT NULL, " +
				"forecastDateTime TEXT NOT NULL, " +
				"temperature REAL," +
				"description TEXT NOT NULL," +
				"nubosity REAL," +
				"windSpeed REAL" +
				")");
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
	try {
		URL url = new URL(apiUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod("GET");

		int responseCode = connection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				StringBuilder response = new StringBuilder();
				String line;

				while ((line = reader.readLine()) != null) {
					response.append(line);
				}

				JSONObject jsonResponse = new JSONObject(response.toString());

				JSONArray forecastList = jsonResponse.getJSONArray("list");
				JSONObject cityList = jsonResponse.getJSONObject("city");

				LocalDateTime currentDate = LocalDateTime.now();

				for (int i = 0; i < forecastList.length(); i++) {
					JSONObject forecast = forecastList.getJSONObject(i);
					String name = cityList.getString("name");
					String timestamp = forecast.getString("dt_txt");

					LocalDateTime forecastDateTime = LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


					if (isProximaSemana(forecastDateTime, currentDate) && forecastDateTime.getHour() == 12) {
						JSONObject main = forecast.getJSONObject("main");
						double temperature = main.getDouble("temp");

						JSONArray weatherArray = forecast.getJSONArray("weather");
						JSONObject weather = weatherArray.getJSONObject(0);
						String description = weather.getString("description");

						JSONObject clouds = forecast.getJSONObject("clouds");
						int nubosity = clouds.getInt("all");

						JSONObject wind = forecast.getJSONObject("wind");
						double windSpeed = wind.getDouble("speed");
						String date = forecastDateTime.toString();




						Weather weatherData = new Weather(name,date, temperature, description, nubosity, windSpeed);
						weatherDataList.add(weatherData);
						insert(weatherData.getIsland(), weatherData.getForecastDateTime(),weatherData.getTemperature(),weatherData.getDescription(), weatherData.getNubosity(), weatherData.getWindSpeed());




					}
				}
			}
		} else {
			System.out.println("Error getting response. Response code: " + responseCode);
		}

		connection.disconnect();
	} catch (Exception e) {
		e.printStackTrace();
	}
}

}
