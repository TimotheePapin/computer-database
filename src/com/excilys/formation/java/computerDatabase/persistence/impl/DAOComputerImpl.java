
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
	private PreparedStatement statement = null;
	private ResultSet result = null;

	@Override
	public List<Computer> getAll() {
		try {
			Connection connection = databaseConnection.open();
			statement = connection
					.prepareStatement("SELECT * FROM computer LEFT JOIN company ON computer.company_id=comapny.id;");
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
				}
			}
			databaseConnection.close();
		}
		return MapComputer.mapComputers(result);
	}

	@Override
	public Computer getById(int id) {
		try {
			Connection connection = databaseConnection.open();
			statement = connection.prepareStatement(
					"SELECT * FROM computer LEFT JOIN company ON computer.company_id=comapny.id WHERE id='?';");
			statement.setInt(1, id);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
				}
			}
			databaseConnection.close();
		}
		return MapComputer.mapComputer(result);
	}

	@Override
	public Computer getByName(String name) {
		try {
			Connection connection = databaseConnection.open();
			statement = connection.prepareStatement(
					"SELECT * FROM computer LEFT JOIN company ON computer.company_id=comapny.id WHERE name='?';");
			statement.setString(1, name);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
				}
			}
			databaseConnection.close();
		}
		return MapComputer.mapComputer(result);
	}

	@Override
	public void deleteByName(String name) {
		try {
			Connection connection = databaseConnection.open();
			statement = connection.prepareStatement("DELETE * FROM computer where name='?';");
			statement.setString(1, name);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
				}
			}
			databaseConnection.close();
		}
	}

	@Override
	public void deleteById(int id) {
		try {
			Connection connection = databaseConnection.open();
			statement = connection.prepareStatement("DELETE * FROM computer where id='?';");
			statement.setInt(1, id);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
				}
			}
			databaseConnection.close();
		}
	}

	@Override
	public void update(Computer computer) {
		try {
			Connection connection = databaseConnection.open();
			statement = connection.prepareStatement(
					"UPDATE computer SET name = '?', introduced = '?', discontinued = '?', company_id = '?' WHERE id = '?';");
			statement.setString(1, computer.getName());
			statement.setTimestamp(2, MapComputer.toTimestamp(computer.getIntroduced()));
			statement.setTimestamp(3, MapComputer.toTimestamp(computer.getDiscontinued()));
			statement.setInt(4, computer.getCompany().getId());
			statement.setInt(5, computer.getId());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
				}
			}
			databaseConnection.close();
		}
	}

	@Override
	public void add(Computer computer) {
		try {
			Connection connection = databaseConnection.open();
			statement = connection.prepareStatement(
					"INSERT INTO computer (name,introduced,discontinued,company_id) VALUES ('?', '?', '?', '?');");
			statement.setString(1, computer.getName());
			statement.setTimestamp(2, MapComputer.toTimestamp(computer.getIntroduced()));
			statement.setTimestamp(3, MapComputer.toTimestamp(computer.getDiscontinued()));
			statement.setInt(4, computer.getCompany().getId());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
				}
			}
			databaseConnection.close();
		}
	}

	public synchronized static DAOComputerImpl getInstance() {
		if (_instance == null) {
			_instance = new DAOComputerImpl();
		}
		return _instance;
	}

}
