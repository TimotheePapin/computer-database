package com.excilys.formation.java.computerdatabase.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.java.computerdatabase.exception.DatabaseException;
import com.excilys.formation.java.computerdatabase.model.Company;
import com.excilys.formation.java.computerdatabase.persistence.CompanyDAO;
import com.excilys.formation.java.computerdatabase.persistence.DatabaseConnection;
import com.excilys.formation.java.computerdatabase.service.CompanyService;

/**
 * The Class CompanyServiceImpl.
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

	/**
	 * The database connection.
	 */
	@Autowired
	private DatabaseConnection databaseConnection;
	
	private ThreadLocal<Connection> threadLocalConnection;
	
	DataSourceTransactionManager data;
	
	/**
	 * The service computer.
	 */
	@Autowired
	private ComputerServiceImpl serviceComputer;

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CompanyServiceImpl.class);

	/**
	 * The company dao.
	 */
	@Autowired
	private CompanyDAO companyDAO;
	
	public CompanyServiceImpl() {
		super();
		threadLocalConnection = new ThreadLocal<>();
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
	@Transactional(readOnly=false)
	public void deleteById(int id) {
		LOGGER.info("Starting deleteById");
		try {
			threadLocalConnection.set(databaseConnection.startTransaction());
			serviceComputer.deleteByCompanyId(id);
			companyDAO.deleteById(id);
			databaseConnection.commit();
		} catch (DatabaseException e) {
			if (threadLocalConnection != null) {
				databaseConnection.rollback();
				LOGGER.error("Failed to execute the Delete by Company Id : "
						+ id);
				throw new DatabaseException(
						"Failed to execute the Delete by Company Id: " + id,
						e);
			}
		} finally {
			try {
				threadLocalConnection.get().setAutoCommit(true);
				if (threadLocalConnection != null) {
					threadLocalConnection.get().close();
				}
			} catch (SQLException e) {
				LOGGER.error("Failed to set back the autoCommit");
				throw new DatabaseException("Failed to set back the autoCommit",
						e);
			}
		}
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
