package com.excilys.formation.java.computerDatabase.model;

import java.io.Serializable;

public class ComputerDTO implements Serializable {
	private static final long serialVersionUID = -230506813682769315L;
	private int id;
	private String name;
	private String introduced;
	private String discontinued;
	private String company;

	public ComputerDTO() {
		super();
	}

	public ComputerDTO(Computer computer) {
		super();
		this.id = computer.getId();
		this.name = computer.getName();
		if (computer.getIntroduced() == null) {
			this.introduced = null;
		} else {
			this.introduced = computer.getIntroduced().toString();
			introduced = introduced.substring(8, 10) + "/" + introduced.substring(5, 7) + "/"
					+ introduced.substring(0, 4);
		}
		if (computer.getDiscontinued() == null) {
			this.discontinued = null;
		} else {
			this.discontinued = computer.getDiscontinued().toString();
			discontinued = discontinued.substring(8, 10) + "/" + discontinued.substring(5, 7) + "/"
					+ discontinued.substring(0, 4);
		}
		this.company = computer.getCompany().getName();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + id;
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ComputerDTO other = (ComputerDTO) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ComputerDTO [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", company=" + company + "]";
	}
}
