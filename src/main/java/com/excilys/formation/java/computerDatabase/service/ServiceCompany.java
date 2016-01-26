package com.excilys.formation.java.computerDatabase.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.java.computerDatabase.exception.DatabaseException;
import com.excilys.formation.java.computerDatabase.model.Company;
import com.excilys.formation.java.computerDatabase.persistence.CompanyDAO;
import com.excilys.formation.java.computerDatabase.persistence.DatabaseConnection;
import com.excilys.formation.java.computerDatabase.persistence.impl.CompanyDAOImpl;

/**
 * The Class ServiceCompany.
 */
@SuppressWarnings("serial")
public class ServiceCompany implements Serializable {

	/**
	 * The instance.
	 */
	private static ServiceCompany instance = new ServiceCompany();;

	private DatabaseConnection databaseConnection;

	private static ServiceComputer serviceComputer;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CompanyDAOImpl.class);

	/**
	 * The dao company.
	 */
	private CompanyDAO companyDAO;

	/**
	 * Instantiates a new service company.
	 */
	private ServiceCompany() {
		companyDAO = CompanyDAOImpl.getInstance();
		serviceComputer = ServiceComputer.getInstance();
		databaseConnection = DatabaseConnection.getInstance();
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public List<Company> getAll() {
		return companyDAO.getAll();
	}

	public void deleteById(int id) {
		Connection connection = null;
		try {
			connection = databaseConnection.getConnection();
			connection.setAutoCommit(false);
			serviceComputer.deleteByCompanyId(id, connection);
			companyDAO.deleteById(id, connection);
			connection.commit();
		} catch (SQLException | DatabaseException e) {
			if (connection != null) {
				try {
					connection.rollback();
					LOGGER.error("Failed to execute the Delete by Company Id");
					throw new DatabaseException(
							"Failed to execute the Delete by Company Id", e);
				} catch (SQLException excep) {
					LOGGER.error("Failed to rollback");
					throw new DatabaseException("Failed to rollback", e);
				}
			}
		} finally {
			try {
				connection.setAutoCommit(true);
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				LOGGER.error("Failed to set back the autoCommit");
				throw new DatabaseException("Failed to set back the autoCommit",
						e);
			}
		}
	}

	/**
	 * Gets the single instance of ServiceCompany.
	 *
	 * @return single instance of ServiceCompany
	 */
	public static ServiceCompany getInstance() {
		return instance;
	}

	/**
	 * Gets the dao company.
	 *
	 * @return the dao company
	 */
	public CompanyDAO getDaoCompany() {
		return companyDAO;
	}

	/**
	 * Sets the dao company.
	 *
	 * @param daoCompany the new dao company
	 */
	public void setDaoCompany(CompanyDAO daoCompany) {
		this.companyDAO = daoCompany;
	}

}
