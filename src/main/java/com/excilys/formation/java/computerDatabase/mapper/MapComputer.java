package com.excilys.formation.java.computerDatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.java.computerDatabase.exception.MappingException;
import com.excilys.formation.java.computerDatabase.model.Company;
import com.excilys.formation.java.computerDatabase.model.Computer;

/**
 * The Class MapComputer.
 */
public class MapComputer {

	/**
	 * Map computers.
	 *
	 * @param result the result
	 * @return the list
	 */
	public static List<Computer> mapComputers(ResultSet result) {
		List<Computer> computers = new ArrayList<Computer>();
		try {
			while (!result.isLast()) {
				computers.add(mapComputer(result));
			}
		} catch (SQLException e) {
			throw new MappingException("Failed to Map Computers", e);
		}
		return computers;
	}

	/**
	 * Map computer.
	 *
	 * @param result the result
	 * @return the computer
	 */
	public static Computer mapComputer(ResultSet result) {
		Computer computer = new Computer();
		Company company = new Company();
		try {
			result.next();
			computer.setId(result.getInt("id"));
			computer.setName((result.getString("computer.name")));
			if (result.getTimestamp("introduced") == null) {
				computer.setIntroduced(null);
			} else {
				computer.setIntroduced(
						(result.getTimestamp("introduced").toLocalDateTime()));
			}
			if (result.getTimestamp("discontinued") == null) {
				computer.setDiscontinued(null);
			} else {
				computer.setDiscontinued((result.getTimestamp("discontinued")
						.toLocalDateTime()));
			}
			company.setId(result.getInt("company.id"));
			company.setName(result.getString("company.name"));
			computer.setCompany(company);
		} catch (SQLException e) {
			throw new MappingException("Failed to Map a Computer", e);
		}
		return computer;
	}

	/**
	 * To timestamp.
	 *
	 * @param date the date
	 * @return the timestamp
	 */
	public static Timestamp toTimestamp(LocalDateTime date) {
		if (date != null) {
			return Timestamp.valueOf(date);
		}
		return null;
	}
}