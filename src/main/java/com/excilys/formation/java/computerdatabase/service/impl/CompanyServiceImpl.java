package com.excilys.formation.java.computerdatabase.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.java.computerdatabase.model.Company;
import com.excilys.formation.java.computerdatabase.persistence.CompanyDAO;
import com.excilys.formation.java.computerdatabase.service.CompanyService;
import com.excilys.formation.java.computerdatabase.service.ComputerService;

/**
 * The Class CompanyServiceImpl.
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
	
	/**
	 * The service computer.
	 */
	@Autowired
	private ComputerService serviceComputer;

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CompanyServiceImpl.class);

	/**
	 * The company dao.
	 */
	@Autowired
	private CompanyDAO companyDAO;

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@Override
	public List<Company> getAll() {
		return companyDAO.getAll();
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		LOGGER.info("Starting deleteById");
		serviceComputer.deleteByCompanyId(id);
		companyDAO.deleteById(id);
	}
	
	@Override
	public Company create(Company company) {
		return companyDAO.add(company);
	}
	
	@Override
	public Company getByName(String name) {
		return companyDAO.getByName(name);
	}
	
	/**
	 * Gets the dao company.
	 *
	 * @return the dao company
	 */
	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	/**
	 * Sets the dao company.
	 *
	 * @param daoCompany the new dao company
	 */
	public void setCompanyDAO(CompanyDAO daoCompany) {
		this.companyDAO = daoCompany;
	}
}
