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
	 */
	public RequestComputer(List<Computer> computers, int dbSize, int page,
			int listSize) {
		super();
		this.computersDTO = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			ComputerDTO computerDTO = new ComputerDTO(computer);
			this.computersDTO.add(computerDTO);
		}
		this.listSize = listSize;
		this.page = page;
		this.dbSize = dbSize;
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
	public int getlistSize() {
		return listSize;
	}

	/**
	 * Sets the list size.
	 *
	 * @param listSize the new list size
	 */
	public void setlistSize(int listSize) {
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
	public int getdbSize() {
		return dbSize;
	}

	/**
	 * Sets the db size.
	 *
	 * @param dbSize the new db size
	 */
	public void setdbSize(int dbSize) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((computersDTO == null) ? 0 : computersDTO.hashCode());
		result = prime * result + dbSize;
		result = prime * result + page;
		result = prime * result + pageMax;
		result = prime * result + listSize;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		RequestComputer other = (RequestComputer) obj;
		if (computersDTO == null) {
			if (other.computersDTO != null) {
				return false;
			}
		} else if (!computersDTO.equals(other.computersDTO)) {
			return false;
		}
		if (dbSize != other.dbSize) {
			return false;
		}
		if (page != other.page) {
			return false;
		}
		if (pageMax != other.pageMax) {
			return false;
		}
		if (listSize != other.listSize) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "RequestComputer [computersDTO=" + computersDTO + ", listSize="
				+ listSize + ", page=" + page + ", dbSize=" + dbSize
				+ ", pageMax=" + pageMax + "]";
	}

}
