package org.example.Model;

public class LocationInfo {


	public class Location {



		private String Name;
		private double Lati;
		private double Longi;

		public Location(String Name, double lati, double longi) {
			Name = Name;
			Lati = lati;
			Longi = longi;
		}

		public void setName(String name) {
			Name = Name;
		}

		public void setLati(double lati) {
			Lati = lati;
		}

		public void setLongi(double longi) {
			Longi = longi;
		}

		public double getLati() {

			return Lati;
		}

		public String getName() {
			return Name;
		}

		public double getLongi() {

			return Longi;
		}
	}


}
