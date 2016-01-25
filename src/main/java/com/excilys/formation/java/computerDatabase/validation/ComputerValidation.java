package com.excilys.formation.java.computerDatabase.validation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.excilys.formation.java.computerDatabase.exception.ValidationException;
import com.excilys.formation.java.computerDatabase.model.Company;
import com.excilys.formation.java.computerDatabase.model.Computer;

/**
 * The Class ComputerValidation.
 */
public class ComputerValidation {

	/**
	 * Instantiates a new computer validation.
	 */
	public ComputerValidation() {
		super();
	}

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
	public static Computer Validation(String strId, String name, String strIntroduced,
			String strDiscontinued, String strCompanyId) {
		int id = 0, companyId = 0;
		LocalDateTime introduced = null, discontinued = null;
		if (strId != null) {
			try {
				id = Integer.parseInt(strId);
			} catch (Exception e) {
				throw new ValidationException("Incorrect Computer Id", e);
			}
		}
		if (name == null || name.isEmpty()) {
			throw new ValidationException("Computer's Name missing");
		}
		if (!(strIntroduced == null || strIntroduced.isEmpty())) {
			try {
				strIntroduced += " 00:00:00";
				DateTimeFormatter formatter =DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:ss",
						new Locale("fr"));
				introduced = LocalDateTime.parse(strIntroduced.trim(), formatter);
			} catch (Exception e) {
				throw new ValidationException("Incorrect Introduced Date", e);
			}
		}
		if (!(strDiscontinued == null || strDiscontinued.isEmpty())) {
			try {
				strDiscontinued += " 00:00:00";
				DateTimeFormatter formatter = DateTimeFormatter
						.ofPattern("dd/MM/yyyy HH:mm:ss",
								new Locale("fr"));
				discontinued = LocalDateTime.parse(strDiscontinued, formatter);
			} catch (Exception e) {
				throw new ValidationException("Incorrect Discontinued Date", e);
			}
		}
		if (strCompanyId != null) {
			try {
				companyId = Integer.parseInt(strCompanyId);
			} catch (Exception e) {
				throw new ValidationException("Incorrect Company Id", e);
			}
		}
		return new Computer(id, name, introduced, discontinued,
				new Company(companyId, ""));
	}

}
