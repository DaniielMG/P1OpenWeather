package org.Control;

import java.time.LocalDateTime;

public class WeatherMapProvider {

	public static boolean isProximaSemana(LocalDateTime datePrediction, LocalDateTime actualDate) {
		return datePrediction.isAfter(actualDate) &&
				datePrediction.isBefore(actualDate.plusDays(5).withHour(12).withMinute(0).withSecond(0));
	}

}

