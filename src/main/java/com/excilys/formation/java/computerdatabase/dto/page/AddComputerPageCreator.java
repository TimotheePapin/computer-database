package com.excilys.formation.java.computerdatabase.dto.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.java.computerdatabase.dto.model.ComputerDTO;
import com.excilys.formation.java.computerdatabase.exception.ValidationException;
import com.excilys.formation.java.computerdatabase.model.Computer;
import com.excilys.formation.java.computerdatabase.service.ComputerService;
import com.excilys.formation.java.computerdatabase.utils.ComputerValidation;

@Service
public class AddComputerPageCreator {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AddComputerPageCreator.class);
	
	@Autowired
	private ComputerService computerService;
	
	public String postRequest(ComputerDTO computerDTO) {
		Computer computer = new Computer();
		
		try {
			computer = ComputerValidation.validation(computerDTO);
			computerService.create(computer);
			return "redirect:/dashboard?search="
					+ (computer.getName().replace(" ", "+"));
		} catch (ValidationException e) {
			LOGGER.error(
					"\n" + e.getMessage() + "\nFailed to Add the Computer;");
			return "addComputer";
		}	
	}
}
