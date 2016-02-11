package com.excilys.formation.java.computerdatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.formation.java.computerdatabase.service.CompanyService;
import com.excilys.formation.java.computerdatabase.service.ComputerService;
import com.excilys.formation.java.computerdatabase.web.dto.ComputerDTO;
import com.excilys.formation.java.computerdatabase.web.dto.EditComputerPageCreator;

@Controller
@RequestMapping("/editComputer")
public class EditComputer {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private ComputerService computerService;

	@Autowired
	private EditComputerPageCreator editComputerPageCreator;

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(int id, ModelMap modelMap) {
		modelMap.addAttribute("Computer",
				new ComputerDTO(computerService.getById(id)));
		modelMap.addAttribute("Companies", companyService.getAll());
		return "editComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doPost(@ModelAttribute("computerDTO") ComputerDTO computerDTO,
			ModelMap modelMap) {
		return editComputerPageCreator.postRequest(computerDTO);
	}
}
