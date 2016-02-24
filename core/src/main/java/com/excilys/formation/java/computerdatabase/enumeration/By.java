package com.excilys.formation.java.computerdatabase.enumeration;

/**
 * The Enum By.
 */
public enum By {

	/**
	 * The computerid.
	 */
	COMPUTERID("computer.id"),

	/**
	 * The computername.
	 */
	COMPUTERNAME("computer.name"),

	/**
	 * The introduced.
	 */
	INTRODUCED("computer.introduced"),

	/**
	 * The discontinued.
	 */
	DISCONTINUED("computer.discontinued"),

	/**
	 * The companyname.
	 */
	COMPANYNAME("company.name");

	/**
	 * The name.
	 */
	private String name = "";

	/**
	 * Instantiates a new by.
	 *
	 * @param name the name
	 */
	By(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
