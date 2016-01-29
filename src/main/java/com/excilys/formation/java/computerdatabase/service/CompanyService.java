package com.excilys.formation.java.computerdatabase.service;

import java.util.List;

import com.excilys.formation.java.computerdatabase.model.Company;

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

}
