package com.excilys.formation.java.computerDatabase.persistence;

import java.io.Serializable;
import java.sql.*;

@SuppressWarnings("serial")
public class SQLComputer implements Serializable {

	private static SQLComputer _instance = null;
	Connection connection;

	private SQLComputer() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = new String("jdbc:mysql://localhost:3306/computer-database-db");
			this.connection = DriverManager.getConnection(url, "admincdb", "qwerty1234");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet recupComputers() {
		Statement statement;
		ResultSet result = null;
		try {
			statement = connection.createStatement();
			result = statement.executeQuery("SELECT * FROM COMPUTER ;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ResultSet recupComputer(int id) {
		Statement statement;
		ResultSet result = null;
		try {
			statement = connection.createStatement();
			result = statement.executeQuery("SELECT * FROM COMPUTER WHERE id = '" + id + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ResultSet recupComputer(String name) {
		Statement statement;
		ResultSet result = null;
		try {
			statement = connection.createStatement();
			result = statement.executeQuery("SELECT * FROM COMPUTER WHERE name = '" + name + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ResultSet supprComputer(String name) {
		Statement statement;
		ResultSet result = null;
		try {
			statement = connection.createStatement();
			result = statement.executeQuery("DELETE * FROM COMPUTER WHERE name = '" + name + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ResultSet supprComputer(int id) {
		Statement statement;
		ResultSet result = null;
		try {
			statement = connection.createStatement();
			result = statement.executeQuery("DELETE * FROM COMPUTER WHERE id = '" + id + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ResultSet updateComputer(int id, String name, String introduced, String discontinued, int companyId) {
		Statement statement;
		ResultSet result = null;
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(
					"UPDATE COMPUTER SET name = '" + name + "', introduced = '" + introduced + "', discontinued = '"
							+ discontinued + "', companyId = '" + companyId + "' WHERE id = '" + id + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void fermeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	synchronized public static SQLComputer getInstance() {
		if (_instance == null) {
			_instance = new SQLComputer();
		}
		return _instance;
	}

}
