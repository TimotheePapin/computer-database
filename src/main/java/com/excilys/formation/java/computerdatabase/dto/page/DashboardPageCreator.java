package com.excilys.formation.java.computerdatabase.dto.page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.java.computerdatabase.dto.model.Page;
import com.excilys.formation.java.computerdatabase.dto.model.PageProperties;
import com.excilys.formation.java.computerdatabase.mapper.MapEnum;
import com.excilys.formation.java.computerdatabase.model.Computer;
import com.excilys.formation.java.computerdatabase.service.ComputerService;

@Service
public class DashboardPageCreator {
	
	@Autowired
	private ComputerService serviceComputer;

	public Page getRequest(Page currentPage) {
		List<Computer> computers;
		int dbSize;
		PageProperties pageProps = new PageProperties();
		if (currentPage.getPage() < 1) {
			currentPage.setPage(1);
		}
		if (currentPage.getListSize() != 0) {
			pageProps.setSize(currentPage.getListSize());
		}
		if (currentPage.getPage() != 0) {
			pageProps.setMin((currentPage.getPage() - 1) * pageProps.getSize());
		}
		if (currentPage.getOrder() != null) {
			pageProps.setOrder(MapEnum.toOrder(currentPage.getOrder()));
		}
		if (currentPage.getBy() != null) {
			pageProps.setBy(MapEnum.toBy(currentPage.getBy()));
		}
		if (currentPage.getSearch() != null && !currentPage.getSearch().trim().isEmpty()) {
			pageProps.setSearch(currentPage.getSearch());
		}
		computers = serviceComputer.getPage(pageProps);
		dbSize = serviceComputer.getSize(pageProps.getSearch());
		return  new Page(computers, dbSize, currentPage.getPage(), pageProps);
	}
}
