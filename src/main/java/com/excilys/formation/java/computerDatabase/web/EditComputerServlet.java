package com.excilys.formation.java.computerDatabase.web;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.java.computerDatabase.model.Company;
import com.excilys.formation.java.computerDatabase.model.Computer;
import com.excilys.formation.java.computerDatabase.model.ComputerDTO;
import com.excilys.formation.java.computerDatabase.service.ServiceCompany;
import com.excilys.formation.java.computerDatabase.service.ServiceComputer;

/**
 * The Class EditComputerServlet.
 */
public class EditComputerServlet extends HttpServlet {
	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 4387176304859389447L;

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
		String strId = request.getParameter("id");
		int id = Integer.valueOf(strId);
		request.setAttribute("Computer",new ComputerDTO(serviceComputer.getById(id)));
		request.setAttribute("Companies", serviceCompany.getAll());
		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editComputer.jsp")
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Computer computer = new Computer();
		computer.setId(Integer.parseInt(request.getParameter("id")));
		computer.setName(request.getParameter("computerName"));
		computer.setIntroduced(toDate(request.getParameter("introduced")));
		computer.setDiscontinued(toDate(request.getParameter("discontinued")));
		computer.setCompany(new Company(
				Integer.parseInt(request.getParameter("companyId")), ""));
		serviceComputer.update(computer);
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

	/**
	 * To date.
	 *
	 * @param date the date
	 * @return the local date time
	 */
	private LocalDateTime toDate(String date) {
		if (date == null || date.isEmpty()) {
			return null;
		} else {
			String[] temp = date.split("/");
			return LocalDateTime.of(Integer.parseInt(temp[2]),
					Integer.parseInt(temp[1]), Integer.parseInt(temp[0]), 0, 0,
					0, 0);
		}
	}
}
