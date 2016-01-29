package com.excilys.formation.java.computerdatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.java.computerdatabase.exception.MappingException;
import com.excilys.formation.java.computerdatabase.model.Company;

/**
 * The Class MapCompany.
 */
public class MapCompany {

	/**
	 * Map companies.
	 *
	 * @param result the result
	 * @return the list
	 */
	public static List<Company> mapCompanies(ResultSet result) {
		List<Company> companies = new ArrayList<Company>();
		try {
			while (result.next()) {
				companies.add(mapCompany(result));
			}
		} catch (SQLException e) {
			throw new MappingException("Failed to Map Companies", e);
		}
		return companies;
	}

	/**
	 * Map company.
	 *
	 * @param result the result
	 * @return the company
	 */
	public static Company mapCompany(ResultSet result) {
		Company company = new Company();
		try {
			company.setId(result.getInt("id"));
			company.setName((result.getString("name")));
		} catch (SQLException e) {
			throw new MappingException("Failed to Map a Company", e);
		}
		return company;
	}
}
