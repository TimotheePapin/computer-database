package com.excilys.formation.java.computerdatabase.service;

import java.util.List;

import com.excilys.formation.java.computerdatabase.model.Company;
import com.excilys.formation.java.computerdatabase.persistence.CompanyDAO;

/**
 * The Interface CompanyService.
 */
public interface CompanyService {

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<Company> getAll();

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	void deleteById(int id);

	/**
	 * Creates the.
	 *
	 * @param company the company
	 */
	Company create(Company company);

	/**
	 * Gets the by name.
	 *
	 * @param name the name
	 * @return the by name
	 */
	Company getByName(String name);

	/**
	 * Gets the dao company.
	 *
	 * @return the dao company
	 */
	CompanyDAO getCompanyDAO();

	/**
	 * Sets the dao company.
	 *
	 * @param daoCompany the new dao company
	 */
	void setCompanyDAO(CompanyDAO daoCompany);
}
