package com.excilys.formation.java.computerDatabase.service;

import java.io.Serializable;
import java.util.List;

import com.excilys.formation.java.computerDatabase.model.Computer;
import com.excilys.formation.java.computerDatabase.persistence.DaoComputer;
import com.excilys.formation.java.computerDatabase.persistence.impl.DAOComputerImpl;

@SuppressWarnings("serial")
public class ServiceComputer implements Serializable {

	private static ServiceComputer _instance = null;
	private DaoComputer daoComputer;

	private ServiceComputer() {
		daoComputer = DAOComputerImpl.getInstance();
	}

	public List<Computer> getAll() {
		return daoComputer.getAll();
	}

	public Computer getByName(String name) {
		return daoComputer.getByName(name);
	}

	public Computer getById(int id) {
		return daoComputer.getById(id);
	}

	public void deleteByName(String name) {
		daoComputer.deleteByName(name);
	}

	public void deleteById(int id) {
		daoComputer.deleteById(id);
	}

	public Computer create(Computer computer) {
		return daoComputer.add(computer);
	}

	public Computer update(Computer computer) {
		return daoComputer.update(computer);
	}

	synchronized public static ServiceComputer getInstance() {
		if (_instance == null) {
			_instance = new ServiceComputer();
		}
		return _instance;
	}	

	public DaoComputer getDaoComputer() {
		return daoComputer;
	}

	public void setDaoComputer(DaoComputer daoComputer) {
		this.daoComputer = daoComputer;
	}
}
