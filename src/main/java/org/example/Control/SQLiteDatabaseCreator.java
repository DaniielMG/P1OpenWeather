import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDatabaseCreator {

	private static final String JDBC_URL_PREFIX = "jdbc:sqlite:C:\\Users\\danie\\Desktop\\CLASE!\\2023 - 2024\\DACD\\Práctica\\P1\\P1_WEATHER\\src\\main\\resources";

	public static void createDatabaseAndTable(String databasePath) {
		String jdbcUrl = JDBC_URL_PREFIX + databasePath;

		try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
			try (Statement statement = connection.createStatement()) {
				String createTableQuery = "CREATE TABLE IF NOT EXISTS WeatherData (" +
						"id INTEGER PRIMARY KEY AUTOINCREMENT," +
						"island TEXT," +
						"latitude REAL," +
						"longitude REAL" +
						")";
				statement.executeUpdate(createTableQuery);

				System.out.println("Tabla WeatherData creada (si no existe).");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// Define el nombre de la base de datos que deseas crear
		String databaseName = "test.db";

		// Llama al método createDatabaseAndTable para crear la base de datos y la tabla
		createDatabaseAndTable(databaseName);
	}
}
