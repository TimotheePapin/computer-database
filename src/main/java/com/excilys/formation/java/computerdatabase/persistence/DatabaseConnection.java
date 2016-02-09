package com.excilys.formation.java.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.java.computerdatabase.exception.DatabaseException;

/**
 * The Class DatabaseConnection.
 */
@Service
public class DatabaseConnection {

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DatabaseConnection.class);
	
	private ThreadLocal<Connection> threadLocalConnection;

	@Autowired
	private DataSource dataSource;

	/**
	 * Instantiates a new database connection.
	 */
	public DatabaseConnection() {
		super();
		LOGGER.info("Loading Driver");
		threadLocalConnection = new ThreadLocal<>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			LOGGER.error("Failed to load Driver");
			throw new DatabaseException("Failed to load Driver", e);
		}
	}

	public Connection getConnection() {
		try {
			if (threadLocalConnection.get() == null || threadLocalConnection.get().isClosed()) {
				threadLocalConnection.set(dataSource.getConnection());
			}
			return threadLocalConnection.get();
		} catch (SQLException e) {
			throw new DatabaseException("Failed to get Connection", e);
		}
	}

	public Connection startTransaction() {
		Connection connection = getConnection();
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			new DatabaseException("Failed to start Transaction", e);
		}
		return connection;
	}
	
	public void closeConnection() {
		closeConnection(getConnection());
	}

	public void commit() {
		try {
			getConnection().commit();
		} catch (SQLException e) {
			throw new DatabaseException("Failed to commit Transaction", e);
		}
	}
	
	public static void closeConnection(Connection connection) {
		try {
			if (connection != null && connection.getAutoCommit()) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new DatabaseException("Failed to close Transaction", e);
		}
	}

	public void forcedCloseConnection() {
		try {
			if (getConnection() != null) {
				getConnection().close();
			}
		} catch (SQLException e) {
			throw new DatabaseException("Failed to close Transaction", e);
		}
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void rollback() {
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			throw new DatabaseException("Failed to rollback", e);
		}
	}

}