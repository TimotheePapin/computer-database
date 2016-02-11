package com.excilys.formation.java.computerdatabase.web.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.java.computerdatabase.model.Computer;

/**
 * The Class Page.
 */
public class Page implements Serializable {

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
	 * Instantiates a new page.
	 */
	public Page() {
		super();
	}

	/**
	 * Instantiates a new page.
	 *
	 * @param computers the computers
	 * @param dbSize the db size
	 * @param page the page
	 * @param pageProps the page props
	 */
	public Page(List<Computer> computers, int dbSize, int page,
			PageProperties pageProps) {
		super();
		this.computersDTO = new ArrayList<ComputerDTO>();
		if(computers.get(0) != null) {
			for (Computer computer : computers) {
				ComputerDTO computerDTO = new ComputerDTO(computer);
				this.computersDTO.add(computerDTO);
			}
		}
		this.listSize = pageProps.getSize();
		this.page = page;
		this.dbSize = dbSize;
		this.search = pageProps.getSearch();
		this.order = pageProps.getOrder().toString();
		this.by = pageProps.getBy().toString();
		pageMax = (dbSize / listSize) + 1;
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