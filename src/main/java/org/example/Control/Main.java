package org.example.Control;

public class Main {

	public static void main(String[] args) {
		// Define la ruta completa de la base de datos (sin la extensión .db)
		String databasePath = "C:/Users/danie/Desktop/CLASE!/2023 - 2024/DACD/Práctica/P1/P1_WEATHER/src/main/resources/mydatabase.db";

		// Llama al método createDatabaseAndTable para crear la base de datos y la tabla
		SQLiteDatabaseCreator.createDatabaseAndTable(databasePath);


	}
}
