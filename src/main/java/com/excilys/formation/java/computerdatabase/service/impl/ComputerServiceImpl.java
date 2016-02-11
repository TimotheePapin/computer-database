package com.excilys.formation.java.computerdatabase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.java.computerdatabase.model.Computer;
import com.excilys.formation.java.computerdatabase.persistence.ComputerDAO;
import com.excilys.formation.java.computerdatabase.service.ComputerService;
import com.excilys.formation.java.computerdatabase.web.dto.PageProperties;

/**
 * The Class ComputerServiceImpl.
 */
@Service
public class ComputerServiceImpl implements ComputerService {

	/**
	 * The computer dao.
	 */
	@Autowired
	private ComputerDAO computerDAO;

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@Override
	public List<Computer> getAll() {
		return computerDAO.getAll();
	}

	/**
	 * Gets the page.
	 *
	 * @param prop the properties
	 * @return the page
	 */
	@Override
	public List<Computer> getPage(PageProperties prop) {
		return computerDAO.getPage(prop);
	}

	/**
	 * Gets the by name.
	 *
	 * @param name the name
	 * @return the by name
	 */
	@Override
	public Computer getByName(String name) {
		return computerDAO.getByName(name);
	}

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	@Override
	public Computer getById(int id) {
		return computerDAO.getById(id);
	}

	/**
	 * Delete by name.
	 *
	 * @param name the name
	 */
	@Override
	public void deleteByName(String name) {
		computerDAO.deleteByName(name);
	}

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	@Override
	public void deleteById(int id) {
		computerDAO.deleteById(id);
	}

	/**
	 * Creates the.
	 *
	 * @param computer the computer
	 * @return the computer
	 */
	@Override
	public Computer create(Computer computer) {
		return computerDAO.add(computer);
	}

	/**
	 * Update.
	 *
	 * @param computer the computer
	 * @return the computer
	 */
	@Override
	public Computer update(Computer computer) {
		return computerDAO.update(computer);
	}

	/**
	 * Delete by company id.
	 *
	 * @param companyId the company id
	 * @param connection the connection
	 */
	@Override
	public void deleteByCompanyId(int companyId) {
		computerDAO.deleteByCompanyId(companyId);
	}

	/**
	 * Gets the size.
	 *
	 * @param search the search
	 * @return the size
	 */
	@Override
	public int getSize(String search) {
		return computerDAO.getSize(search);
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
}
