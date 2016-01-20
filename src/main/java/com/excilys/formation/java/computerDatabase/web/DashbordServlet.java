package com.excilys.formation.java.computerDatabase.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.java.computerDatabase.model.RequestComputer;
import com.excilys.formation.java.computerDatabase.service.ServiceComputer;

public class DashbordServlet extends HttpServlet {
	private static final long serialVersionUID = 2578883235862569397L;
	private ServiceComputer serviceComputer;
	private int page = 1;
	private int size = 10;
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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public ServiceComputer getServiceComputer() {
		return serviceComputer;
	}

	public void setServiceComputer(ServiceComputer serviceComputer) {
		this.serviceComputer = serviceComputer;
	}
}
