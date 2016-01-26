package com.excilys.formation.java.computerDatabase.service;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import com.excilys.formation.java.computerDatabase.enumeration.By;
import com.excilys.formation.java.computerDatabase.enumeration.Order;
import com.excilys.formation.java.computerDatabase.model.Computer;
import com.excilys.formation.java.computerDatabase.persistence.ComputerDAO;
import com.excilys.formation.java.computerDatabase.persistence.impl.ComputerDAOImpl;

/**
 * The Class ServiceComputer.
 */
@SuppressWarnings("serial")
public class ServiceComputer implements Serializable {

	/**
	 * The instance.
	 */
	private static ServiceComputer instance = new ServiceComputer();;

	/**
	 * The dao computer.
	 */
	private ComputerDAO computerDAO;

	/**
	 * Instantiates a new service computer.
	 */
	private ServiceComputer() {
		computerDAO = ComputerDAOImpl.getInstance();
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public List<Computer> getAll() {
		return computerDAO.getAll();
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
	public List<Computer> getPart(int size, int min, Order order, By by) {
		return computerDAO.getPart(size, min, order, by);
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
	public List<Computer> getSearchPart(int size, int min,String search, Order order, By by) {
		return computerDAO.getSearchPart(size, min,search, order, by);
	}

	/**
	 * Gets the by name.
	 *
	 * @param name the name
	 * @return the by name
	 */
	public Computer getByName(String name) {
		return computerDAO.getByName(name);
	}

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	public Computer getById(int id) {
		return computerDAO.getById(id);
	}

	/**
	 * Delete by name.
	 *
	 * @param name the name
	 */
	public void deleteByName(String name) {
		computerDAO.deleteByName(name);
	}

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	public void deleteById(int id) {
		computerDAO.deleteById(id);
	}

	/**
	 * Creates the.
	 *
	 * @param computer the computer
	 * @return the computer
	 */
	public Computer create(Computer computer) {
		return computerDAO.add(computer);
	}

	/**
	 * Update.
	 *
	 * @param computer the computer
	 * @return the computer
	 */
	public Computer update(Computer computer) {
		return computerDAO.update(computer);
	}
	
	/**
	 * Delete by company id.
	 *
	 * @param companyId the company id
	 */
	public void deleteByCompanyId(int companyId, Connection connection) {
		computerDAO.deleteByCompanyId(companyId, connection);
	}

	/**
	 * Gets the single instance of ServiceComputer.
	 *
	 * @return single instance of ServiceComputer
	 */
	public static ServiceComputer getInstance() {
		return instance;
	}

	/**
	 * Gets the dao computer.
	 *
	 * @return the dao computer
	 */
	public ComputerDAO getDaoComputer() {
		return computerDAO;
	}

	/**
	 * Sets the dao computer.
	 *
	 * @param daoComputer the new dao computer
	 */
	public void setDaoComputer(ComputerDAO daoComputer) {
		this.computerDAO = daoComputer;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return computerDAO.getSize();
	}
	
	/**
	 * Gets the search size.
	 *
	 * @param search the search
	 * @return the search size
	 */
	public int getSearchSize(String search) {
		return computerDAO.getSearchSize(search);
	}
}
