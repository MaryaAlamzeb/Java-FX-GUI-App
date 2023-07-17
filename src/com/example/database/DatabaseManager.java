package com.example.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {

	private static final String PROPERTIES_FILE = "/com/example/resources/config.properties";

	private static DatabaseManager instance;

	private String dbUrl;
	private String dbUser;
	private String dbPassword;
	private Connection connection;

	private DatabaseManager() {
		loadDatabaseProperties();
		initializeConnection();
	}

	public static synchronized DatabaseManager getInstance() {
		if (instance == null) {
			instance = new DatabaseManager();
		}
		return instance;
	}

	private void loadDatabaseProperties() {
		try (InputStream input = getClass().getResourceAsStream(PROPERTIES_FILE)) {
			Properties properties = new Properties();
			properties.load(input);

			dbUrl = properties.getProperty("db.url");
			dbUser = properties.getProperty("db.user");
			dbPassword = properties.getProperty("db.password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initializeConnection() {
		try {
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveData(String name, int age) {
		String query = "INSERT INTO employees(name, age) VALUES (?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, name);
			statement.setInt(2, age);
			statement.executeUpdate();
			System.out.println("Data saved successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
