package com.excilys.formation.java.computerDatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.java.computerDatabase.model.Computer;

public class MapComputer {

	private static MapComputer _instance = null;
	private Computer computer;

	private MapComputer() {
	}

	public List<Computer> mapcomputers(ResultSet result) {
		List<Computer> computers = new ArrayList<Computer>();
		@SuppressWarnings("deprecation")
		Timestamp timestamp = new Timestamp(0, 0, 0, 0, 0, 0, 0);
		try {
			while (result.next()) {
				computer = new Computer();
				computer.setId(result.getInt("id"));
				computer.setName((result.getString("name")));
				if (result.getTimestamp("introduced") == null || result.getTimestamp("introduced").equals(timestamp)) {
					computer.setIntroduced("null");
				} else {
					computer.setIntroduced((result.getTimestamp("introduced").toString()));
				}
				if (result.getTimestamp("discontinued") == null
						|| result.getTimestamp("discontinued").equals(timestamp)) {
					computer.setDiscontinued("null");
				} else {
					computer.setDiscontinued((result.getTimestamp("discontinued").toString()));
				}
				computer.setCompanyId(result.getInt("company_id"));
				computers.add(computer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computers;
	}

	public Computer mapcomputer(ResultSet result) {
		@SuppressWarnings("deprecation")
		Timestamp timestamp = new Timestamp(0, 0, 0, 0, 0, 0, 0);
		try {
			result.next();
			computer = new Computer();
			computer.setId(result.getInt("id"));
			computer.setName((result.getString("name")));
			if (result.getTimestamp("introduced") == null || result.getTimestamp("introduced").equals(timestamp)) {
				computer.setIntroduced("null");
			} else {
				computer.setIntroduced((result.getTimestamp("introduced").toString()));
			}
			if (result.getTimestamp("discontinued") == null) {
				computer.setDiscontinued("null");
			} else {
				computer.setDiscontinued((result.getTimestamp("discontinued").toString()));
			}
			computer.setCompanyId(result.getInt("company_id"));
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