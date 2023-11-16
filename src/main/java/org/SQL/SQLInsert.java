package org.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLInsert {
	public static void insert(String municipality, String forecastDateTime,double temperature, String description, double nubosity, double windSpeed) {
		String sql = "INSERT INTO WeatherData (municipality,forecastDateTime, temperature, description, nubosity, windSpeed) " +
				"VALUES (?, ?, ?, ?, ?, ?)";
		String dbPath = "src\\main\\resources\\DataBase.db";
		SQLConnect connect = new SQLConnect();
		try (Connection conn = connect.connect(dbPath);
			 PreparedStatement pstmt =  conn.prepareStatement(sql)) {
			pstmt.setString(1, municipality);
			pstmt.setString(2, forecastDateTime);
			pstmt.setDouble(3, temperature);
			pstmt.setString(4, description);
			pstmt.setDouble(5, nubosity);
			pstmt.setDouble(6, windSpeed);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
