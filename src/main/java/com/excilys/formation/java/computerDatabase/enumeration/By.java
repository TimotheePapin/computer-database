package com.excilys.formation.java.computerDatabase.enumeration;

public enum By {
	COMPUTERID("computer.id"),
	COMPUTERNAME("computer.name"), 
	INTRODUCED("computer.introduced"), 
	DISCONTINUED("computer.discontinued"), 
	COMPANYNAME("company.name");

	private String name = "";

	By(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
