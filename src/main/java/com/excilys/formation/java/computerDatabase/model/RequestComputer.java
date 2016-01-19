package com.excilys.formation.java.computerDatabase.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RequestComputer implements Serializable {
	private static final long serialVersionUID = 5811137958510558260L;
	private List<ComputerDTO> computersDTO;
	private int listSize;
	private int page;
	private int dbSize;
	private int pageMax;

	public RequestComputer() {
		super();
	}

	public RequestComputer(List<Computer> computers, int dbSize, int page, int listSize) {
		super();
		this.computersDTO = new ArrayList();
		for (Computer computer : computers) {
			ComputerDTO computerDTO = new ComputerDTO(computer);
			this.computersDTO.add(computerDTO);
		}
		this.listSize = listSize;
		this.page = page;
		this.dbSize = dbSize;
		pageMax = (int) (dbSize / listSize) + 1;
	}

	public List<ComputerDTO> getComputersDTO() {
		return computersDTO;
	}

	public void setComputersDTO(List<ComputerDTO> computersDTO) {
		this.computersDTO = computersDTO;
	}

	public int getlistSize() {
		return listSize;
	}

	public void setlistSize(int listSize) {
		this.listSize = listSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getdbSize() {
		return dbSize;
	}

	public void setdbSize(int dbSize) {
		this.dbSize = dbSize;
	}

	public int getPageMax() {
		return pageMax;
	}

	public void setPageMax(int pageMax) {
		this.pageMax = pageMax;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((computersDTO == null) ? 0 : computersDTO.hashCode());
		result = prime * result + dbSize;
		result = prime * result + page;
		result = prime * result + pageMax;
		result = prime * result + listSize;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestComputer other = (RequestComputer) obj;
		if (computersDTO == null) {
			if (other.computersDTO != null)
				return false;
		} else if (!computersDTO.equals(other.computersDTO))
			return false;
		if (dbSize != other.dbSize)
			return false;
		if (page != other.page)
			return false;
		if (pageMax != other.pageMax)
			return false;
		if (listSize != other.listSize)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RequestComputer [computersDTO=" + computersDTO + ", listSize=" + listSize + ", page=" + page
				+ ", dbSize=" + dbSize + ", pageMax=" + pageMax + "]";
	}

}
