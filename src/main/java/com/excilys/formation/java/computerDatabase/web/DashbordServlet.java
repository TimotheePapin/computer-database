package com.excilys.formation.java.computerDatabase.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.java.computerDatabase.model.RequestComputer;
import com.excilys.formation.java.computerDatabase.service.ServiceComputer;

public class DashbordServlet extends HttpServlet {
	private static ServiceComputer serviceComputer;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serviceComputer = ServiceComputer.getInstance();
		RequestComputer reqComp = new RequestComputer(serviceComputer.getPart(0,10),serviceComputer.getSize(),0);
		
		request.setAttribute("reqComp", reqComp);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashbord.jsp").forward(request, response);
	}
}
