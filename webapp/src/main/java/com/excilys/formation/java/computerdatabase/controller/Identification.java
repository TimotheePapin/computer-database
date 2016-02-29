package com.excilys.formation.java.computerdatabase.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/identification")
public class Identification {

	@RequestMapping(method = RequestMethod.GET)
	public String login(
			@RequestParam(value = "error", required = false) String error,
			ModelMap modelMap) {
		if (error == null || !"true".equals(error)) {
			error = "false";
		}
		SecurityContextHolder.getContext().getAuthentication()
				.setAuthenticated(false);
		modelMap.addAttribute("error", error);
		return "identification";
	}
}
