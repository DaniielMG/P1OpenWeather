package org.Control;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TimerTask;

class WeatherTask extends TimerTask {
	private final WeatherMapProvider weatherMapProvider;

	public WeatherTask(WeatherMapProvider weatherMapProvider) {
		this.weatherMapProvider = weatherMapProvider;
	}

	@Override
	public void run() {
		System.out.println("Ejecutando la actualización cada 6 horas...");

		try (BufferedReader csvReader = new BufferedReader(new FileReader("C:\\Users\\danie\\Desktop\\CLASE!\\2023 - 2024\\DACD\\Práctica\\P1\\P1_WEATHER\\src\\main\\resources\\island.csv"))) {
			CSVParser csvParser = new CSVParser(csvReader, CSVFormat.DEFAULT);
			for (CSVRecord record : csvParser) {
				String locationName = record.get(0);
				double lat = Double.parseDouble(record.get(1));
				double lon = Double.parseDouble(record.get(2));

				System.out.println("Obteniendo datos para: " + locationName);
				weatherMapProvider.fetchWeatherData(lat, lon);
				System.out.println("---------------------");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}