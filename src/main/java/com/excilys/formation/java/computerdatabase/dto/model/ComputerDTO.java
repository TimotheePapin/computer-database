package com.excilys.formation.java.computerdatabase.dto.model;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.validation.constraints.Size;

import org.springframework.context.i18n.LocaleContextHolder;

import com.excilys.formation.java.computerdatabase.model.Computer;
import com.excilys.formation.java.computerdatabase.validator.Date;

/**
 * The Class ComputerDTO.
 */
public class ComputerDTO implements Serializable {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = -230506813682769315L;

	/**
	 * The id.
	 */
	private int id;

	/**
	 * The name.
	 */
	@Size(min=2, max=255) //= "{add.error.name}"
	private String name;

	/**
	 * The introduced.
	 */
	@Date(message = "{add.error.date}")
	private String introduced;

	/**
	 * The discontinued.
	 */
	@Date(message = "{add.error.date}")
	private String discontinued;

	/**
	 * The company.
	 */
	private String company;

	/**
	 * Instantiates a new computer dto.
	 */
	public ComputerDTO() {
		super();
	}

	/**
	 * Instantiates a new computer dto.
	 *
	 * @param computer the computer
	 */
	public ComputerDTO(Computer computer) {
		System.out.println(computer);
		this.id = computer.getId();
		this.name = computer.getName();
		DateTimeFormatter formatter;
		if(LocaleContextHolder.getLocaleContext().getLocale().equals(new Locale("fr", ""))) {
			formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		} else {
			formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		}
		if (computer.getIntroduced() == null) {
			this.introduced = null;
		} else {
			introduced = computer.getIntroduced().format(formatter);
		}
		if (computer.getDiscontinued() == null) {
			this.discontinued = null;
		} else {
			this.discontinued = computer.getDiscontinued().toString();
			discontinued = computer.getDiscontinued().format(formatter);
		}
		if (computer.getCompany() != null) {
			this.company = computer.getCompany().getName();
		}
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the introduced.
	 *
	 * @return the introduced
	 */
	public String getIntroduced() {
		return introduced;
	}

	/**
	 * Sets the introduced.
	 *
	 * @param introduced the new introduced
	 */
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	/**
	 * Gets the discontinued.
	 *
	 * @return the discontinued
	 */
	public String getDiscontinued() {
		return discontinued;
	}

	/**
	 * Sets the discontinued.
	 *
	 * @param discontinued the new discontinued
	 */
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	/**
	 * Gets the company.
	 *
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * Sets the company.
	 *
	 * @param company the new company
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result
				+ ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (getClass() != obj.getClass())
			return false;
		ComputerDTO other = (ComputerDTO) obj;
		if (company == null) {
			if (other.company != null) {
				return false;
			}
		} else if (!company.equals(other.company)) {
			return false;
		}
		if (discontinued == null) {
			if (other.discontinued != null) {
				return false;
			}
		} else if (!discontinued.equals(other.discontinued)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (introduced == null) {
			if (other.introduced != null) {
				return false;
			}
		} else if (!introduced.equals(other.introduced)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ComputerDTO [id=" + id + ", name=" + name + ", introduced="
				+ introduced + ", discontinued=" + discontinued + ", company="
				+ company + "]";
	}
}
