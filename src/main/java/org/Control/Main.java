package org.Control;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.Control.WeatherMapProvider.fetchWeatherData;

public class Main {
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
	}
}
