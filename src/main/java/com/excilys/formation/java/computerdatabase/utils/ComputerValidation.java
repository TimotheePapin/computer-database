package com.excilys.formation.java.computerdatabase.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import com.excilys.formation.java.computerdatabase.exception.ValidationException;
import com.excilys.formation.java.computerdatabase.model.Company;
import com.excilys.formation.java.computerdatabase.model.Computer;
import com.excilys.formation.java.computerdatabase.web.dto.ComputerDTO;

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
	public static Computer validation(ComputerDTO computerDTO) {
		int companyId = 0;
		LocalDateTime introduced = null, discontinued = null;
		if (computerDTO.getName() == null || computerDTO.getName().trim().isEmpty()) {
			throw new ValidationException("Computer's Name missing");
		}
		introduced = toDateTime(computerDTO.getIntroduced(), "introduced");
		discontinued = toDateTime(computerDTO.getDiscontinued(), "discontinued");
		companyId = toInteger(computerDTO.getCompany(), "Company");
		return new Computer(computerDTO.getId(), computerDTO.getName(), introduced, discontinued,
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
