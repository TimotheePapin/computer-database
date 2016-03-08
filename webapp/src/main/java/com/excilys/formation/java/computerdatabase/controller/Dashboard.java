package com.excilys.formation.java.computerdatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.java.computerdatabase.ComputerService;
import com.excilys.formation.java.computerdatabase.page.DashboardPageCreator;
import com.excilys.formation.java.computerdatabase.model.Page;

@Controller
@RequestMapping("/dashboard")
public class Dashboard {

	@Autowired
	private DashboardPageCreator dashboardPageCreator;

	@Autowired
	private ComputerService computerService;

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(Page webPage, ModelMap modelMap) {
		webPage = dashboardPageCreator.getRequest(webPage);
		modelMap.addAttribute("webPage", webPage);
		return "dashboard";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doPost(@RequestParam("selection") String selection,
			ModelMap modelMap) {
		if (selection != null && !selection.trim().isEmpty()) {
			String[] ids = selection.split(",");
			for (String id : ids) {
				computerService.deleteById(Integer.parseInt(id));
			}
		}
		return "redirect:/dashboard";
	}
}
