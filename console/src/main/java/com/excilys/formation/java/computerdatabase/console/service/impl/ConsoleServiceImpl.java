package com.excilys.formation.java.computerdatabase.console.service.impl;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.excilys.formation.java.computerdatabase.console.service.ConsoleService;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;
import com.excilys.formation.java.computerdatabase.mapper.MapComputer;
import com.excilys.formation.java.computerdatabase.model.Company;
import com.excilys.formation.java.computerdatabase.model.Computer;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

@Service
public class ConsoleServiceImpl implements ConsoleService{
	
	private static final String COMPANY = "http://localhost:8080/webservice/rest/xml/company";
	private static final String COMPUTER = "http://localhost:8080/webservice/rest/xml/computer";
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ConsoleServiceImpl.class);

	@Override
	public List<ComputerDTO> getAllComputer() {
		LOGGER.info("Starting getAllComputer");
		return Client.create()
				.resource(COMPUTER + "/getAll")
				.type(MediaType.APPLICATION_XML)
				.get( ClientResponse.class )
				.getEntity(new GenericType <List<ComputerDTO>>(){});
	}

	@Override
	public List<Company> getAllCompany() {
		LOGGER.info("Starting getAllCompany");
		return Client.create()
				.resource(COMPANY + "/getAll")
				.type(MediaType.APPLICATION_XML)
				.get( ClientResponse.class )
				.getEntity(new GenericType<List<Company>>(){});
	}

	@Override
	public ComputerDTO getComputerById(int id) {
		LOGGER.info("Starting getComputerById {}", id);
		return Client.create()
				.resource(COMPUTER + "/getById" + "/" + id)
				.get( ClientResponse.class )
				.getEntity(new GenericType<ComputerDTO>(){});
	}

	@Override
	public ComputerDTO createComputer(Computer computer) {
		LOGGER.info("Starting createComputer {}", computer.getName());
		ComputerDTO computerDTO = MapComputer.computerToDTO(computer);
		return Client.create()
				.resource(COMPUTER + "/create")
				.accept( MediaType.APPLICATION_XML )
				.type( MediaType.APPLICATION_XML )
				.entity( computerDTO )
				.post( ClientResponse.class )
				.getEntity(new GenericType<ComputerDTO>(){});
	}
	
	@Override
	public ComputerDTO updateComputer(Computer computer) {
		LOGGER.info("Starting updateComputer {}", computer.getName());
		ComputerDTO computerDTO = MapComputer.computerToDTO(computer);
		return  Client.create()
			.resource(COMPUTER + "/update")
			.accept( MediaType.APPLICATION_XML )
			.type( MediaType.APPLICATION_XML )
			.entity( computerDTO )
			.post( ClientResponse.class )
			.getEntity(new GenericType<ComputerDTO>(){});
	}

	@Override
	public void deleteComputer(int id) {
		LOGGER.info("Starting deleteComputer {}", id);
		Client.create()
			.resource(COMPUTER + "/delete/" + id)
			.header("Content-Type", "application/xml")
			.delete( ClientResponse.class );		
	}

	@Override
	public void deleteCompany(int id) {
		LOGGER.info("Starting deleteCompany {}", id);
		Client.create()
			.resource(COMPANY + "/delete/" + id)
			.header("Content-Type", "application/xml")
			.delete( ClientResponse.class );		
	}

}
