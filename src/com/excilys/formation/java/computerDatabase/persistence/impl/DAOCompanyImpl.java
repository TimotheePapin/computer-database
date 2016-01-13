package com.excilys.formation.java.computerDatabase.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.excilys.formation.java.computerDatabase.mapper.MapCompany;
import com.excilys.formation.java.computerDatabase.model.Company;
import com.excilys.formation.java.computerDatabase.persistence.DaoCompany;
import com.excilys.formation.java.computerDatabase.persistence.DatabaseConnection;

public class DAOCompanyImpl implements DaoCompany {

	private static DaoCompany _instance = null;
	private static DatabaseConnection databaseConnection;
	private PreparedStatement statement = null;
	private ResultSet result = null;

	@Override
	public List<Company> getAll() {
		try {
			Connection connection = databaseConnection.open();
			statement = connection.prepareStatement("SELECT * FROM company;");
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
				}
			}
			databaseConnection.close();
		}
		return MapCompany.mapCompanies(result);
	}

	@Override
	public Company getByName(String name) {
		try {
			Connection connection = databaseConnection.open();
			statement = connection.prepareStatement("SELECT * FROM company WHERE name='?';");
			statement.setString(1, name);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
				}
			}
			databaseConnection.close();
		}
		return MapCompany.mapCompany(result);

	}

	public synchronized static DaoCompany getInstance() {
		if (_instance == null) {
			_instance = new DAOCompanyImpl();
		}
		return _instance;
	}

}
