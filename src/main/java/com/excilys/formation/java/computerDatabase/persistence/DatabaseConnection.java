/*
 * 
 */
package com.excilys.formation.java.computerDatabase.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.formation.java.computerDatabase.exception.DatabaseException;

/**
 * The Class DatabaseConnection.
 */
public class DatabaseConnection {

	/**
	 * The _instance.
	 */
	private static DatabaseConnection _instance = null;

	/**
	 * Open.
	 *
	 * @return the connection
	 */
	public Connection open() {
		try {
			InputStream ips = new FileInputStream(
					"/home/excilys/Documents/Workspace/computer-database/src/main/resources/properties");
			Properties prop = new Properties();
			try {
				prop.load(ips);
			} catch (IOException e) {
				throw new DatabaseException("Fail to load properties", e);
			}
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = new String(prop.getProperty("url"));
			return DriverManager.getConnection(url, prop.getProperty("log"), prop.getProperty("psw"));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException
				| FileNotFoundException e) {
			throw new DatabaseException("Fail to open connection", e);
		}
	}

	/**
	 * Close.
	 *
	 * @param connection the connection
	 */
	public void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new DatabaseException("Fail to close connection", e);
		}
	}

	/**
	 * Gets the single instance of DatabaseConnection.
	 *
	 * @return single instance of DatabaseConnection
	 */
	public synchronized static DatabaseConnection getInstance() {
		if (_instance == null) {
			_instance = new DatabaseConnection();
		}
		return _instance;
	}
}
