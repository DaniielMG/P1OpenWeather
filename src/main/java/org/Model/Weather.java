package org.Model;

import java.time.LocalDateTime;

public class Weather {
	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(double precipitation) {
		this.precipitation = precipitation;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public double getCloudiness() {
		return cloudiness;
	}

	public void setCloudiness(double cloudiness) {
		this.cloudiness = cloudiness;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public String getDtTxt() {
		return dtTxt;
	}

	public void setDtTxt(String dtTxt) {
		this.dtTxt = dtTxt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private double temperature;
	private double precipitation;
	private double humidity;
	private double cloudiness;
	private double windSpeed;
	private String dtTxt;
	private String description;
	private String location;

	public Weather(double temperature, double precipitation, double humidity, double cloudiness, double windSpeed, String dtTxt, String description) {
		this.temperature = temperature;
		this.precipitation = precipitation;
		this.humidity = humidity;
		this.cloudiness = cloudiness;
		this.windSpeed = windSpeed;
		this.dtTxt = dtTxt;
		this.description = description;
	}

	public Weather(LocalDateTime forecastDateTime, double temperatura, String descripcion, int nubosidad, double velocidadViento) {

	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	// Agrega los métodos getters y setters para los demás atributos según sea necesario
}
