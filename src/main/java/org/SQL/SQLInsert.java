package org.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLInsert {
	public static void insert(String island, String forecastDateTime,double temperatura, String descripcion, double nubosidad, double velocidadViento) {
		String sql = "INSERT INTO WeatherData (island,forecastDateTime, temperatura, descripcion, nubosidad, velocidadViento) " +
				"VALUES (?, ?, ?, ?, ?, ?)";
		String dbPath = "src\\main\\resources\\DataBase.db";
		SQLConnect connect = new SQLConnect();
		try (Connection conn = connect.connect(dbPath);
			 PreparedStatement pstmt =  conn.prepareStatement(sql)) {
			pstmt.setString(1, island);
			pstmt.setString(2, forecastDateTime);
			pstmt.setDouble(3, temperatura);
			pstmt.setString(4, descripcion);
			pstmt.setDouble(5, nubosidad);
			pstmt.setDouble(6, velocidadViento);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
