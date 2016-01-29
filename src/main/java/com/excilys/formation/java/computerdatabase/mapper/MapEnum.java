package com.excilys.formation.java.computerdatabase.mapper;

import com.excilys.formation.java.computerdatabase.exception.ValidationException;
import com.excilys.formation.java.computerdatabase.persistence.enumeration.By;
import com.excilys.formation.java.computerdatabase.persistence.enumeration.Order;

/**
 * The Class MapEnum.
 */
public class MapEnum {

	/**
	 * To order.
	 *
	 * @param order the order
	 * @return the order
	 */
	public static Order toOrder(String order) {
		if (order.equals("ASC")) {
			return Order.ASC;
		}
		if (order.equals("DESC")) {
			return Order.DESC;
		}
		throw new ValidationException("Incorrect Order");
	}

	/**
	 * To by.
	 *
	 * @param by the by
	 * @return the by
	 */
	public static By toBy(String by) {
		if (by.equals("computer.name")) {
			return By.COMPUTERNAME;
		}
		if (by.equals("computer.introduced")) {
			return By.INTRODUCED;
		}
		if (by.equals("computer.discontinued")) {
			return By.DISCONTINUED;
		}
		if (by.equals("company.name")) {
			return By.COMPANYNAME;
		}
		if (by.equals("computer.id")) {
			return By.COMPUTERID;
		}
		throw new ValidationException("Incorrect By");
	}

}
