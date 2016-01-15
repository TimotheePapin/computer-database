package com.excilys.formation.java.computerDatabase.persistence;

import java.util.List;

import com.excilys.formation.java.computerDatabase.model.Computer;

public interface DaoComputer {

	/**
	 * Return a list of Computer containing all the computers of the database
	 * 
	 * @return
	 */
	List<Computer> getAll();

	/**
	 * Return the Computer with this Id in the database
	 * 
	 * @return
	 */
	Computer getById(int id);

	/**
	 * Return the Computer with this Name in the database
	 * 
	 * @return
	 */
	Computer getByName(String name);

	/**
	 * Delete the Computer with this Name in the database
	 * 
	 * @return
	 */
	void deleteByName(String name);

	/**
	 * Delete the Computer with this Id in the database
	 * 
	 * @return
	 */
	void deleteById(int id);

	/**
	 * Update the Computer with this Id in the database
	 * 
	 * @return
	 */
	Computer update(Computer computer);

	/**
	 * Add the Computer given to the database
	 * 
	 * @return
	 */
	Computer add(Computer computer);
}
