package com.excilys.formation.java.computerdatabase.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.java.computerdatabase.exception.ValidationException;
import com.excilys.formation.java.computerdatabase.model.Computer;
import com.excilys.formation.java.computerdatabase.service.impl.CompanyServiceImpl;
import com.excilys.formation.java.computerdatabase.service.impl.ComputerServiceImpl;
import com.excilys.formation.java.computerdatabase.utils.ComputerValidation;
import com.excilys.formation.java.computerdatabase.web.DTO.ComputerDTO;

/**
 * The Class EditComputerServlet.
 */
public class EditComputerServlet extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 4387176304859389447L;

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(EditComputerServlet.class);

	/**
	 * The service computer.
	 */
	@Autowired
	private ComputerServiceImpl serviceComputer;

	/**
	 * The service company.
	 */
	@Autowired
	private CompanyServiceImpl serviceCompany;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String strId = request.getParameter("id");
		int id = 0;
		if (strId != null && !strId.isEmpty()) {
			id = Integer.valueOf(strId);
		}
		request.setAttribute("Computer",
				new ComputerDTO(serviceComputer.getById(id)));
		request.setAttribute("Companies", serviceCompany.getAll());
		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editComputer.jsp")
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Computer computer = new Computer();
		try {
			computer = ComputerValidation.validation(request.getParameter("id"),
					request.getParameter("computerName"),
					request.getParameter("introduced"),
					request.getParameter("discontinued"),
					request.getParameter("companyId"));
			serviceComputer.update(computer);
			response.sendRedirect("dashboard?search="
					+ (computer.getName().replace(" ", "+")));
		} catch (ValidationException e) {
			LOGGER.error(
					"\n" + e.getMessage() + "\nFailed to Edit the Computer;");
			response.sendRedirect("editComputer?id=" + computer.getId());

		}

	}

	/**
	 * Gets the service computer.
	 *
	 * @return the service computer
	 */
	public ComputerServiceImpl getServiceComputer() {
		return serviceComputer;
	}

	/**
	 * Sets the service computer.
	 *
	 * @param serviceComputer the new service computer
	 */
	public void setServiceComputer(ComputerServiceImpl serviceComputer) {
		this.serviceComputer = serviceComputer;
	}
}
