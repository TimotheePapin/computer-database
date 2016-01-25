package com.excilys.formation.java.computerDatabase.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class RequestComputer.
 */
public class RequestComputer implements Serializable {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 5811137958510558260L;

	/**
	 * The computers dto.
	 */
	private List<ComputerDTO> computersDTO;

	/**
	 * The list size.
	 */
	private int listSize;

	/**
	 * The page.
	 */
	private int page;

	/**
	 * The db size.
	 */
	private int dbSize;

	/**
	 * The page max.
	 */
	private int pageMax;
	
	/**
	 * The search.
	 */
	private String search;
	
	/**
	 * The order.
	 */
	private String order;
	
	/**
	 * The by.
	 */
	private String by;

	/**
	 * Instantiates a new request computer.
	 */
	public RequestComputer() {
		super();
	}

	/**
	 * Instantiates a new request computer.
	 *
	 * @param computers the computers
	 * @param dbSize the db size
	 * @param page the page
	 * @param listSize the list size
	 * @param search the search
	 * @param order the order
	 * @param by the by
	 */
	public RequestComputer(List<Computer> computers, int dbSize, int page,
			int listSize, String search, String order, String by) {
		super();
		this.computersDTO = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			ComputerDTO computerDTO = new ComputerDTO(computer);
			this.computersDTO.add(computerDTO);
		}
		this.listSize = listSize;
		this.page = page;
		this.dbSize = dbSize;
		this.search=search;
		this.order=order;
		this.by=by;
		pageMax =(dbSize / listSize) + 1;
	}

	/**
	 * Gets the computers dto.
	 *
	 * @return the computers dto
	 */
	public List<ComputerDTO> getComputersDTO() {
		return computersDTO;
	}

	/**
	 * Sets the computers dto.
	 *
	 * @param computersDTO the new computers dto
	 */
	public void setComputersDTO(List<ComputerDTO> computersDTO) {
		this.computersDTO = computersDTO;
	}

	/**
	 * Gets the list size.
	 *
	 * @return the list size
	 */
	public int getListSize() {
		return listSize;
	}

	/**
	 * Sets the list size.
	 *
	 * @param listSize the new list size
	 */
	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	/**
	 * Gets the page.
	 *
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * Sets the page.
	 *
	 * @param page the new page
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * Gets the db size.
	 *
	 * @return the db size
	 */
	public int getDbSize() {
		return dbSize;
	}

	/**
	 * Sets the db size.
	 *
	 * @param dbSize the new db size
	 */
	public void setDbSize(int dbSize) {
		this.dbSize = dbSize;
	}

	/**
	 * Gets the page max.
	 *
	 * @return the page max
	 */
	public int getPageMax() {
		return pageMax;
	}

	/**
	 * Sets the page max.
	 *
	 * @param pageMax the new page max
	 */
	public void setPageMax(int pageMax) {
		this.pageMax = pageMax;
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
	public String getOrder() {
		return order;
	}

	/**
	 * Sets the order.
	 *
	 * @param order the new order
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * Gets the by.
	 *
	 * @return the by
	 */
	public String getBy() {
		return by;
	}

	/**
	 * Sets the by.
	 *
	 * @param by the new by
	 */
	public void setBy(String by) {
		this.by = by;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((by == null) ? 0 : by.hashCode());
		result = prime * result
				+ ((computersDTO == null) ? 0 : computersDTO.hashCode());
		result = prime * result + dbSize;
		result = prime * result + listSize;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + page;
		result = prime * result + pageMax;
		result = prime * result + ((search == null) ? 0 : search.hashCode());
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestComputer other = (RequestComputer) obj;
		if (by == null) {
			if (other.by != null)
				return false;
		} else if (!by.equals(other.by))
			return false;
		if (computersDTO == null) {
			if (other.computersDTO != null)
				return false;
		} else if (!computersDTO.equals(other.computersDTO))
			return false;
		if (dbSize != other.dbSize)
			return false;
		if (listSize != other.listSize)
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (page != other.page)
			return false;
		if (pageMax != other.pageMax)
			return false;
		if (search == null) {
			if (other.search != null)
				return false;
		} else if (!search.equals(other.search))
			return false;
		return true;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "RequestComputer [computersDTO=" + computersDTO + ", listSize="
				+ listSize + ", page=" + page + ", dbSize=" + dbSize
				+ ", pageMax=" + pageMax + ", search=" + search + ", order="
				+ order + ", by=" + by + "]";
	}
}