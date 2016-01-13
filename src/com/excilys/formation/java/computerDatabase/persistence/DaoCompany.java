package com.excilys.formation.java.computerDatabase.persistence;

import java.util.List;

import com.excilys.formation.java.computerDatabase.model.Company;

public interface DaoCompany {
	/**
	 * Return a list of Company containing all the companies of the database
	 * @return
	 */
	List<Company> getAll();

}
