package com.excilys.formation.java.computerdatabase.webservice.json.impl;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.java.computerdatabase.CompanyService;
import com.excilys.formation.java.computerdatabase.model.Company;
import com.excilys.formation.java.computerdatabase.webservice.CompanyRessource;

@RestController
@RequestMapping("rest/json/company")
public class CompanyRessourceImpl implements CompanyRessource {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CompanyRessourceImpl.class);

	@Autowired
	private CompanyService companyService;

	@Override
	@GET
	@RequestMapping("getAll")
	public List<Company> getAll() {
		LOGGER.info("Starting getAll");
		return companyService.getAll();
	}

	@Override
	@DELETE
	@Produces(MediaType.TEXT_HTML)
	@RequestMapping("delete/{id}")
	public Response delete(int id) {
		LOGGER.info("Starting deleteById {}", id);
		companyService.deleteById(id);
		return Response.ok("Company and associat Computers deleted").build();
	}

}
