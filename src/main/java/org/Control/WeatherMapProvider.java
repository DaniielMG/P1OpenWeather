package org.Control;

import org.Model.Weather;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class WeatherMapProvider {

	public static void fetchWeatherData(double lat, double lon) {
		String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?lat=" + lat + "&lon=" + lon + "&appid=ceed62c2ca44d4b31950b46d7b614d33";
		List<Weather> weatherDataList = new ArrayList<>();


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

					// Obtener la fecha y hora actual
					LocalDateTime currentDate = LocalDateTime.now();

					// Iterar a través de los datos de la predicción
					for (int i = 0; i < forecastList.length(); i++) {
						JSONObject forecast = forecastList.getJSONObject(i);
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

							// Almacenar datos en la lista
							Weather weatherData = new Weather(forecastDateTime, temperatura, descripcion, nubosidad, velocidadViento);
							weatherDataList.add(weatherData);



						}

						for (Weather weatherData : weatherDataList) {
							// Acceder a los datos según sea necesario
							System.out.println("Fecha: " + weatherData.getDescription());
							System.out.println("Temperatura: " + weatherData.getTemperature());
							System.out.println("Descripción: " + weatherData.getDescription());
							System.out.println("Nubosidad: " + weatherData.getHumidity());
							System.out.println("Velocidad del viento: " + weatherData.getWindSpeed());
							System.out.println("--------");
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

	public static void main(String[] args) {
		// Leer el archivo CSV y procesar las ubicaciones
		try (BufferedReader csvReader = new BufferedReader(new FileReader("C:\\Users\\danie\\Desktop\\CLASE!\\2023 - 2024\\DACD\\Práctica\\P1\\P1_WEATHER\\src\\main\\resources\\island.csv"))) {
			CSVParser csvParser = new CSVParser(csvReader, CSVFormat.DEFAULT);
			for (CSVRecord record : csvParser) {
				String locationName = record.get(0);
				double lat = Double.parseDouble(record.get(1));
				double lon = Double.parseDouble(record.get(2));

				System.out.println("Obteniendo datos para: " + locationName);
				fetchWeatherData(lat, lon);
				System.out.println("---------------------");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}



		// Ahora la lista weatherDataList contiene todos los datos del clima
		// Puedes acceder a estos datos para su posterior uso



	}
}

