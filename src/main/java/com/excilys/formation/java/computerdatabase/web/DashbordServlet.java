package com.excilys.formation.java.computerdatabase.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.java.computerdatabase.mapper.MapEnum;
import com.excilys.formation.java.computerdatabase.model.Computer;
import com.excilys.formation.java.computerdatabase.service.ComputerService;
import com.excilys.formation.java.computerdatabase.web.DTO.Page;
import com.excilys.formation.java.computerdatabase.web.DTO.PageProperties;

/**
 * The Class DashbordServlet.
 */
@Component
public class DashbordServlet extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 466444159340957004L;
	
	/**
	 * The service computer.
	 */
	@Autowired
	private ComputerService serviceComputer;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageProperties pageProps = new PageProperties();
		int page = 1;
		List<Computer> computers;
		int dbSize;
		String strPage = request.getParameter("page");
		String strSize = request.getParameter("size");
		String search = request.getParameter("search");
		String order = request.getParameter("order");
		String by = request.getParameter("by");
		if (strSize != null) {
			pageProps.setSize(Integer.valueOf(strSize));
		}
		if (strPage != null) {
			page = Integer.valueOf(strPage);
			pageProps.setMin((page - 1) * pageProps.getSize());
		}
		if (order != null) {
			pageProps.setOrder(MapEnum.toOrder(order));
		}
		if (by != null) {
			pageProps.setBy(MapEnum.toBy(by));
		}
		if (search != null && !search.trim().isEmpty()) {
			pageProps.setSearch(search);
		}
		computers = serviceComputer.getPage(pageProps);
		dbSize = serviceComputer.getSize(pageProps.getSearch());
		if (page <= dbSize / pageProps.getSize() + 1) {
			Page webPage = new Page(computers, dbSize, page, pageProps);
			request.setAttribute("webPage", webPage);
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/dashbord.jsp")
					.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String selection = request.getParameter("selection");
		if (selection != null && !selection.trim().isEmpty()) {
			String[] ids = selection.split(",");
			for (String id : ids) {
				serviceComputer.deleteById(Integer.parseInt(id));
			}
		}
		response.sendRedirect("dashboard");
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
