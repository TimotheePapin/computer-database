package com.excilys.formation.java.computerDatabase.mapper;

import com.excilys.formation.java.computerDatabase.enumeration.By;
import com.excilys.formation.java.computerDatabase.enumeration.Order;
import com.excilys.formation.java.computerDatabase.exception.ValidationException;

public class MapEnum {
	
	public static Order toOrder (String order) {
		if(order.equals("ASC")) {
			return Order.ASC;
		}
		if(order.equals("DESC")) {
			return Order.DESC;
		}
		throw new ValidationException("Incorrect Order");
	}
	
	public static By toBy (String by) {
		if(by.equals("computer.name")) {
			return By.COMPUTERNAME;
		}
		if(by.equals("computer.introduced")) {
			return By.INTRODUCED;
		}
		if(by.equals("computer.discontinued")) {
			return By.DISCONTINUED;
		}
		if(by.equals("company.name")) {
			return By.COMPANYNAME;
		}
		if(by.equals("computer.id")) {
			return By.COMPUTERID;
		}
		throw new ValidationException("Incorrect By");
	}

}
