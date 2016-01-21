package com.excilys.formation.java.computerDatabase.persistence;

import java.util.List;

import com.excilys.formation.java.computerDatabase.model.Computer;

/**
 * The Interface DaoComputer.
 */
public interface DaoComputer {

	/**
	 * Return a list of Computer containing all the computers of the database.
	 *
	 * @return list of Computer
	 */
	List<Computer> getAll();

	/**
	 * Return the Computer with this Id in the database.
	 *
	 * @param id
	 *            the id
	 * @return Computer
	 */
	Computer getById(int id);

	/**
	 * Return the Computer with this Name in the database.
	 *
	 * @param name
	 *            the name
	 * @return Computer
	 */
	Computer getByName(String name);

	/**
	 * Delete the Computer with this Name in the database.
	 *
	 * @param name
	 *            the name
	 */
	void deleteByName(String name);

	/**
	 * Delete the Computer with this Id in the database.
	 *
	 * @param id
	 *            the id
	 */
	void deleteById(int id);

	/**
	 * Update the Computer with this Id in the database.
	 *
	 * @param computer
	 *            the computer
	 * @return the computer
	 */
	Computer update(Computer computer);

	/**
	 * Add the Computer given to the database.
	 *
	 * @param computer
	 *            the computer
	 * @return the computer
	 */
	Computer add(Computer computer);

	/**
	 * Return a part of the computers of the database.
	 *
	 * @param min
	 *            the min
	 * @param max
	 *            the max
	 * @return the part
	 */
	List<Computer> getPart(int min, int max);

	/**
	 * Return the computer table's size.
	 *
	 * @return the size
	 */
	int getSize();
}
