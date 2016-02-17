package com.excilys.formation.java.computerdatabase.persistence;

import java.util.List;

import com.excilys.formation.java.computerdatabase.dto.model.PageProperties;
import com.excilys.formation.java.computerdatabase.model.Computer;

/**
 * The Interface ComputerDAO.
 */
public interface ComputerDAO {

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<Computer> getAll();

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	Computer getById(int id);

	/**
	 * Gets the by name.
	 *
	 * @param name the name
	 * @return the by name
	 */
	Computer getByName(String name);

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
	 * Update.
	 *
	 * @param computer the computer
	 * @return the computer
	 */
	Computer update(Computer computer);

	/**
	 * Adds the.
	 *
	 * @param computer the computer
	 * @return the computer
	 */
	Computer add(Computer computer);

	/**
	 * Gets the page.
	 *
	 * @param prop the prop
	 * @return the page
	 */
	List<Computer> getPage(PageProperties prop);

	/**
	 * Gets the size.
	 *
	 * @param search the search
	 * @return the size
	 */
	int getSize(String search);

	/**
	 * Delete by company id.
	 *
	 * @param companyId the company id
	 * @param connection the connection
	 */
	List<Computer> getByCompanyId(int companyId);
}
