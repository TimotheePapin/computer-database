package com.excilys.formation.java.computerdatabase.webservice;

import java.util.List;

import javax.ws.rs.core.Response;

import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;

public interface ComputerRessource {

	public List<ComputerDTO> getAll();

	public ComputerDTO getById(int id);

	public Response delete(int id);

	public ComputerDTO update(ComputerDTO computerDTO);

	public ComputerDTO create(ComputerDTO computerDTO);
}
