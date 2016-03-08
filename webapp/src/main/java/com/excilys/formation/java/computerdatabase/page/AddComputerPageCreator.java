package com.excilys.formation.java.computerdatabase.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.java.computerdatabase.ComputerService;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;
import com.excilys.formation.java.computerdatabase.exception.MappingException;
import com.excilys.formation.java.computerdatabase.mapper.MapComputer;
import com.excilys.formation.java.computerdatabase.model.Computer;

@Service
public class AddComputerPageCreator {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AddComputerPageCreator.class);

	@Autowired
	private ComputerService computerService;

	public String postRequest(ComputerDTO computerDTO) {
		Computer computer = new Computer();

		try {
			computer = MapComputer.dtoToComputer(computerDTO);
			computerService.create(computer);
			return "redirect:/dashboard?search="
					+ (computer.getName().replace(" ", "+"));
		} catch (MappingException e) {
			LOGGER.error(
					"\n" + e.getMessage() + "\nFailed to Add the Computer;");
			return "addComputer";
		}
	}
}
