package com.excilys.formation.java.computerdatabase.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.java.computerdatabase.CompanyDAO;
import com.excilys.formation.java.computerdatabase.model.Company;

/**
 * The Class CompanyDAOImpl.
 */
@Repository
@SuppressWarnings("unchecked")
public class CompanyDAOImpl implements CompanyDAO {

	@Autowired
	private SessionFactory sf;

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CompanyDAOImpl.class);

	@Override
	public List<Company> getAll() {
		LOGGER.info("Starting Company getAll");
		Session session = sf.getCurrentSession();
		return session.createCriteria(Company.class).list();
	}

	@Override
	public Company getByName(String name) {
		LOGGER.info("Starting Company getByName");
		Session session = sf.getCurrentSession();
		return (Company) session.get(Company.class, name);	
	}

	@Override
	public void deleteById(int id) {
		LOGGER.info("Starting Company deleteById");
		Session session = sf.getCurrentSession();
		Company company = (Company) session.get(Company.class, id);
		if(company != null) {
			session.delete(company);
		}
	}

	@Override
	public Company add(Company company) {
		LOGGER.info("Starting Company addCompany");
		Session session = sf.getCurrentSession();
		company.setId((int)session.save(company));
		return company;
	}
}
