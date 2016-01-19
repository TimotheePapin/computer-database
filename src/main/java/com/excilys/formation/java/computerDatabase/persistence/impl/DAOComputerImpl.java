
package com.excilys.formation.java.computerDatabase.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.excilys.formation.java.computerDatabase.mapper.MapComputer;
import com.excilys.formation.java.computerDatabase.model.Computer;
import com.excilys.formation.java.computerDatabase.persistence.DaoComputer;
import com.excilys.formation.java.computerDatabase.persistence.DatabaseConnection;

public class DAOComputerImpl implements DaoComputer {

	private static DAOComputerImpl _instance = null;
	private static DatabaseConnection databaseConnection;

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
			connection = databaseConnection.open();
			statement = connection
					.prepareStatement("SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id;");
			result = statement.executeQuery();
			return MapComputer.mapComputers(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			databaseConnection.close(connection);
		}
		return null;
	}
	
	@Override
	public List<Computer> getPart(int size, int min) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = databaseConnection.open();
			statement = connection
					.prepareStatement("SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id LIMIT ? OFFSET ?");
			statement.setInt(1, size);
			statement.setInt(2, min);
			result = statement.executeQuery();
			return MapComputer.mapComputers(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			databaseConnection.close(connection);
		}
		return null;
	}

	@Override
	public Computer getById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = databaseConnection.open();
			statement = connection.prepareStatement(
					"SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.id= ?;");
			statement.setInt(1, id);
			result = statement.executeQuery();
			return MapComputer.mapComputer(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection, statement);
		}
		return null;
	}

	@Override
	public Computer getByName(String name) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = databaseConnection.open();
			statement = connection.prepareStatement(
					"SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.name= ?;");
			statement.setString(1, name);
			result = statement.executeQuery();
			return MapComputer.mapComputer(result);
		} catch (SQLException e) {
			e.printStackTrace();
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
			connection = databaseConnection.open();
			statement = connection.prepareStatement("DELETE FROM computer where name= ?;");
			statement.setString(1, name);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection, statement);
		}
	}

	@Override
	public void deleteById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = databaseConnection.open();
			statement = connection.prepareStatement("DELETE FROM computer where id= ?;");
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection, statement);
		}
	}

	@Override
	public Computer update(Computer computer) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = databaseConnection.open();
			statement = connection.prepareStatement(
					"UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?;");
			statement.setString(1, computer.getName());
			statement.setTimestamp(2, MapComputer.toTimestamp(computer.getIntroduced()));
			statement.setTimestamp(3, MapComputer.toTimestamp(computer.getDiscontinued()));
			statement.setInt(4, computer.getCompany().getId());
			statement.setInt(5, computer.getId());
			statement.execute();
			computer = getById(computer.getId());
		} catch (SQLException e) {
			e.printStackTrace();
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
			connection = databaseConnection.open();
			statement = connection.prepareStatement(
					"INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?, ?, ?, ?);");
			statement.setString(1, computer.getName());
			statement.setTimestamp(2, MapComputer.toTimestamp(computer.getIntroduced()));
			statement.setTimestamp(3, MapComputer.toTimestamp(computer.getDiscontinued()));
			statement.setInt(4, computer.getCompany().getId());
			statement.execute();
			computer = getByName(computer.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection, statement);
		}
		return computer;
	}
	
	@Override
	public int getSize() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = databaseConnection.open();
			statement = connection
					.prepareStatement("SELECT COUNT(*) AS count FROM computer;");
			result = statement.executeQuery();
			result.next();
			return result.getInt("count");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			databaseConnection.close(connection);
		}
		return 0;
	}

	private void close(Connection connection, PreparedStatement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
			}
		}
		databaseConnection.close(connection);
	}
	
	public synchronized static DAOComputerImpl getInstance() {
		if (_instance == null) {
			_instance = new DAOComputerImpl();
		}
		return _instance;
	}

}
