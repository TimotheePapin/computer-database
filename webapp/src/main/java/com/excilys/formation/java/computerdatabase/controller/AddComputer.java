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
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;
import com.excilys.formation.java.computerdatabase.page.AddComputerPageCreator;

@Controller
@RequestMapping("/addComputer")
public class AddComputer {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private AddComputerPageCreator addComputerPageCreator;

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(ModelMap modelMap) {
		modelMap.addAttribute("Companies", companyService.getAll());
		return "addComputer";
	}

	@ModelAttribute("ComputerDTO")
	public ComputerDTO getComputerDTO() {
		return new ComputerDTO();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doPost(
			@Valid @ModelAttribute("ComputerDTO") ComputerDTO computerDTO,
			BindingResult result, ModelMap modelMap) {
		if (result.hasErrors()) {
			return "addComputer";
		}
		return addComputerPageCreator.postRequest(computerDTO);
	}
}
