package com.excilys.formation.java.computerdatabase.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.java.computerdatabase.exception.DatabaseException;
import com.excilys.formation.java.computerdatabase.mapper.MapCompany;
import com.excilys.formation.java.computerdatabase.model.Company;
import com.excilys.formation.java.computerdatabase.persistence.CompanyDAO;
import com.excilys.formation.java.computerdatabase.persistence.DatabaseConnection;

/**
 * The Class CompanyDAOImpl.
 */
@Repository
public class CompanyDAOImpl implements CompanyDAO {
	
	private ThreadLocal<Connection> threadLocalConnection;
	
	public CompanyDAOImpl() {
		super();
		threadLocalConnection = new ThreadLocal<>();
	}

	/**
	 * The database connection.
	 */
	@Autowired
	private DatabaseConnection databaseConnection;

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CompanyDAOImpl.class);


	@Override
	public List<Company> getAll() {
		LOGGER.info("Starting Company getAll");
		try (Connection connection = databaseConnection.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM company");
				ResultSet result = statement.executeQuery()) {
			return MapCompany.mapCompanies(result);
		} catch (SQLException e) {
			LOGGER.error("Failed to execute the getAll Query");
			throw new DatabaseException("Failed to execute the getAll Query",
					e);
		}
	}

	@Override
	public Company getByName(String name) {
		LOGGER.info("Starting Company getByName");
		ResultSet result = null;
		try (Connection connection = databaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT * FROM company WHERE name=?")) {
			statement.setString(1, name);
			result = statement.executeQuery();
			return MapCompany.mapCompany(result);
		} catch (SQLException e) {
			LOGGER.error("Failed to execute the getByName Query :" + name);
			throw new DatabaseException(
					"Failed to execute the getByName Query :" + name, e);
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					LOGGER.error("Failed to close ResultSet");
					throw new DatabaseException("Failed to close ResultSet", e);
				}
			}
		}
	}

	@Override
	public void deleteById(int id) {
		LOGGER.info("Starting Company deleteById");
		try (PreparedStatement statement = threadLocalConnection.get()
				.prepareStatement("DELETE FROM company where id= ?")) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to execute the deleteById Query :" + id);
			throw new DatabaseException(
					"Failed to execute the deleteById Query :" + id, e);
		}
	}
}
