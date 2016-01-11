package com.excilys.formation.java.computerDatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.java.computerDatabase.model.Computer;

public class MapComputer {

	private static MapComputer _instance = null;
	private Computer computer;

	private MapComputer() {
		computer = new Computer();
	}

	public List<Computer> mapcomputers(ResultSet result) {
		List<Computer> computers = new ArrayList<Computer>();
		try {
			while (result.next()) {
				computer.setId(Integer.parseInt(result.getString("id")));
				computer.setName((result.getString("name")));
				computer.setName((result.getString("introduced")));
				computer.setName((result.getString("discontinued")));
				computer.setId(Integer.parseInt(result.getString("companyId")));
				computers.add(computer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computers;
	}

	public Computer mapcomputer(ResultSet result) {
		try {
			while (result.next()) {
				computer.setId(Integer.parseInt(result.getString("id")));
				computer.setName((result.getString("name")));
				computer.setName((result.getString("introduced")));
				computer.setName((result.getString("discontinued")));
				computer.setId(Integer.parseInt(result.getString("companyId")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computer;
	}

	synchronized public static MapComputer getInstance() {
		if (_instance == null) {
			_instance = new MapComputer();
		}
		return _instance;
	}

}