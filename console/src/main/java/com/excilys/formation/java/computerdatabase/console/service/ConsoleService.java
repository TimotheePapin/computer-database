package com.excilys.formation.java.computerdatabase.console.service;

import java.util.List;

import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;
import com.excilys.formation.java.computerdatabase.model.Company;
import com.excilys.formation.java.computerdatabase.model.Computer;

/**
 * The Interface ConsoleService.
 */
public interface ConsoleService {
	
	/**
	 * Gets the all ComputerDTO.
	 *
	 * @return the all ComputerDTO
	 */
	public List<ComputerDTO> getAllComputer();
	
	/**
	 * Gets the all company.
	 *
	 * @return the all company
	 */
	public List<Company> getAllCompany();
	
	/**
	 * Gets the ComputerDTO by id.
	 *
	 * @param id the id
	 * @return the ComputerDTO by id
	 */
	public ComputerDTO getComputerById(int id);
	
	/**
	 * Creates the Computer.
	 *
	 * @param Computer the Computer
	 */
	public ComputerDTO createComputer(Computer computer);
	
	/**
	 * Update Computer.
	 *
	 * @param Computer the Computer
	 */
	public ComputerDTO updateComputer(Computer computer);
	
	/**
	 * Delete Computer.
	 *
	 * @param id the id
	 */
	public void deleteComputer(int id);
	
	/**
	 * Delete company.
	 *
	 * @param id the id
	 */
	public void deleteCompany(int id);
}
