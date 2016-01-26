package com.excilys.formation.java.computerDatabase.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.java.computerDatabase.exception.DatabaseException;
import com.excilys.formation.java.computerDatabase.persistence.impl.CompanyDAOImpl;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

/**
 * The Class DatabaseConnection.
 */
public class DatabaseConnection {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CompanyDAOImpl.class);

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
	
	private static final int MINCONNECTION;
	
	private static final int MAXCONNECTION;
	
	private static final int PARTITIONS;

	static {
		InputStream ips = null;
		try {
			Properties prop = new Properties();
			ips = DatabaseConnection.class.getClassLoader()
					.getResourceAsStream("sql.properties");
			try {
				prop.load(ips);
			} catch (IOException e) {
				LOGGER.error("Couldn't reach properties");
				throw new DatabaseException("Couldn't reach properties", e);
			}
			Class.forName(prop.getProperty("driver")).newInstance();
			URL = new String(prop.getProperty("url"));
			LOG = new String(prop.getProperty("log"));
			PSW = new String(prop.getProperty("psw"));
			MINCONNECTION = Integer.parseInt(prop.getProperty("minconnections"));
			MAXCONNECTION = Integer.parseInt(prop.getProperty("maxconnections"));
			PARTITIONS = Integer.parseInt(prop.getProperty("partitions"));
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			LOGGER.error("Failed to load properties");
			throw new DatabaseException("Failed to load properties", e);
		} finally {
			try {
				ips.close();
			} catch (IOException e) {
				LOGGER.error("Failed to close the Stream");
				throw new DatabaseException("Failed to close the Stream", e);
			}
		}
	}
	
	/**
	 * The instance.
	 */
	private static DatabaseConnection instance = new DatabaseConnection();

	/**
	 * Instantiates a new database connection.
	 */
	private DatabaseConnection() {
		try {
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl(URL);
			config.setUsername(LOG);
			config.setPassword(PSW);
			config.setMinConnectionsPerPartition(MINCONNECTION);
			config.setMaxConnectionsPerPartition(MAXCONNECTION);
			config.setPartitionCount(PARTITIONS);
			connectionPool = new BoneCP(config);
		} catch (SQLException e) {
			LOGGER.error("Configuration error of the connection pool.");
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
	 * Close.
	 *
	 * @param connection the connection
	 * @param statement the statement
	 */
	public void close(Connection connection, PreparedStatement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.error("Failed to close statement");
				throw new DatabaseException("Failed to close statement", e);
			}
		}
		try {
			connection.close();
		} catch (SQLException e) {
			LOGGER.error("Failed to close connection");
			throw new DatabaseException("Failed to close connection", e);
		}
	}

	/**
	 * Gets the single instance of DatabaseConnection.
	 *
	 * @return single instance of DatabaseConnection
	 */
	public static DatabaseConnection getInstance() {
		return instance;
	}
}
