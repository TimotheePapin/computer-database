package com.excilys.formation.java.computerDatabase.persistence;

import java.io.Serializable;
import java.sql.*;

@SuppressWarnings("serial")
public class SQLComputer implements Serializable {

	private static SQLComputer _instance = null;
	Connection connection;
	Statement statement;
	ResultSet result = null;

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
		try {
			statement = connection.createStatement();
			result = statement.executeQuery("SELECT * FROM computer ;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ResultSet recupComputer(int id) {
		try {
			statement = connection.createStatement();
			result = statement.executeQuery("SELECT * FROM computer WHERE id = '" + id + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ResultSet recupComputer(String name) {
		try {
			statement = connection.createStatement();
			result = statement.executeQuery("SELECT * FROM computer WHERE name = '" + name + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void supprComputer(String name) {
		try {
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM computer WHERE name = '" + name + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void supprComputer(int id) {
		try {
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM computer WHERE id = '" + id + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateComputer(int id, String name, Timestamp introduced, Timestamp discontinued, int companyId) {
		try {
			statement = connection.createStatement();
			statement.executeUpdate(
					"UPDATE computer SET name = '" + name + "', introduced = '" + introduced + "', discontinued = '"
							+ discontinued + "', company_id = '" + companyId + "' WHERE id = '" + id + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void creerComputer(String name, Timestamp introduced, Timestamp discontinued, int companyId) {
		try {
			statement = connection.createStatement();
			statement.executeUpdate(
					"INSERT INTO computer (name,introduced,discontinued,company_id) VALUES ('"+ name + "', '" + introduced + "', '"+ discontinued + "', " + companyId + ");");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
