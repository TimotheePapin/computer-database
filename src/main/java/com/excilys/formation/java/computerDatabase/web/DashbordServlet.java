package com.excilys.formation.java.computerDatabase.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.java.computerDatabase.model.RequestComputer;
import com.excilys.formation.java.computerDatabase.service.ServiceComputer;

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

	/**
	 * The page.
	 */
	private int page = 1;

	/**
	 * The size.
	 */
	private int size = 10;

	/**
	 * The min.
	 */
	private int min = 0;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		if (page <= (int) serviceComputer.getSize() / size + 1) {
			RequestComputer reqComp = new RequestComputer(serviceComputer.getPart(size, min), serviceComputer.getSize(),
					page, size);
			request.setAttribute("reqComp", reqComp);
			this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashbord.jsp").forward(request, response);
		}
	}

	/**
	 * Gets the page.
	 *
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * Sets the page.
	 *
	 * @param page the new page
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Gets the min.
	 *
	 * @return the min
	 */
	public int getMin() {
		return min;
	}

	/**
	 * Sets the min.
	 *
	 * @param min the new min
	 */
	public void setMin(int min) {
		this.min = min;
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
