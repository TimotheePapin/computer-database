package com.excilys.formation.java.computerdatabase;

import java.util.List;

import com.excilys.formation.java.computerdatabase.model.Company;

/**
 * The Interface CompanyDAO.
 */
public interface CompanyDAO {

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<Company> getAll();

	/**
	 * Gets the by name.
	 *
	 * @param name the name
	 * @return the by name
	 */
	Company getByName(String name);

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 * @param connection the connection
	 */
	void deleteById(int id);

	/**
	 * Adds the Company
	 *
	 * @param company the company
	 * @return the company
	 */
	Company add(Company company);
}
