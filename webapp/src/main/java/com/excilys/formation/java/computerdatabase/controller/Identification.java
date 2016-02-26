package com.excilys.formation.java.computerdatabase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/identification")
public class Identification {
	
	@RequestMapping(method = RequestMethod.GET)
	public String doGet(ModelMap modelMap) {
		return "identification";
	}
}
