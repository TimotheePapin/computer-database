package com.excilys.formation.java.computerDatabase.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.java.computerDatabase.mapper.MapComputer;
import com.excilys.formation.java.computerDatabase.model.Computer;
import com.excilys.formation.java.computerDatabase.persistence.DaoComputer;
import com.excilys.formation.java.computerDatabase.persistence.DatabaseConnection;

/**
 * The Class DAOComputerImpl.
 */
public class DAOComputerImpl implements DaoComputer {

	/**
	 * The instance.
	 */
	private static DAOComputerImpl instance = null;

	/**
	 * The database connection.
	 */
	private static DatabaseConnection databaseConnection;

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DAOCompanyImpl.class);

	/**
	 * Instantiates a new DAO computer impl.
	 */
	private DAOComputerImpl() {
		super();
		databaseConnection = DatabaseConnection.getInstance();
	}

	@Override
	public List<Computer> getAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = databaseConnection.getConnection();
			statement = connection.prepareStatement(
					"SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id;");
			result = statement.executeQuery();
			return MapComputer.mapComputers(result);
		} catch (SQLException e) {
			LOGGER.error("Fail to execute the getAll Query");
		} finally {
			close(connection, statement);
		}
		return null;
	}

	@Override
	public List<Computer> getPart(int size, int min, String order, String by) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result;
		try {
			connection = databaseConnection.getConnection();
			statement = connection.prepareStatement(
					"SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id ORDER BY ? ? LIMIT ? OFFSET ?");
			statement.setString(1, by);
			statement.setString(2, order);
			statement.setInt(3, size);
			statement.setInt(4, min);
			result = statement.executeQuery();
			return MapComputer.mapComputers(result);
		} catch (SQLException e) {
			LOGGER.error("Fail to execute the getPart Query");
		} finally {
			close(connection, statement);
		}
		return null;
	}

	@Override
	public List<Computer> getSearchPart(int size, int min,String search, String order, String by) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result;
		try {
			connection = databaseConnection.getConnection();
			statement = connection.prepareStatement(
					"SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE company.name LIKE ? OR computer.name LIKE ? ORDER BY ? ? LIMIT ? OFFSET ?");
			statement.setString(1, "%"+search+"%");
			statement.setString(2, "%"+search+"%");
			statement.setString(3, by);
			statement.setString(4, order);
			statement.setInt(5, size);
			statement.setInt(6, min);
			result = statement.executeQuery();
			return MapComputer.mapComputers(result);
		} catch (SQLException e) {
			LOGGER.error("Fail to execute the getSearchPart Query");
		} finally {
			close(connection, statement);
		}
		return null;
	}
	
	@Override
	public Computer getById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result;
		try {
			connection = databaseConnection.getConnection();
			statement = connection.prepareStatement(
					"SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.id= ?;");
			statement.setInt(1, id);
			result = statement.executeQuery();
			return MapComputer.mapComputer(result);
		} catch (SQLException e) {
			LOGGER.error("Fail to execute the getById Query");
		} finally {
			close(connection, statement);
		}
		return null;
	}

	@Override
	public Computer getByName(String name) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result;
		try {
			connection = databaseConnection.getConnection();
			statement = connection.prepareStatement(
					"SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.name= ?;");
			statement.setString(1, name);
			result = statement.executeQuery();
			return MapComputer.mapComputer(result);
		} catch (SQLException e) {
			LOGGER.error("Fail to execute the getByName Query");
		} finally {
			close(connection, statement);
		}
		return null;
	}

	@Override
	public void deleteByName(String name) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = databaseConnection.getConnection();
			statement = connection
					.prepareStatement("DELETE FROM computer where name= ?;");
			statement.setString(1, name);
			statement.execute();
		} catch (SQLException e) {
			LOGGER.error("Fail to execute the deleteByName Query");
		} finally {
			close(connection, statement);
		}
	}

	@Override
	public void deleteById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = databaseConnection.getConnection();
			statement = connection
					.prepareStatement("DELETE FROM computer where id= ?;");
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Fail to execute the deleteById Query");
		} finally {
			close(connection, statement);
		}
	}

	@Override
	public Computer update(Computer computer) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = databaseConnection.getConnection();
			statement = connection.prepareStatement(
					"UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?;");
			statement.setString(1, computer.getName());
			statement.setTimestamp(2,
					MapComputer.toTimestamp(computer.getIntroduced()));
			statement.setTimestamp(3,
					MapComputer.toTimestamp(computer.getDiscontinued()));
			statement.setInt(4, computer.getCompany().getId());
			statement.setInt(5, computer.getId());
			statement.execute();
			computer = getById(computer.getId());
		} catch (SQLException e) {
			LOGGER.error("Fail to execute the update Query");
		} finally {
			close(connection, statement);
		}
		return computer;
	}

	@Override
	public Computer add(Computer computer) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = databaseConnection.getConnection();
			statement = connection.prepareStatement(
					"INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?, ?, ?, ?);");
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
		} catch (SQLException e) {
			LOGGER.error("Fail to execute the add Query");
		} finally {
			close(connection, statement);
		}
		return computer;
	}

	@Override
	public int getSize() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result;
		try {
			connection = databaseConnection.getConnection();
			statement = connection.prepareStatement(
					"SELECT COUNT(*) AS count FROM computer;");
			result = statement.executeQuery();
			result.next();
			return result.getInt("count");
		} catch (SQLException e) {
			LOGGER.error("Fail to execute the getSize Query");
		} finally {
			close(connection, statement);
		}
		return 0;
	}
	
	@Override
	public int getSearchSize(String search) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result;
		try {
			connection = databaseConnection.getConnection();
			statement = connection.prepareStatement(
					"SELECT COUNT(*) AS count FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE company.name LIKE ? OR computer.name LIKE ?;");
			statement.setString(1, "%"+search+"%");
			statement.setString(2, "%"+search+"%");
			result = statement.executeQuery();
			result.next();
			return result.getInt("count");
		} catch (SQLException e) {
			LOGGER.error("Fail to execute the getSearchSize Query");
		} finally {
			close(connection, statement);
		}
		return 0;
	}

	/**
	 * Close.
	 *
	 * @param connection the connection
	 * @param statement the statement
	 */
	private void close(Connection connection, PreparedStatement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.error("Fail to close Connection");
			}
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the single instance of DAOComputerImpl.
	 *
	 * @return single instance of DAOComputerImpl
	 */
	public static synchronized DAOComputerImpl getInstance() {
		if (instance == null) {
			instance = new DAOComputerImpl();
		}
		return instance;
	}

}
