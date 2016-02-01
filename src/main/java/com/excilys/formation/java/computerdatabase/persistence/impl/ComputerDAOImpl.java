package com.excilys.formation.java.computerdatabase.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.java.computerdatabase.exception.DatabaseException;
import com.excilys.formation.java.computerdatabase.mapper.MapComputer;
import com.excilys.formation.java.computerdatabase.model.Computer;
import com.excilys.formation.java.computerdatabase.persistence.ComputerDAO;
import com.excilys.formation.java.computerdatabase.persistence.DatabaseConnection;
import com.excilys.formation.java.computerdatabase.service.impl.CompanyServiceImpl;
import com.excilys.formation.java.computerdatabase.web.DTO.PageProperties;

/**
 * The Class ComputerDAOImpl.
 */
public class ComputerDAOImpl implements ComputerDAO {

	/**
	 * The Constant INSTANCE.
	 */
	private static final ComputerDAOImpl INSTANCE = new ComputerDAOImpl();

	/**
	 * The database connection.
	 */
	private static DatabaseConnection databaseConnection;

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ComputerDAOImpl.class);

	/**
	 * Instantiates a new computer dao impl.
	 */
	private ComputerDAOImpl() {
		databaseConnection = DatabaseConnection.getInstance();
	}

	@Override
	public List<Computer> getAll() {
		LOGGER.info("Starting Computer getAll");
		try (Connection connection = databaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id;");
				ResultSet result = statement.executeQuery()) {
			return MapComputer.mapComputers(result);
		} catch (SQLException e) {
			LOGGER.error("Failed to execute the getAll Query");
			throw new DatabaseException("Failed to execute the getAll Query",
					e);
		}
	}

	@Override
	public List<Computer> getPage(PageProperties prop) {
		LOGGER.info("Starting Computer getPage");
		ResultSet result = null;
		try (Connection connection = databaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE company.name LIKE ? OR computer.name LIKE ? ORDER BY "
								+ prop.getBy() + " " + prop.getOrder()
								+ " LIMIT ? OFFSET ?");) {
			statement.setString(1, "%" + prop.getSearch() + "%");
			statement.setString(2, "%" + prop.getSearch() + "%");
			statement.setInt(3, prop.getSize());
			statement.setInt(4, prop.getMin());
			result = statement.executeQuery();
			return MapComputer.mapComputers(result);
		} catch (SQLException e) {
			LOGGER.error("Failed to execute the getPart Query");
			throw new DatabaseException("Failed to execute the getPage Query",
					e);
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					LOGGER.error("Failed to close ResultSet");
					throw new DatabaseException("Failed to close ResultSet", e);
				}
			}
		}
	}

	@Override
	public Computer getById(int id) {
		LOGGER.info("Starting Computer getById");
		ResultSet result = null;
		try (Connection connection = databaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.id= ?;");) {
			statement.setInt(1, id);
			result = statement.executeQuery();
			return MapComputer.mapComputer(result);
		} catch (SQLException e) {
			LOGGER.error("Failed to execute the getById Query :" + id);
			throw new DatabaseException(
					"Failed to execute the getById Query :" + id, e);
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					LOGGER.error("Failed to close ResultSet");
					throw new DatabaseException("Failed to close ResultSet", e);
				}
			}
		}
	}

	@Override
	public Computer getByName(String name) {
		LOGGER.info("Starting Computer getByName");
		ResultSet result = null;
		try (Connection connection = databaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.name= ?")) {
			statement.setString(1, name);
			result = statement.executeQuery();
			return MapComputer.mapComputer(result);
		} catch (SQLException e) {
			LOGGER.error("Failed to execute the getByName Query :" + name);
			throw new DatabaseException(
					"Failed to execute the getByName Query :" + name, e);
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					LOGGER.error("Failed to close ResultSet");
					throw new DatabaseException("Failed to close ResultSet", e);
				}
			}
		}
	}

	@Override
	public void deleteByName(String name) {
		LOGGER.info("Starting Computer deleteByName");
		try (Connection connection = databaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"DELETE FROM computer where name= ?")) {
			statement.setString(1, name);
			statement.execute();
		} catch (SQLException e) {
			LOGGER.error("Failed to execute the deleteByName Query :" + name);
			throw new DatabaseException(
					"Failed to execute the deleteByName Query :" + name, e);
		}
	}

	@Override
	public void deleteById(int id) {
		LOGGER.info("Starting Computer deleteById");
		try (Connection connection = databaseConnection.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM computer where id= ?")) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to execute the deleteById Query :" + id);
			throw new DatabaseException(
					"Failed to execute the deleteById Query :" + id, e);
		}
	}

	@Override
	public Computer update(Computer computer) {
		LOGGER.info("Starting Computer update");
		try (Connection connection = databaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?")) {
			statement.setString(1, computer.getName());
			statement.setTimestamp(2,
					MapComputer.toTimestamp(computer.getIntroduced()));
			statement.setTimestamp(3,
					MapComputer.toTimestamp(computer.getDiscontinued()));
			if (computer.getCompany().getId() == 0) {
				statement.setNull(4, java.sql.Types.BIGINT);
			} else {
				statement.setInt(4, computer.getCompany().getId());
			}
			statement.setInt(5, computer.getId());
			statement.execute();
			computer = getById(computer.getId());
			return computer;
		} catch (SQLException e) {
			LOGGER.error("Failed to execute the update Query :" + computer);
			throw new DatabaseException(
					"Failed to execute the update Query :" + computer, e);
		}
	}

	@Override
	public Computer add(Computer computer) {
		LOGGER.info("Starting Computer addComputer");
		try (Connection connection = databaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?, ?, ?, ?)")) {
			statement.setString(1, computer.getName());
			statement.setTimestamp(2,
					MapComputer.toTimestamp(computer.getIntroduced()));
			statement.setTimestamp(3,
					MapComputer.toTimestamp(computer.getDiscontinued()));
			if (computer.getCompany().getId() == 0) {
				statement.setNull(4, java.sql.Types.BIGINT);
			} else {
				statement.setInt(4, computer.getCompany().getId());
			}
			statement.execute();
			computer = getByName(computer.getName());
			return computer;
		} catch (SQLException e) {
			LOGGER.error("Failed to execute the add Query :" + computer);
			throw new DatabaseException(
					"Failed to execute the add Query :" + computer, e);
		}
	}

	@Override
	public int getSize(String search) {
		LOGGER.info("Starting Computer getSize");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result;
		try {
			connection = databaseConnection.getConnection();
			statement = connection.prepareStatement(
					"SELECT COUNT(*) AS count FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE company.name LIKE ? OR computer.name LIKE ?");
			statement.setString(1, "%" + search + "%");
			statement.setString(2, "%" + search + "%");
			result = statement.executeQuery();
			result.next();
			return result.getInt("count");
		} catch (SQLException e) {
			LOGGER.error("Failed to execute the getSize Query");
			throw new DatabaseException("Failed to execute the getSize Query",
					e);
		}
	}

	@Override
	public void deleteByCompanyId(int companyId) {
		LOGGER.info("Starting Computer deleteByCompanyId");
		try (PreparedStatement statement = CompanyServiceImpl.connection.get()
				.prepareStatement("DELETE FROM computer where company_id= ?")) {
			statement.setInt(1, companyId);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to execute the deleteByCompanyId Query :"
					+ companyId);
			throw new DatabaseException(
					"Failed to execute the deleteByCompanyId Query :"
							+ companyId,
					e);
		}
	}

	/**
	 * Gets the single instance of ComputerDAOImpl.
	 *
	 * @return single instance of ComputerDAOImpl
	 */
	public static ComputerDAOImpl getInstance() {
		return INSTANCE;
	}

}
