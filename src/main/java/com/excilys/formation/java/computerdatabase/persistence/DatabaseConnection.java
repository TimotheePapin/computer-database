package com.excilys.formation.java.computerdatabase.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.java.computerdatabase.exception.DatabaseException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

/**
 * The Class DatabaseConnection.
 */
public class DatabaseConnection {

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DatabaseConnection.class);

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

	/**
	 * The Constant MINCONNECTION.
	 */
	private static final int MINCONNECTION;

	/**
	 * The Constant MAXCONNECTION.
	 */
	private static final int MAXCONNECTION;

	/**
	 * The Constant PARTITIONS.
	 */
	private static final int PARTITIONS;

	static {
		LOGGER.info("Loading Properties");
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
			Class.forName(prop.getProperty("mySQLdriver")).newInstance();
			URL = new String(prop.getProperty("mySQLurl"));
			LOG = new String(prop.getProperty("mySQLlog"));
			PSW = new String(prop.getProperty("mySQLpsw"));
			MINCONNECTION = Integer
					.parseInt(prop.getProperty("boneCPminconnections"));
			MAXCONNECTION = Integer
					.parseInt(prop.getProperty("boneCPmaxconnections"));
			PARTITIONS = Integer.parseInt(prop.getProperty("boneCPpartitions"));

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
	 * The Constant INSTANCE.
	 */
	private static final DatabaseConnection INSTANCE = new DatabaseConnection();

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
			config.setCloseConnectionWatch(true);
			config.setCloseConnectionWatchTimeoutInMs(0);
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
	 * Gets the single instance of DatabaseConnection.
	 *
	 * @return single instance of DatabaseConnection
	 */
	public static DatabaseConnection getInstance() {
		return INSTANCE;
	}
}
