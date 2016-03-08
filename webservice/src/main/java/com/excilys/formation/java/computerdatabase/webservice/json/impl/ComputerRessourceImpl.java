package com.excilys.formation.java.computerdatabase.webservice.json.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.java.computerdatabase.ComputerService;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;
import com.excilys.formation.java.computerdatabase.mapper.MapComputer;
import com.excilys.formation.java.computerdatabase.model.Computer;
import com.excilys.formation.java.computerdatabase.webservice.ComputerRessource;

@RestController
@RequestMapping("rest/json/computer")
public class ComputerRessourceImpl implements ComputerRessource {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ComputerRessourceImpl.class);

	@Autowired
	private ComputerService computerService;

	@Override
	@GET
	@RequestMapping("getAll")
	public List<ComputerDTO> getAll() {
		LOGGER.info("Starting getAll");
		List<ComputerDTO> computersDTO = new ArrayList<>();
		;
		for (Computer computer : computerService.getAll()) {
			computersDTO.add(MapComputer.computerToDTO(computer));
		}
		return computersDTO;
	}

	@Override
	@GET
	@RequestMapping("getById/{id}")
	public ComputerDTO getById(int id) {
		LOGGER.info("Starting getById {}", id);
		return MapComputer.computerToDTO(computerService.getById(id));
	}

	@Override
	@DELETE
	@Produces(MediaType.TEXT_HTML)
	@RequestMapping("delete/{id}")
	public Response delete(int id) {
		LOGGER.info("Starting deleteById {}", id);
		computerService.deleteById(id);
		return Response.ok("Computer deleted").build();
	}

	@Override
	@POST
	@RequestMapping("update")
	public ComputerDTO update(ComputerDTO computerDTO) {
		LOGGER.info("Starting update {}", computerDTO.toString());
		int id = computerService.update(MapComputer.dtoToComputer(computerDTO))
				.getId();
		return MapComputer.computerToDTO(computerService.getById(id));
	}

	@Override
	@POST
	@RequestMapping("create")
	public ComputerDTO create(ComputerDTO computerDTO) {
		LOGGER.info("Starting create {}", computerDTO.getName());
		int id = computerService.create(MapComputer.dtoToComputer(computerDTO))
				.getId();
		return MapComputer.computerToDTO(computerService.getById(id));
	}

}
