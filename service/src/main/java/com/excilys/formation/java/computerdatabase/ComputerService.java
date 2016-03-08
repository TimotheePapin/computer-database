package com.excilys.formation.java.computerdatabase;

import java.util.List;

import com.excilys.formation.java.computerdatabase.properties.PageProperties;
import com.excilys.formation.java.computerdatabase.model.Computer;

/**
 * The Interface ComputerService.
 */
public interface ComputerService {

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<Computer> getAll();

	/**
	 * Gets the page.
	 *
	 * @param prop the prop
	 * @return computers
	 */
	List<Computer> getPage(PageProperties prop);

	/**
	 * Gets the by name.
	 *
	 * @param name the name
	 * @return the by name
	 */
	Computer getByName(String name);

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	Computer getById(int id);

	/**
	 * Delete by name.
	 *
	 * @param name the name
	 */
	void deleteByName(String name);

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	void deleteById(int id);

	/**
	 * Creates the.
	 *
	 * @param computer the computer
	 * @return the computer
	 */
	Computer create(Computer computer);

	/**
	 * Update.
	 *
	 * @param computer the computer
	 * @return the computer
	 */
	Computer update(Computer computer);

	/**
	 * Get by company id.
	 *
	 * @param companyId the company id
	 * @return computers
	 */
	List<Computer> getByCompanyId(int companyId);

	/**
	 * Delete by company id.
	 *
	 * @param companyId the company id
	 */
	void deleteByCompanyId(int companyId);

	/**
	 * Gets the size.
	 *
	 * @param search the search
	 * @return the size
	 */
	int getSize(String search);

}
