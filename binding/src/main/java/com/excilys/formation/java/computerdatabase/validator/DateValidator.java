package com.excilys.formation.java.computerdatabase.validator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.context.i18n.LocaleContextHolder;

public class DateValidator implements ConstraintValidator<Date, String> {

	@Override
	public void initialize(Date constraintAnnotation) {

	}

	@Override
	public boolean isValid(String strDate, ConstraintValidatorContext context) {
		if (strDate != null && !strDate.isEmpty()) {
			try {
				strDate += " 00:00:00";
				DateTimeFormatter formatter;
				if (LocaleContextHolder.getLocaleContext().getLocale()
						.equals(new Locale("fr", ""))) {
					formatter = DateTimeFormatter
							.ofPattern("dd/MM/uuuu HH:mm:ss", new Locale("fr"));
				} else {
					formatter = DateTimeFormatter
							.ofPattern("MM/dd/uuuu HH:mm:ss", new Locale("en"));
				}
				LocalDateTime.parse(strDate.trim(), formatter);
			} catch (DateTimeParseException e) {
				return false;
			}
		}
		return true;
	}

}
