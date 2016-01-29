package com.excilys.formation.java.computerdatabase.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.java.computerdatabase.exception.DatabaseException;
import com.excilys.formation.java.computerdatabase.model.Company;
import com.excilys.formation.java.computerdatabase.persistence.CompanyDAO;
import com.excilys.formation.java.computerdatabase.persistence.DatabaseConnection;
import com.excilys.formation.java.computerdatabase.persistence.impl.CompanyDAOImpl;
import com.excilys.formation.java.computerdatabase.service.CompanyService;

/**
 * The Class CompanyServiceImpl.
 */
public class CompanyServiceImpl implements CompanyService {

	/**
	 * The instance.
	 */
	private static CompanyServiceImpl instance = new CompanyServiceImpl();;

	/**
	 * The database connection.
	 */
	private DatabaseConnection databaseConnection;

	/**
	 * The service computer.
	 */
	private static ComputerServiceImpl serviceComputer;

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CompanyServiceImpl.class);

	/**
	 * The company dao.
	 */
	private CompanyDAO companyDAO;

	/**
	 * Instantiates a new company service impl.
	 */
	private CompanyServiceImpl() {
		companyDAO = CompanyDAOImpl.getInstance();
		serviceComputer = ComputerServiceImpl.getInstance();
		databaseConnection = DatabaseConnection.getInstance();
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@Override
	public List<Company> getAll() {
		return companyDAO.getAll();
	}

	@Override
	public void deleteById(int id) {
		LOGGER.info("Starting deleteById");
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
					LOGGER.error("Failed to execute the Delete by Company Id : "
							+ id);
					throw new DatabaseException(
							"Failed to execute the Delete by Company Id: " + id,
							e);
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
	 * Gets the single instance of CompanyServiceImpl.
	 *
	 * @return single instance of CompanyServiceImpl
	 */
	public static CompanyServiceImpl getInstance() {
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
