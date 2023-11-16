package org.Control;

import java.util.Timer;

public class Main {
	public static void main(String[] args) {
		WeatherMapProvider obj1 = new WeatherMapProvider();

		long period = 6 * 60 * 60 * 1000;
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new WeatherTask(obj1), 0, period);
	}
}
