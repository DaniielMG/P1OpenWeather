package org.example.Model;

public class Weather {
	public static class WeatherData {


		private double temperature;
		private double precipitation;
		private double humidity;
		private double cloudiness;
		private double windSpeed;


		public WeatherData(double temperature, double precipitation, double humidity, double cloudiness, double windSpeed, String Name) {
			this.temperature = temperature;
			this.precipitation = precipitation;
			this.humidity = humidity;
			this.cloudiness = cloudiness;
			this.windSpeed = windSpeed;
		}

		public WeatherData(String timestamp, double temperatura, String descripcion, int nubosidad, double velocidadViento) {
		}

		public double getTemperature() {

			return temperature;
		}

		public double getPrecipitation() {

			return precipitation;
		}

		public double getHumidity() {

			return humidity;
		}

		public double getCloudiness() {

			return cloudiness;
		}

		public double getWindSpeed() {

			return windSpeed;
		}

		public void setTemperature(double temperature) {

			this.temperature = temperature;
		}

		public void setPrecipitation(double precipitation) {

			this.precipitation = precipitation;
		}

		public void setHumidity(double humidity) {

			this.humidity = humidity;
		}

		public void setCloudiness(double cloudiness) {

			this.cloudiness = cloudiness;
		}

		public void setWindSpeed(double windSpeed) {
			this.windSpeed = windSpeed;
		}
	}

}
