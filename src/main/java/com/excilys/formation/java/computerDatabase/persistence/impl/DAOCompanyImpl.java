package com.excilys.formation.java.computerDatabase.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.java.computerDatabase.mapper.MapCompany;
import com.excilys.formation.java.computerDatabase.model.Company;
import com.excilys.formation.java.computerDatabase.persistence.DaoCompany;
import com.excilys.formation.java.computerDatabase.persistence.DatabaseConnection;

/**
 * The Class DAOCompanyImpl.
 */
public class DAOCompanyImpl implements DaoCompany {

	/**
	 * The instance.
	 */
	private static DaoCompany instance = null;

	/**
	 * The database connection.
	 */
	private DatabaseConnection databaseConnection;

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DAOCompanyImpl.class);

	/**
	 * Instantiates a new DAO company impl.
	 */
	private DAOCompanyImpl() {
		super();
		databaseConnection = DatabaseConnection.getInstance();
	}

	@Override
	public List<Company> getAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = databaseConnection.getConnection();
			statement = connection.prepareStatement("SELECT * FROM company;");
			result = statement.executeQuery();
			return MapCompany.mapCompanies(result);
		} catch (SQLException e) {
			LOGGER.error("Failed to execute the getAll Query");
		} finally {
			close(connection, statement);
		}
		return null;
	}

	@Override
	public Company getByName(String name) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result;
		try {
			connection = databaseConnection.getConnection();
			statement = connection
					.prepareStatement("SELECT * FROM company WHERE name=?;");
			statement.setString(1, name);
			result = statement.executeQuery();
			return MapCompany.mapCompany(result);
		} catch (SQLException e) {
			LOGGER.error("Failed to execute the getByName Query");
		} finally {
			close(connection, statement);
		}
		return null;
	}

	/**
	 * Close.
	 *
	 * @param connection the connection
	 * @param statement the statement
	 */
	private void close(Connection connection, PreparedStatement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.error("Failed to close statement");
			}
		}
		try {
			connection.close();
		} catch (SQLException e) {
			LOGGER.error("Failed to close connection");
		}
	}

	/**
	 * Gets the single instance of DAOCompanyImpl.
	 *
	 * @return single instance of DAOCompanyImpl
	 */
	public static synchronized DaoCompany getInstance() {
		if (instance == null) {
			instance = new DAOCompanyImpl();
		}
		return instance;
	}

}
