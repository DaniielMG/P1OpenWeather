package org.Control;

import org.Model.Weather;
import org.SQL.SQLConnect;
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
import java.util.ArrayList;
import java.util.List;

public class WeatherMapProvider {

	List<String> Location;

	public static void insert(String island, String forecastDateTime,double temperatura, String descripcion, double nubosidad, double velocidadViento) {
		String sql = "INSERT INTO WeatherData (island,forecastDateTime, temperatura, descripcion, nubosidad, velocidadViento) " +
				"VALUES (?, ?, ?, ?, ?, ?)";
		String dbPath = "src\\main\\resources\\DataBase.db";
		SQLConnect connect = new SQLConnect();
		try (Connection conn = connect.connect(dbPath);
			 PreparedStatement pstmt =  conn.prepareStatement(sql)) {
			pstmt.setString(1, island);
			pstmt.setString(2, forecastDateTime);
			pstmt.setDouble(3, temperatura);
			pstmt.setString(4, descripcion);
			pstmt.setDouble(5, nubosidad);
			pstmt.setDouble(6, velocidadViento);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public  void fetchWeatherData(double lat, double lon) {
		String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?lat=" + lat + "&lon=" + lon + "&appid=ceed62c2ca44d4b31950b46d7b614d33";
		List<Weather> weatherDataList = new ArrayList<>();
		String databasePath = "src\\main\\resources\\DataBase.db";

		SQLConnect connect = new SQLConnect();

		try (Connection conn = connect.connect(databasePath)) {
			Statement statement = conn.createStatement();
			statement.execute("CREATE TABLE IF NOT EXISTS WeatherData (" +
					"id INTEGER PRIMARY KEY AUTOINCREMENT," +
					"island TEXT NOT NULL, " +
					"forecastDateTime TEXT NOT NULL, " +
					"temperatura REAL," +
					"descripcion TEXT NOT NULL," +
					"nubosidad REAL," +
					"velocidadViento REAL" +
					")");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			// Crear la conexión
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// Configurar la solicitud
			connection.setRequestMethod("GET");

			// Obtener la respuesta
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				// Leer la respuesta
				try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
					StringBuilder response = new StringBuilder();
					String line;

					while ((line = reader.readLine()) != null) {
						response.append(line);
					}

					// Procesar la respuesta como JSON
					JSONObject jsonResponse = new JSONObject(response.toString());

					// Extraer información específica
					JSONArray forecastList = jsonResponse.getJSONArray("list");
					JSONObject cityList = jsonResponse.getJSONObject("city");

					// Obtener la fecha y hora actual
					LocalDateTime currentDate = LocalDateTime.now();

					// Iterar a través de los datos de la predicción
					for (int i = 0; i < forecastList.length(); i++) {
						JSONObject forecast = forecastList.getJSONObject(i);
						String name = cityList.getString("name");
						String timestamp = forecast.getString("dt_txt");

						// Parsear la marca de tiempo a LocalDateTime
						LocalDateTime forecastDateTime = LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


						// Verificar si la predicción es para los próximos 5 días a las 12:00 PM
						if (isProximaSemana(forecastDateTime, currentDate) && forecastDateTime.getHour() == 12) {
							JSONObject main = forecast.getJSONObject("main");
							double temperatura = main.getDouble("temp");

							JSONArray weatherArray = forecast.getJSONArray("weather");
							JSONObject weather = weatherArray.getJSONObject(0);
							String descripcion = weather.getString("description");

							JSONObject clouds = forecast.getJSONObject("clouds");
							int nubosidad = clouds.getInt("all");

							JSONObject wind = forecast.getJSONObject("wind");
							double velocidadViento = wind.getDouble("speed");
							String date = forecastDateTime.toString();



							// Almacenar datos en la lista

							Weather weatherData = new Weather(name,date, temperatura, descripcion, nubosidad, velocidadViento);
							weatherDataList.add(weatherData);
							insert(weatherData.getIsland(), weatherData.getForecastDateTime(),weatherData.getTemperature(),weatherData.getDescription(), weatherData.getNubosity(), weatherData.getWindSpeed());




						}
					}
				}
			} else {
				System.out.println("Error al obtener la respuesta. Código de respuesta: " + responseCode);
			}

			// Cerrar la conexión
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean isProximaSemana(LocalDateTime fechaPrediccion, LocalDateTime fechaActual) {
		// Verificar si la predicción es dentro de los próximos 5 días y a las 12:00 PM
		return fechaPrediccion.isAfter(fechaActual) &&
				fechaPrediccion.isBefore(fechaActual.plusDays(5).withHour(12).withMinute(0).withSecond(0));
	}

}

