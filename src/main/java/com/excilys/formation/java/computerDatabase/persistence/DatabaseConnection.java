package com.excilys.formation.java.computerDatabase.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.formation.java.computerDatabase.exception.DatabaseException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

/**
 * The Class DatabaseConnection.
 */
/**
 * @author excilys
 *
 */
public class DatabaseConnection {

	/**
	 * The _instance.
	 */
	private static DatabaseConnection _instance = null;

	/**
	 * The connection pool.
	 */
	private BoneCP connectionPool = null;

	/**
	 * Instantiates a new database connection.
	 */
	private DatabaseConnection() {
		String url;
		String log;
		String psw;
		try {
			InputStream ips = new FileInputStream(
					"/home/excilys/Documents/Workspace/computer-database/src/main/resources/properties");
			Properties prop = new Properties();
			try {
				prop.load(ips);
			} catch (IOException e) {
				throw new DatabaseException("Couldn't reach properties", e);
			}
			Class.forName(prop.getProperty("driver")).newInstance();
			url = new String(prop.getProperty("url"));
			log = new String(prop.getProperty("log"));
			psw = new String(prop.getProperty("psw"));
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | FileNotFoundException e) {
			throw new DatabaseException("Failed to load properties", e);
		}
		try {
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl(url);
			config.setUsername(log);
			config.setPassword(psw);
			config.setMinConnectionsPerPartition(5);
			config.setMaxConnectionsPerPartition(10);
			config.setPartitionCount(2);
			connectionPool = new BoneCP(config);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(
					"Configuration error of the connection pool.", e);
		}
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 * @throws SQLException the SQL exception
	 */
	public Connection getConnection() throws SQLException {
		return connectionPool.getConnection();
	}

	/**
	 * Gets the single instance of DatabaseConnection.
	 *
	 * @return single instance of DatabaseConnection
	 */
	public static synchronized DatabaseConnection getInstance() {
		if (_instance == null) {
			_instance = new DatabaseConnection();
		}
		return _instance;
	}
}
