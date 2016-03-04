package com.excilys.formation.java.computerdatabase.webservice.xml;

import java.util.List;

import javax.ws.rs.core.Response;

import com.excilys.formation.java.computerdatabase.model.Company;

public interface CompanyRessource {
	
	public List<Company> getAll();
	
	public Response delete(int id);
}
