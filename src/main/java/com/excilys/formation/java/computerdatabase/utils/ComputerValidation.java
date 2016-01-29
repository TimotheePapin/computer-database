package com.excilys.formation.java.computerdatabase.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import com.excilys.formation.java.computerdatabase.exception.ValidationException;
import com.excilys.formation.java.computerdatabase.model.Company;
import com.excilys.formation.java.computerdatabase.model.Computer;

/**
 * The Interface ComputerValidation.
 */
public interface ComputerValidation {

	/**
	 * Validation.
	 *
	 * @param strId the str id
	 * @param name the name
	 * @param strIntroduced the str introduced
	 * @param strDiscontinued the str discontinued
	 * @param strCompanyId the str company id
	 * @return the computer
	 */
	public static Computer validation(String strId, String name,
			String strIntroduced, String strDiscontinued, String strCompanyId) {
		int id = 0, companyId = 0;
		LocalDateTime introduced = null, discontinued = null;
		id = toInteger(strId, "Computer");
		if (name == null || name.trim().isEmpty()) {
			throw new ValidationException("Computer's Name missing");
		}
		introduced = toDateTime(strIntroduced, "introduced");
		discontinued = toDateTime(strDiscontinued, "discontinued");
		companyId = toInteger(strCompanyId, "Company");
		return new Computer(id, name, introduced, discontinued,
				new Company(companyId, ""));
	}

	/**
	 * To integer.
	 *
	 * @param strId the str id
	 * @param msg the msg
	 * @return the int
	 */
	public static int toInteger(String strId, String msg) {
		if (strId != null) {
			try {
				return Integer.parseInt(strId);
			} catch (NumberFormatException e) {
				throw new ValidationException("Incorrect " + msg + " Id", e);
			}
		}
		return 0;
	}

	/**
	 * To date time.
	 *
	 * @param strDate the str date
	 * @param msg the msg
	 * @return the local date time
	 */
	public static LocalDateTime toDateTime(String strDate, String msg) {
		if (strDate != null && !strDate.isEmpty()) {
			try {
				strDate += " 00:00:00";
				DateTimeFormatter formatter = DateTimeFormatter
						.ofPattern("dd/MM/uuuu HH:mm:ss", new Locale("fr"));
				return LocalDateTime.parse(strDate.trim(), formatter);
			} catch (DateTimeParseException e) {
				throw new ValidationException("Incorrect " + msg + " Date", e);
			}
		}
		return null;
	}

}
