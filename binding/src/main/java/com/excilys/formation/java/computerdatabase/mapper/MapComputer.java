package com.excilys.formation.java.computerdatabase.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;
import com.excilys.formation.java.computerdatabase.exception.MappingException;
import com.excilys.formation.java.computerdatabase.model.Company;
import com.excilys.formation.java.computerdatabase.model.Computer;

public interface MapComputer {
	
	public static Computer dtoToComputer(ComputerDTO computerDTO) {
		Computer computer = new Computer();
		try {
			computer.setId(computerDTO.getId());
			computer.setName(computerDTO.getName());
			computer.setIntroduced(toDateTime(computerDTO.getIntroduced(), "introduced"));
			computer.setDiscontinued(toDateTime(computerDTO.getDiscontinued(), "discontinued"));
			computer.setCompany(new Company(toInteger(computerDTO.getCompany(), "Company"),""));
			return computer;
		} catch (NumberFormatException | DateTimeParseException e) {
			throw new MappingException("Failed to convert the DTO into a Computer", e);
		}
	}
	
	public static int toInteger(String strId, String msg) throws NumberFormatException {
		if (strId != null) {
			return Integer.parseInt(strId);
		}
		return 0;
	}

	public static LocalDateTime toDateTime(String strDate, String msg) throws DateTimeParseException {
		if (strDate != null && !strDate.isEmpty()) {
			strDate += " 00:00:00";
			DateTimeFormatter formatter;
			if(LocaleContextHolder.getLocaleContext().getLocale().equals(new Locale("fr", ""))) {
				formatter = DateTimeFormatter
						.ofPattern("dd/MM/uuuu HH:mm:ss", new Locale("fr"));
			} else {
				formatter = DateTimeFormatter
						.ofPattern("MM/dd/uuuu HH:mm:ss", new Locale("en"));
			}
			return LocalDateTime.parse(strDate.trim(), formatter);
		}
		return null;
	}
}
