package com.excilys.formation.java.computerDatabase.model;

import java.io.Serializable;
import java.util.List;

public class RequestComputer implements Serializable{
	private static final long serialVersionUID = 5811137958510558260L;
	private List<Computer> computers;
	private int sizeTab;
	private int page;
	
	public RequestComputer () {
		super();
	}
	
	public RequestComputer (List<Computer> computers, int sizeTab, int page) {
		super();
		this.computers=computers;
		this.sizeTab=sizeTab;
		this.page=page;
	}

	public List<Computer> getComputers() {
		return computers;
	}

	public void setComputers(List<Computer> computers) {
		this.computers = computers;
	}

	public int getSizeTab() {
		return sizeTab;
	}

	public void setSizeTab(int sizeTab) {
		this.sizeTab = sizeTab;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((computers == null) ? 0 : computers.hashCode());
		result = prime * result + page;
		result = prime * result + sizeTab;
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
		if (computers == null) {
			if (other.computers != null)
				return false;
		} else if (!computers.equals(other.computers))
			return false;
		if (page != other.page)
			return false;
		if (sizeTab != other.sizeTab)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RequestComputer [computers=" + computers + ", sizeTab=" + sizeTab + ", page=" + page + "]";
	}
	
}
