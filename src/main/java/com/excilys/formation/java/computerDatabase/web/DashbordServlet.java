package com.excilys.formation.java.computerDatabase.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.java.computerDatabase.exception.ValidationException;
import com.excilys.formation.java.computerDatabase.model.Computer;
import com.excilys.formation.java.computerDatabase.model.RequestComputer;
import com.excilys.formation.java.computerDatabase.service.ServiceComputer;
import com.excilys.formation.java.computerDatabase.validation.ComputerValidation;

/**
 * The Class DashbordServlet.
 */
public class DashbordServlet extends HttpServlet {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 2578883235862569397L;

	/**
	 * The service computer.
	 */
	private ServiceComputer serviceComputer;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = 1;
		int size = 10;
		int min = 0;
		String strPage = request.getParameter("page");
		String strSize = request.getParameter("size");
		if (strPage != null) {
			page = Integer.valueOf(strPage);
			min = ((page - 1) * size);
		}
		if (strSize != null) {
			size = Integer.valueOf(strSize);
			min = ((page - 1) * size);
		}
		serviceComputer = ServiceComputer.getInstance();
		if (page <= serviceComputer.getSize() / size + 1) {
			RequestComputer reqComp = new RequestComputer(
					serviceComputer.getPart(size, min),
					serviceComputer.getSize(), page, size);
			request.setAttribute("reqComp", reqComp);
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/dashbord.jsp")
					.forward(request, response);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String selection = request.getParameter("selection");
		String[] ids = selection.split(",");
		for (String id : ids) {
			serviceComputer.deleteById(Integer.parseInt(id));
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
