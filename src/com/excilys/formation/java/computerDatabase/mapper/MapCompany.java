package com.excilys.formation.java.computerDatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.java.computerDatabase.model.Company;

public class MapCompany {

	public static List<Company> mapCompanies(ResultSet result) {
		List<Company> companies = new ArrayList<Company>();
		try {
			while (result.next()) {
				companies.add(mapCompany(result));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return companies;
	}

	public static Company mapCompany(ResultSet result) {
		Company company = new Company();
		try {
			company.setId(result.getInt("id"));
			company.setName((result.getString("name")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}
}
