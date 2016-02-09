package com.excilys.formation.java.computerdatabase.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.java.computerdatabase.exception.ValidationException;
import com.excilys.formation.java.computerdatabase.model.Computer;
import com.excilys.formation.java.computerdatabase.service.CompanyService;
import com.excilys.formation.java.computerdatabase.service.ComputerService;
import com.excilys.formation.java.computerdatabase.utils.ComputerValidation;

/**
 * The Class AddComputerServlet.
 */
@Component
public class AddComputerServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = -1084282215550710682L;

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AddComputerServlet.class);

	/**
	 * The service computer.
	 */
	@Autowired
	private ComputerService serviceComputer;

	/**
	 * The service company.
	 */
	@Autowired
	private CompanyService serviceCompany;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = request.getParameter("error");
		if (error == null) {
			error = "";
		}
		request.setAttribute("error", error);
		request.setAttribute("Companies", serviceCompany.getAll());
		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/addComputer.jsp")
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Computer computer = new Computer();
		try {
			computer = ComputerValidation.validation(null,
					request.getParameter("computerName"),
					request.getParameter("introduced"),
					request.getParameter("discontinued"),
					request.getParameter("companyId"));
			serviceComputer.create(computer);
			response.sendRedirect("dashboard?search="
					+ (computer.getName().replace(" ", "+")));
		} catch (ValidationException e) {
			LOGGER.error(
					"\n" + e.getMessage() + "\nFailed to Add the Computer;");
			response.sendRedirect("addComputer?error=" + computer.getName());
		}
	}

	/**
	 * Gets the service computer.
	 *
	 * @return the service computer
	 */
	public ComputerService getServiceComputer() {
		return serviceComputer;
	}

	/**
	 * Sets the service computer.
	 *
	 * @param serviceComputer the new service computer
	 */
	public void setServiceComputer(ComputerService serviceComputer) {
		this.serviceComputer = serviceComputer;
	}
}
