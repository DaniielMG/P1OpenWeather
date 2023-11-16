package org.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDatabaseCreator {

	public static final String DATABASE_URL = "jdbc:sqlite:C:\\Users\\danie\\Desktop\\CLASE!\\2023 - 2024\\DACD\\Práctica\\P1\\P1_WEATHER\\src\\main\\resources";
	public static final String databasePath = "\\DataBase.db"; // Agregué la definición de databasePath

	public static void createDatabaseAndTable() {
		String jdbcUrl = DATABASE_URL + databasePath;

		try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
			try (Statement statement = connection.createStatement()) {
				String createTableQuery = "CREATE TABLE IF NOT EXISTS WeatherData (" +
						"id INTEGER PRIMARY KEY AUTOINCREMENT," +
						"island TEXT," +
						"latitude REAL," +
						"date_time TEXT," +
						"cloudiness REAL," +
						"humidity REAL," +
						"temperature REAL,"+
						"precipitation REAL," +
						"location REAL" +
						")";
				statement.executeUpdate(createTableQuery);

				System.out.println("Table WeatherData created.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
