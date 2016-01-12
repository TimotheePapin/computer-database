package com.excilys.formation.java.computerDatabase.service;

import java.io.Serializable;
import java.util.List;

import com.excilys.formation.java.computerDatabase.mapper.MapCompany;
import com.excilys.formation.java.computerDatabase.model.Company;
import com.excilys.formation.java.computerDatabase.persistence.DaoCompany;
import com.excilys.formation.java.computerDatabase.persistence.impl.DAOCompany;
import com.excilys.formation.java.computerDatabase.persistence.impl.DAOCompanyImpl;

@SuppressWarnings("serial")
public class ServiceCompany implements Serializable {

	private static ServiceCompany _instance = null;
	private static DaoCompany sqlCompany;

	private ServiceCompany() {
		sqlCompany = DAOCompanyImpl.getInstance();
	}

	public void afficherCompany(Company company) {
		System.out.println(company.getId() + " - " + company.getName());
	}

	public void afficherCompanies(List<Company> companies) {
		for (Company company : companies) {
			afficherCompany(company);
		}
	}

	public List<Company> recupererCompanies() {
		return mapCompany.mapcompanies(sqlCompany.recupCompanies());
	}

	public void closeConnection() {
		sqlCompany.fermeConnection();
	}
	
	synchronized public static ServiceCompany getInstance() {
		if (_instance == null) {
			_instance = new ServiceCompany();
		}
		return _instance;
	}
}
