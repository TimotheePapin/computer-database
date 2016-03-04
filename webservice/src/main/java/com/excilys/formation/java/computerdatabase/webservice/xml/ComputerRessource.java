package com.excilys.formation.java.computerdatabase.webservice.xml;

import java.util.List;

import javax.ws.rs.core.Response;

import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;

public interface ComputerRessource {
	
	public List<ComputerDTO> getAll();
	
	public ComputerDTO getById(int i);
	
	public Response delete(int i);
	
	public ComputerDTO update(ComputerDTO computerDTO);
	
	public ComputerDTO create(ComputerDTO computerDTO);
}
