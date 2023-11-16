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

	public String getForecastDateTime() {
		return forecastDateTime;
	}

	public double getTemperature() {
		return Temperature;
	}

	public String getDescription() {
		return description;
	}

	public double getNubosity() {
		return nubosity;
	}

	public double getWindSpeed() {
		return WindSpeed;
	}


}
