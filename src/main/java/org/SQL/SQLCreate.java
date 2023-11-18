package org.SQL;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.Control.WeatherMapProvider.isProximaSemana;

public class SQLCreate {
	public static void fetchWeatherData(double lat, double lon, String locationName) {
		String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?lat=" + lat + "&lon=" + lon + "&appid=ceed62c2ca44d4b31950b46d7b614d33";
		String databasePath = "src\\main\\resources\\DataBase.db";

		SQLConnect connect = new SQLConnect();
		String tableName = "WeatherData_" + locationName.replaceAll("\\s+", "_").toLowerCase();

		try (Connection conn = connect.connect(databasePath)) {
			// Crear tabla para la ubicación específica
			try (Statement statement = conn.createStatement()) {
				String createTableQuery = String.format(
						"CREATE TABLE IF NOT EXISTS %s (" +
								"id INTEGER PRIMARY KEY AUTOINCREMENT," +
								"forecastDateTime TEXT NOT NULL, " +
								"temperature REAL," +
								"description TEXT NOT NULL," +
								"nubosity REAL," +
								"windSpeed REAL" +
								")", tableName);
				statement.execute(createTableQuery);
			}

			// Obtener datos del clima y almacenarlos en la base de datos
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
					processWeatherResponse(response.toString(), tableName, conn);
				}
			} else {
				System.out.println("Error getting response. Response code: " + responseCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void processWeatherResponse(String jsonResponse, String tableName, Connection conn) throws SQLException {
		JSONObject json = new JSONObject(jsonResponse);
		JSONArray forecastList = json.getJSONArray("list");
		JSONObject cityList = json.getJSONObject("city");
		LocalDateTime currentDate = LocalDateTime.now();
		String insertQuery = String.format("INSERT INTO %s (forecastDateTime, temperature, description, nubosity, windSpeed) VALUES (?, ?, ?, ?, ?)", tableName);

		for (int i = 0; i < forecastList.length(); i++) {
			JSONObject forecast = forecastList.getJSONObject(i);
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

				// Insertar datos en la tabla
				try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
					pstmt.setString(1, timestamp);
					pstmt.setDouble(2, temperature);
					pstmt.setString(3, description);
					pstmt.setDouble(4, nubosity);
					pstmt.setDouble(5, windSpeed);
					pstmt.executeUpdate();
				}
			}
		}
	}
}
