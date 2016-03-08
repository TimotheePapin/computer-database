package com.excilys.formation.java.computerdatabase.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContext;
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
			computer.setIntroduced(toDateTime(computerDTO.getIntroduced()));
			computer.setDiscontinued(toDateTime(computerDTO.getDiscontinued()));
			computer.setCompany(new Company(computerDTO.getCompanyId(), ""));
			return computer;
		} catch (NumberFormatException | DateTimeParseException e) {
			throw new MappingException(
					"Failed to convert the DTO into a Computer", e);
		}
	}

	public static ComputerDTO computerToDTO(Computer computer) {
		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setId(computer.getId());
		computerDTO.setName(computer.getName());
		DateTimeFormatter formatter;
		LocaleContext context = LocaleContextHolder.getLocaleContext();
		if (context != null
				&& new Locale("fr", "").equals(context.getLocale())) {
			formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		} else {
			formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		}
		if (computer.getIntroduced() == null) {
			computerDTO.setIntroduced(null);
		} else {
			computerDTO
					.setIntroduced(computer.getIntroduced().format(formatter));
		}
		if (computer.getDiscontinued() == null) {
			computerDTO.setDiscontinued(null);
		} else {
			computerDTO.setDiscontinued(
					computer.getDiscontinued().format(formatter));
		}
		if (computer.getCompany() != null) {
			computerDTO.setCompany(computer.getCompany().getName());
			computerDTO.setCompanyId(computer.getCompany().getId());
		}
		return computerDTO;
	}

	public static int toInteger(String strId) throws NumberFormatException {
		if (strId != null) {
			return Integer.parseInt(strId);
		}
		return 0;
	}

	public static LocalDateTime toDateTime(String strDate)
			throws DateTimeParseException {
		if (strDate != null && !strDate.trim().isEmpty()) {
			strDate += " 00:00:00";
			DateTimeFormatter formatter;
			LocaleContext context = LocaleContextHolder.getLocaleContext();
			if (context != null
					&& new Locale("fr", "").equals(context.getLocale())) {
				formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:ss",
						new Locale("fr"));
			} else {
				formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu HH:mm:ss",
						new Locale("en"));
			}
			return LocalDateTime.parse(strDate.trim(), formatter);
		}
		return null;
	}
}
