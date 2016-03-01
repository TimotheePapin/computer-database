package com.excilys.formation.java.computerdatabase.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.formation.java.computerdatabase.CompanyService;
import com.excilys.formation.java.computerdatabase.ComputerService;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;
import com.excilys.formation.java.computerdatabase.page.EditComputerPageCreator;

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
	
	@ModelAttribute("ComputerDTO")
	public ComputerDTO getComputerDTO() {
		return new ComputerDTO();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doPost(@Valid @ModelAttribute("computerDTO") ComputerDTO computerDTO,
			BindingResult result, ModelMap modelMap) {
		if(result.hasErrors()) {
			return "editComputer";
		} 
		return editComputerPageCreator.postRequest(computerDTO);
	}
}
