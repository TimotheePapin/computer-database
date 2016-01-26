package com.excilys.formation.java.computerDatabase.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.java.computerDatabase.exception.DatabaseException;
import com.excilys.formation.java.computerDatabase.mapper.MapCompany;
import com.excilys.formation.java.computerDatabase.model.Company;
import com.excilys.formation.java.computerDatabase.persistence.CompanyDAO;
import com.excilys.formation.java.computerDatabase.persistence.DatabaseConnection;

/**
 * The Class DAOCompanyImpl.
 */
public class CompanyDAOImpl implements CompanyDAO {

	/**
	 * The instance.
	 */
	private static CompanyDAO instance = new CompanyDAOImpl();

	/**
	 * The database connection.
	 */
	private DatabaseConnection databaseConnection;

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CompanyDAOImpl.class);

	/**
	 * Instantiates a new DAO company impl.
	 */
	private CompanyDAOImpl() {
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
			throw new DatabaseException("Failed to execute the getAll Query", e);
		} finally {
			databaseConnection.close(connection, statement);
		}
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
			throw new DatabaseException("Failed to execute the getByName Query", e);
		} finally {
			databaseConnection.close(connection, statement);
		}
	}
	
	@Override
	public void deleteById(int id, Connection connection) {
		PreparedStatement statement = null;
		try {
			statement = connection
					.prepareStatement("DELETE FROM company where id= ?;");
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to execute the deleteById Query");
			throw new DatabaseException("Failed to execute the deleteById Query", e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					LOGGER.error("Failed to close statement");
					throw new DatabaseException("Failed to close statement", e);
				}
			}
		}
	}

	/**
	 * Gets the single instance of DAOCompanyImpl.
	 *
	 * @return single instance of DAOCompanyImpl
	 */
	public static CompanyDAO getInstance() {
		return instance;
	}

}
