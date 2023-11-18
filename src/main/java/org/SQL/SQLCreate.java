package org.SQL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;

import static org.SQL.SQLInsert.processWeatherResponse;

public class SQLCreate {
	public static void fetchWeatherData(double lat, double lon, String locationName) {
		String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?lat=" + lat + "&lon=" + lon + "&appid=ceed62c2ca44d4b31950b46d7b614d33";
		String databasePath = "src\\main\\resources\\DataBase.db";

		SQLConnect connect = new SQLConnect();
		String tableName = "WeatherData_" + locationName.replaceAll("\\s+", "_").toLowerCase();

		try (Connection conn = connect.connect(databasePath)) {
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
}