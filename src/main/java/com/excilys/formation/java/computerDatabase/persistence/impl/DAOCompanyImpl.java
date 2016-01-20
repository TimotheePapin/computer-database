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

public class DAOCompanyImpl implements DaoCompany {

	private static DaoCompany _instance = null;
	private DatabaseConnection databaseConnection;
	private static final Logger LOGGER = LoggerFactory.getLogger(DAOCompanyImpl.class);

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
			connection = databaseConnection.open();
			statement = connection.prepareStatement("SELECT * FROM company;");
			result = statement.executeQuery();
			return MapCompany.mapCompanies(result);
		} catch (SQLException e) {
			LOGGER.error("Fail to execute the getAll Query");
		} finally {
			close(connection, statement);
		}
		throw new RuntimeException();
	}

	@Override
	public Company getByName(String name) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = databaseConnection.open();
			statement = connection.prepareStatement("SELECT * FROM company WHERE name=?;");
			statement.setString(1, name);
			result = statement.executeQuery();
			return MapCompany.mapCompany(result);
		} catch (SQLException e) {
			LOGGER.error("Fail to execute the getByName Query");
		} finally {
			close(connection, statement);
		}
		return null;

	}

	private void close(Connection connection, PreparedStatement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.error("Fail to close statement");
			}
		}
		databaseConnection.close(connection);
	}

	public synchronized static DaoCompany getInstance() {
		if (_instance == null) {
			_instance = new DAOCompanyImpl();
		}
		return _instance;
	}

}
