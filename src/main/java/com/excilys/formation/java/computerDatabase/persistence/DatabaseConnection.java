package com.excilys.formation.java.computerDatabase.persistence;

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
public class DatabaseConnection {

	/**
	 * The instance.
	 */
	private static DatabaseConnection instance = null;

	/**
	 * The connection pool.
	 */
	private BoneCP connectionPool = null;

	/**
	 * The Constant URL.
	 */
	private static final String URL;

	/**
	 * The Constant LOG.
	 */
	private static final String LOG;

	/**
	 * The Constant PSW.
	 */
	private static final String PSW;

	static {
		InputStream ips = null;
		try {
			Properties prop = new Properties();
			ips = DatabaseConnection.class.getClassLoader()
					.getResourceAsStream("sql.properties");
			try {
				prop.load(ips);
			} catch (IOException e) {
				throw new DatabaseException("Couldn't reach properties", e);
			}
			Class.forName(prop.getProperty("driver")).newInstance();
			URL = new String(prop.getProperty("url"));
			LOG = new String(prop.getProperty("log"));
			PSW = new String(prop.getProperty("psw"));
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			throw new DatabaseException("Failed to load properties", e);
		} finally {
			try {
				ips.close();
			} catch (IOException e) {
				throw new DatabaseException("Failed to close the Stream", e);
			}
		}
	}

	/**
	 * Instantiates a new database connection.
	 */
	private DatabaseConnection() {
		try {
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl(URL);
			config.setUsername(LOG);
			config.setPassword(PSW);
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
		if (instance == null) {
			instance = new DatabaseConnection();
		}
		return instance;
	}
}
