package com.excilys.formation.java.computerdatabase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.java.computerdatabase.dto.model.PageProperties;
import com.excilys.formation.java.computerdatabase.model.Computer;
import com.excilys.formation.java.computerdatabase.persistence.ComputerDAO;
import com.excilys.formation.java.computerdatabase.service.ComputerService;

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
	@Transactional(readOnly = true)
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
	@Transactional(readOnly = true)
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
	@Transactional(readOnly = true)
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
	@Transactional(readOnly = true)
	public Computer getById(int id) {
		return computerDAO.getById(id);
	}

	/**
	 * Delete by name.
	 *
	 * @param name the name
	 */
	@Override
	@Transactional
	public void deleteByName(String name) {
		computerDAO.deleteByName(name);
	}

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	@Override
	@Transactional
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
	@Transactional
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
	@Transactional
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
	@Transactional
	public void deleteByCompanyId(int companyId) {
		for (Computer computer : getByCompanyId(companyId)) {
			computerDAO.deleteById(computer.getId());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Computer> getByCompanyId(int companyId) {
		return computerDAO.getByCompanyId(companyId);
	}

	/**
	 * Gets the size.
	 *
	 * @param search the search
	 * @return the size
	 */
	@Override
	@Transactional(readOnly = true)
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
