package org.example.Control;

public class WeatherController {
	private String location;
	private int days;
	private WeatherProvider weatherProvider;
	private WeatherStore weatherStore;

	public WeatherController(String location, int days, WeatherProvider weatherProvider, WeatherStore weatherStore) {
		this.location = location;
		this.days = days;
		this.weatherProvider = weatherProvider;
		this.weatherStore = weatherStore;
	}

	public void execute() {
	}
}