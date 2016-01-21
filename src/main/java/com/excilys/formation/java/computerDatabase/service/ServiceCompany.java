package com.excilys.formation.java.computerDatabase.service;

import java.io.Serializable;
import java.util.List;

import com.excilys.formation.java.computerDatabase.model.Company;
import com.excilys.formation.java.computerDatabase.persistence.DaoCompany;
import com.excilys.formation.java.computerDatabase.persistence.impl.DAOCompanyImpl;

/**
 * The Class ServiceCompany.
 */
@SuppressWarnings("serial")
public class ServiceCompany implements Serializable {

	/**
	 * The instance.
	 */
	private static ServiceCompany instance = null;

	/**
	 * The dao company.
	 */
	private DaoCompany daoCompany;

	/**
	 * Instantiates a new service company.
	 */
	private ServiceCompany() {
		daoCompany = DAOCompanyImpl.getInstance();
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public List<Company> getAll() {
		return daoCompany.getAll();
	}

	/**
	 * Gets the single instance of ServiceCompany.
	 *
	 * @return single instance of ServiceCompany
	 */
	synchronized public static ServiceCompany getInstance() {
		if (instance == null) {
			instance = new ServiceCompany();
		}
		return instance;
	}

	/**
	 * Gets the dao company.
	 *
	 * @return the dao company
	 */
	public DaoCompany getDaoCompany() {
		return daoCompany;
	}

	/**
	 * Sets the dao company.
	 *
	 * @param daoCompany the new dao company
	 */
	public void setDaoCompany(DaoCompany daoCompany) {
		this.daoCompany = daoCompany;
	}

}
