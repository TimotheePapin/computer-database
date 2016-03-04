package com.excilys.formation.java.computerdatabase.webservice.xml.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.java.computerdatabase.CompanyService;
import com.excilys.formation.java.computerdatabase.model.Company;
import com.excilys.formation.java.computerdatabase.webservice.xml.CompanyRessource;

@Path("/company")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class CompanyRessourceImpl implements CompanyRessource{
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CompanyRessourceImpl.class);
	
	@Autowired
	CompanyService companyService;

	@Override
	@GET
	@Path("getAll")
	public List<Company> getAll() {
		LOGGER.info("Starting getAll");
		return companyService.getAll();
	}

	@Override
	@DELETE
	@Produces(MediaType.TEXT_HTML)
	@Path("delete/{id}")
	public Response delete(@PathParam("id") int id) {
		LOGGER.info("Starting deleteById {}", id);
		companyService.deleteById(id);	
		return Response.ok("Company and associat Computers deleted").build();
	}

}
