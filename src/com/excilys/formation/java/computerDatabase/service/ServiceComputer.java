package com.excilys.formation.java.computerDatabase.service;

import java.io.Serializable;
import java.util.List;

import com.excilys.formation.java.computerDatabase.model.Computer;
import com.excilys.formation.java.computerDatabase.persistence.impl.DAOComputerImpl;

@SuppressWarnings("serial")
public class ServiceComputer implements Serializable {

	private static ServiceComputer _instance = null;
	private static DAOComputerImpl daoComputer;

	private ServiceComputer() {
		daoComputer = DAOComputerImpl.getInstance();
	}

	public List<Computer> getAll() {
		List<Computer> computers = daoComputer.getAll();
		for (Computer computer : computers) {
			System.out.println(computer);
		}
		return computers;
	}

	public Computer getByName(String name) {
		Computer computer = daoComputer.getByName(name);
		System.out.println(computer);
		return computer;
	}

	public Computer getById(int id) {
		Computer computer = daoComputer.getById(id);
		System.out.println(computer);
		return computer;
	}

	public void deleteByName(String name) {
		daoComputer.deleteByName(name);
	}

	public void deleteById(int id) {
		daoComputer.deleteById(id);
	}

	public void create(Computer computer) {
		daoComputer.add(computer);
	}

	public void update(Computer computer) {
		daoComputer.update(computer);
	}

	synchronized public static ServiceComputer getInstance() {
		if (_instance == null) {
			_instance = new ServiceComputer();
		}
		return _instance;
	}
}
