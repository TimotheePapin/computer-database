package com.excilys.formation.java.computerdatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.formation.java.computerdatabase.service.CompanyService;
import com.excilys.formation.java.computerdatabase.web.dto.AddComputerPageCreator;
import com.excilys.formation.java.computerdatabase.web.dto.ComputerDTO;

@Controller
@RequestMapping("/addComputer")
public class AddComputer {
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private AddComputerPageCreator addComputerPageCreator;

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(String error, ModelMap modelMap) {
		if(error == null || error.trim().isEmpty()) {
			error="";
		}
		modelMap.addAttribute("Companies", companyService.getAll());
		modelMap.addAttribute("error", error);
		return "addComputer";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doPost(@ModelAttribute("computerDTO") ComputerDTO computerDTO, ModelMap modelMap) {
		return addComputerPageCreator.postRequest(computerDTO);	
	}
}
