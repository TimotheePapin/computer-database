package com.excilys.formation.java.computerDatabase.service;

import java.io.Serializable;
import java.util.List;

import com.excilys.formation.java.computerDatabase.model.Company;
import com.excilys.formation.java.computerDatabase.persistence.DaoCompany;
import com.excilys.formation.java.computerDatabase.persistence.impl.DAOCompanyImpl;

@SuppressWarnings("serial")
public class ServiceCompany implements Serializable {

	private static ServiceCompany _instance = null;
	private DaoCompany daoCompany;

	private ServiceCompany() {
		daoCompany = DAOCompanyImpl.getInstance();
	}

	public List<Company> getAll() {
		return daoCompany.getAll();
	}

	synchronized public static ServiceCompany getInstance() {
		if (_instance == null) {
			_instance = new ServiceCompany();
		}
		return _instance;
	}

	public DaoCompany getDaoCompany() {
		return daoCompany;
	}

	public void setDaoCompany(DaoCompany daoCompany) {
		this.daoCompany = daoCompany;
	}

}
