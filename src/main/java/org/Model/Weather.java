package org.Model;

public class Weather {
	String island;
	String forecastDateTime;
	double Temperature;
	String description;
	double nubosity;
	double WindSpeed;



	public Weather(String island, String forecastDateTime, double temperature, String description, double nubosity, double windSpeed) {
		this.island = island;
		this.forecastDateTime = forecastDateTime;
		this.Temperature = temperature;
		this.description = description;
		this.nubosity = nubosity;
		this.WindSpeed = windSpeed;
	}
	public String getIsland() {
		return island;
	}

	public void setIsland(String island) {
		this.island = island;
	}

	public String getForecastDateTime() {
		return forecastDateTime;
	}

	public void setForecastDateTime(String forecastDateTime) {
		this.forecastDateTime = forecastDateTime;
	}

	public double getTemperature() {
		return Temperature;
	}

	public void setTemperature(double temperature) {
		Temperature = temperature;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getNubosity() {
		return nubosity;
	}

	public void setNubosity(double nubosity) {
		this.nubosity = nubosity;
	}

	public double getWindSpeed() {
		return WindSpeed;
	}

	public void setWindSpeed(double windSpeed) {
		WindSpeed = windSpeed;
	}




}
