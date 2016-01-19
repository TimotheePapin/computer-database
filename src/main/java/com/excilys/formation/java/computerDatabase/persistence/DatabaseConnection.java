package com.excilys.formation.java.computerDatabase.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.java.computerDatabase.persistence.impl.DAOCompanyImpl;

public class DatabaseConnection {

	private static DatabaseConnection _instance = null;
	final Logger logger = LoggerFactory.getLogger(DAOCompanyImpl.class);

	public Connection open() {
		try {
			InputStream ips = new FileInputStream("/home/excilys/Documents/Workspace/computer-database/src/main/resources/properties");
			Properties prop = new Properties();
			try {
				prop.load(ips);
			} catch (IOException e) {
				logger.error("Fail to load properties");
			}
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = new String(prop.getProperty("url"));
			return DriverManager.getConnection(url, prop.getProperty("log"), prop.getProperty("psw"));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | FileNotFoundException e) {
			logger.error("Fail to open connection");
		}
		return null;
	}

	public void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			logger.error("Fail to close connection");
		}
	}

	public synchronized static DatabaseConnection getInstance() {
		if (_instance == null) {
			_instance = new DatabaseConnection();
		}
		return _instance;
	}
}
