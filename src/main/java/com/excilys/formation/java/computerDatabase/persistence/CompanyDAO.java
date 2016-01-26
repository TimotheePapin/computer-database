package com.excilys.formation.java.computerDatabase.persistence;

import java.sql.Connection;
import java.util.List;

import com.excilys.formation.java.computerDatabase.model.Company;

/**
 * The Interface DaoCompany.
 */
public interface CompanyDAO {

	/**
	 * Return a list of Company containing all the companies of the database.
	 *
	 * @return Company as list
	 */
	List<Company> getAll();

	/**
	 * Return the Company with this Name in the database.
	 *
	 * @param name the name
	 * @return Company
	 */
	Company getByName(String name);

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	void deleteById(int id, Connection connection);
}
