package org.SQL;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.Control.WeatherMapProvider.isProximaSemana;

public class SQLInsert {
	static void processWeatherResponse(String jsonResponse, String tableName, Connection conn) throws SQLException {
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
