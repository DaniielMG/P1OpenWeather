package org.Control;

import java.util.Timer;

public class Main {
	public static void main(String[] args) {
		WeatherMapProvider obj1 = new WeatherMapProvider();

		// Crear una tarea programada que se ejecute cada 6 horas (en milisegundos)
		long periodo = 6 * 60 * 60 * 1000; // 6 horas en milisegundos
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new WeatherTask(obj1), 0, periodo);
	}
}
