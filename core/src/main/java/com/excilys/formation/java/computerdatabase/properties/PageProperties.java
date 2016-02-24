package com.excilys.formation.java.computerdatabase.properties;

import com.excilys.formation.java.computerdatabase.enumeration.By;
import com.excilys.formation.java.computerdatabase.enumeration.Order;

/**
 * The Class PageProperties.
 */
public class PageProperties {

	/**
	 * The min.
	 */
	private int min;

	/**
	 * The size.
	 */
	private int size;

	/**
	 * The search.
	 */
	private String search;

	/**
	 * The order.
	 */
	private Order order;

	/**
	 * The by.
	 */
	private By by;

	/**
	 * Instantiates a new page properties.
	 */
	public PageProperties() {
		size = 10;
		min = 0;
		search = "";
		order = Order.ASC;
		by = By.COMPUTERID;
	}

	/**
	 * Gets the min.
	 *
	 * @return the min
	 */
	public int getMin() {
		return min;
	}

	/**
	 * Sets the min.
	 *
	 * @param min the new min
	 */
	public void setMin(int min) {
		this.min = min;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Gets the search.
	 *
	 * @return the search
	 */
	public String getSearch() {
		return search;
	}

	/**
	 * Sets the search.
	 *
	 * @param search the new search
	 */
	public void setSearch(String search) {
		this.search = search;
	}

	/**
	 * Gets the order.
	 *
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * Sets the order.
	 *
	 * @param order the new order
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * Gets the by.
	 *
	 * @return the by
	 */
	public By getBy() {
		return by;
	}

	/**
	 * Sets the by.
	 *
	 * @param by the new by
	 */
	public void setBy(By by) {
		this.by = by;
	}

	@Override
	public String toString() {
		return "PageProperties [min=" + min + ", size=" + size + ", search="
				+ search + ", order=" + order + ", by=" + by + "]";
	}
	
	
}
