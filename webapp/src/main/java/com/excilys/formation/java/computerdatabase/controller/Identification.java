package com.excilys.formation.java.computerdatabase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Identification {

	@RequestMapping(value = "/identification", method = RequestMethod.GET)
	public String login(
			@RequestParam(value = "error", required = false) String error,
			ModelMap modelMap) {
		if (error == null || !"true".equals(error)) {
			error = "false";
		}
		modelMap.addAttribute("error", error);
		return "identification";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(
			@RequestParam(value = "logout", required = false) String error,
			ModelMap modelMap) {
		modelMap.addAttribute("logout", true);
		return "redirect:/identification";
	}
}
