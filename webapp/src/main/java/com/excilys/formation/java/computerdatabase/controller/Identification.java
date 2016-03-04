package com.excilys.formation.java.computerdatabase.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Identification {

	@RequestMapping(value = "/identification", method = RequestMethod.GET)
	public String login(
			@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout,
			ModelMap modelMap) {
		if (error == null || !"true".equals(error)) {
			error = "false";
		}
		modelMap.addAttribute("error", error);
		modelMap.addAttribute("logout", logout);
		return "identification";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap modelMap) {;
		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
		modelMap.addAttribute("logout", true);
		return "redirect:/identification";
	}
}
