package com.excilys.formation.java.computerDatabase.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
	
	private static DatabaseConnection _instance = null;
	private Connection connection;
	
	public Connection open() {
		try {
			InputStream ips = DatabaseConnection.class.getClassLoader().getResourceAsStream("properties");
			Properties prop = new Properties();
			try {
				prop.load(ips);
			} catch (IOException e) {
			} 
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = new String(prop.getProperty("url"));
			this.connection = DriverManager.getConnection(url, prop.getProperty("log"), prop.getProperty("psw"));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
		}
		return this.connection;
	}
	
	public void close() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public synchronized static DatabaseConnection getInstance() {
		if (_instance == null) {
			_instance = new DatabaseConnection();
		}
		return _instance;
	}
}
