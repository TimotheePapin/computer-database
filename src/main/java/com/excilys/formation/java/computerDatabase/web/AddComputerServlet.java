package com.excilys.formation.java.computerDatabase.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.java.computerDatabase.exception.ValidationException;
import com.excilys.formation.java.computerDatabase.model.Computer;
import com.excilys.formation.java.computerDatabase.persistence.impl.CompanyDAOImpl;
import com.excilys.formation.java.computerDatabase.service.ServiceCompany;
import com.excilys.formation.java.computerDatabase.service.ServiceComputer;
import com.excilys.formation.java.computerDatabase.validation.ComputerValidation;

/**
 * The Class AddComputerServlet.
 */
public class AddComputerServlet extends HttpServlet {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = -1084282215550710682L;

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CompanyDAOImpl.class);

	/**
	 * The service computer.
	 */
	private ServiceComputer serviceComputer = ServiceComputer.getInstance();

	/**
	 * The service company.
	 */
	private ServiceCompany serviceCompany = ServiceCompany.getInstance();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("Companies", serviceCompany.getAll());
		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/addComputer.jsp")
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Computer computer = ComputerValidation.Validation(null,
					request.getParameter("computerName"),
					request.getParameter("introduced"),
					request.getParameter("discontinued"),
					request.getParameter("companyId"));
			serviceComputer.create(computer);
		} catch (ValidationException e) {
			LOGGER.error("\n"+e.getMessage()+"\nFailed to Add the Computer;");
		}
		response.sendRedirect("dashboard");
	}

	/**
	 * Gets the service computer.
	 *
	 * @return the service computer
	 */
	public ServiceComputer getServiceComputer() {
		return serviceComputer;
	}

	/**
	 * Sets the service computer.
	 *
	 * @param serviceComputer the new service computer
	 */
	public void setServiceComputer(ServiceComputer serviceComputer) {
		this.serviceComputer = serviceComputer;
	}
}
