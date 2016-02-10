package com.excilys.formation.java.computerdatabase.web;

import java.time.LocalDateTime;

import com.excilys.formation.java.computerdatabase.model.Company;
import com.excilys.formation.java.computerdatabase.model.Computer;

public class ComputerBuilder {

	private int id;
	private String name;
	private LocalDateTime introducedDate;
	private LocalDateTime discontinuedDate;
	private Company company;

	public ComputerBuilder setId(int id) {
		this.id = id;
		return this;
	}

	public ComputerBuilder setName(String name) {
		this.name = name;
		return this;
	}
	public ComputerBuilder setIntroducedDate(LocalDateTime introducedDate) {
		this.introducedDate = introducedDate;
		return this;
	}
	public ComputerBuilder setDiscontinuedDate(LocalDateTime discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
		return this;
	}
	public ComputerBuilder setCompany(Company company) {
		this.company = company;
		return this;
	}

	public Computer build() {
		return new Computer(id, name, introducedDate, discontinuedDate, company);
	}
}
