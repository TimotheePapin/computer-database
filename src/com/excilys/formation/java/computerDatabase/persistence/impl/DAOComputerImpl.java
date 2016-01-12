
package com.excilys.formation.java.computerDatabase.persistence.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import com.excilys.formation.java.computerDatabase.mapper.MapComputer;
import com.excilys.formation.java.computerDatabase.model.Computer;

public class DAOComputerImpl {

	private static DAOComputerImpl _instance = null;
	Connection connection;
	Statement statement;
	ResultSet result = null;
	private static MapComputer mapComputer;

	private DAOComputerImpl() {
		mapComputer = MapComputer.getInstance();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = new String("jdbc:mysql://localhost:3306/computer-database-db");
			this.connection = DriverManager.getConnection(url, "admincdb", "qwerty1234");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public List < Computer > getAll() {
		try {
			statement = connection.createStatement();
			result = statement.executeQuery("SELECT * FROM computer ;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapComputer.mapcomputers(result);
	}

	public Computer getById(int id) {
		try {
			statement = connection.createStatement();
			result = statement.executeQuery("SELECT * FROM computer WHERE id = '" + id + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapComputer.mapcomputer(result);
	}

	public Computer getByName(String name) {
		try {
			statement = connection.createStatement();
			result = statement.executeQuery("SELECT * FROM computer WHERE name = '" + name + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapComputer.mapcomputer(result);
	}

	public void deleteByName(String name) {
		try {
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM computer WHERE name = '" + name + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteById(int id) {
		try {
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM computer WHERE id = '" + id + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Computer computer) {
		try {
			statement = connection.createStatement();
			statement.executeUpdate(
					"UPDATE computer SET name = '" + name + "', introduced = '" + introduced + "', discontinued = '"
							+ discontinued + "', company_id = '" + companyId + "' WHERE id = '" + id + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void add(Computer computer) {
		try {
			statement = connection.createStatement();
			statement.executeUpdate(
					"INSERT INTO computer (name,introduced,discontinued,company_id) VALUES ('"+ name + "', '" + introduced + "', '"+ discontinued + "', " + companyId + ");");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public synchronized static DAOComputerImpl getInstance() {
		if (_instance == null) {
			_instance = new DAOComputerImpl();
		}
		return _instance;
	}

}
