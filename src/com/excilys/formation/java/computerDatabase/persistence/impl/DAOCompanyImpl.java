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
	private static MapCompany mapCompany;
	private static DatabaseConnection databaseConnection;

	private DAOCompanyImpl() {
		mapCompany = MapCompany.getInstance();
	}

	@Override
	public List<Company> getAll() {
		PreparedStatement statement=null;
		ResultSet result = null;
		try {
			Connection connection = databaseConnection.open();
			statement = connection.prepareStatement("SELECT * FROM company;");
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(statement != null ) {
				try {
					statement.close();
				} catch (SQLException e) {
				}
			}
			databaseConnection.close();
		}
		return mapCompany.mapcompanies(result);
	}

	public synchronized static DaoCompany getInstance() {
		if (_instance == null) {
			_instance = new DAOCompanyImpl();
		}
		return _instance;
	}

}
