package com.excilys.formation.java.computerDatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.java.computerDatabase.model.Company;

public class MapCompany {

	private static MapCompany _instance = null;
	private Company company;

	private MapCompany() {
		company = new Company();
	}

	public List<Company> mapcompanies(ResultSet result) {
		List<Company> companies = new ArrayList<Company>();
		try {
			while (result.next()) {
				company.setId(Integer.parseInt(result.getString("id")));
				company.setName((result.getString("name")));
				companies.add(company);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return companies;
	}

	synchronized public static MapCompany getInstance() {
		if (_instance == null) {
			_instance = new MapCompany();
		}
		return _instance;
	}

}
