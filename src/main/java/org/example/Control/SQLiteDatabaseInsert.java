package org.example.Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLiteDatabaseInsert {

	private static final String JDBC_URL_PREFIX = "jdbc:sqlite:C:\\Users\\danie\\Desktop\\CLASE!\\2023 - 2024\\DACD\\Práctica\\P1\\P1_WEATHER\\src\\main\\resources";

	public static void insertData(String databasePath, String island, double latitude, double longitude) {
		String jdbcUrl = JDBC_URL_PREFIX + databasePath;

		try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
			String insertDataQuery = "INSERT INTO WeatherData (island, latitude, longitude) VALUES (?, ?, ?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(insertDataQuery)) {
				preparedStatement.setString(1, island);
				preparedStatement.setDouble(2, latitude);
				preparedStatement.setDouble(3, longitude);

				preparedStatement.executeUpdate();

				System.out.println("Datos insertados en WeatherData.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
