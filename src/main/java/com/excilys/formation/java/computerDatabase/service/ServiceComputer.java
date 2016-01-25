package com.excilys.formation.java.computerDatabase.service;

import java.io.Serializable;
import java.util.List;

import com.excilys.formation.java.computerDatabase.model.Computer;
import com.excilys.formation.java.computerDatabase.persistence.DaoComputer;
import com.excilys.formation.java.computerDatabase.persistence.impl.DAOComputerImpl;

/**
 * The Class ServiceComputer.
 */
@SuppressWarnings("serial")
public class ServiceComputer implements Serializable {

	/**
	 * The instance.
	 */
	private static ServiceComputer instance = null;

	/**
	 * The dao computer.
	 */
	private DaoComputer daoComputer;

	/**
	 * Instantiates a new service computer.
	 */
	private ServiceComputer() {
		daoComputer = DAOComputerImpl.getInstance();
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public List<Computer> getAll() {
		return daoComputer.getAll();
	}

	/**
	 * Gets the part.
	 *
	 * @param size the size
	 * @param min the min
	 * @param order the order
	 * @param by the by
	 * @return the part
	 */
	public List<Computer> getPart(int size, int min, String order, String by) {
		return daoComputer.getPart(size, min, order, by);
	}
	
	/**
	 * Gets the search part.
	 *
	 * @param size the size
	 * @param min the min
	 * @param search the search
	 * @param order the order
	 * @param by the by
	 * @return the search part
	 */
	public List<Computer> getSearchPart(int size, int min,String search, String order, String by) {
		return daoComputer.getSearchPart(size, min,search, order, by);
	}

	/**
	 * Gets the by name.
	 *
	 * @param name the name
	 * @return the by name
	 */
	public Computer getByName(String name) {
		return daoComputer.getByName(name);
	}

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	public Computer getById(int id) {
		return daoComputer.getById(id);
	}

	/**
	 * Delete by name.
	 *
	 * @param name the name
	 */
	public void deleteByName(String name) {
		daoComputer.deleteByName(name);
	}

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	public void deleteById(int id) {
		daoComputer.deleteById(id);
	}

	/**
	 * Creates the.
	 *
	 * @param computer the computer
	 * @return the computer
	 */
	public Computer create(Computer computer) {
		return daoComputer.add(computer);
	}

	/**
	 * Update.
	 *
	 * @param computer the computer
	 * @return the computer
	 */
	public Computer update(Computer computer) {
		return daoComputer.update(computer);
	}

	/**
	 * Gets the single instance of ServiceComputer.
	 *
	 * @return single instance of ServiceComputer
	 */
	synchronized public static ServiceComputer getInstance() {
		if (instance == null) {
			instance = new ServiceComputer();
		}
		return instance;
	}

	/**
	 * Gets the dao computer.
	 *
	 * @return the dao computer
	 */
	public DaoComputer getDaoComputer() {
		return daoComputer;
	}

	/**
	 * Sets the dao computer.
	 *
	 * @param daoComputer the new dao computer
	 */
	public void setDaoComputer(DaoComputer daoComputer) {
		this.daoComputer = daoComputer;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return daoComputer.getSize();
	}
	
	/**
	 * Gets the search size.
	 *
	 * @param search the search
	 * @return the search size
	 */
	public int getSearchSize(String search) {
		return daoComputer.getSearchSize(search);
	}
}
