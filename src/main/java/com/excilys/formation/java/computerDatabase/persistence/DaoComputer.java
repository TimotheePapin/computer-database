package com.excilys.formation.java.computerDatabase.persistence;

import java.util.List;

import com.excilys.formation.java.computerDatabase.model.Computer;

/**
 * The Interface DaoComputer.
 */
public interface DaoComputer {

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
	 * Gets the part.
	 *
	 * @param min the min
	 * @param max the max
	 * @param order the order
	 * @param by the by
	 * @return the part
	 */
	List<Computer> getPart(int min, int max, String order, String by);
	
	/**
	 * Gets the search part.
	 *
	 * @param min the min
	 * @param max the max
	 * @param search the search
	 * @param order the order
	 * @param by the by
	 * @return the search part
	 */
	List<Computer> getSearchPart(int min, int max,String search, String order, String by);

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	int getSize();

	/**
	 * Gets the search size.
	 *
	 * @param search the search
	 * @return the search size
	 */
	int getSearchSize(String search);
}
