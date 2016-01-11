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
			result = statement.executeQuery("SELECT * FROM COMPUTER ;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ResultSet recupComputer(int id) {
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
		try {
			statement = connection.createStatement();
			result = statement.executeQuery("SELECT * FROM COMPUTER WHERE name = '" + name + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void supprComputer(String name) {
		try {
			statement = connection.createStatement();
			result = statement.executeQuery("DELETE * FROM COMPUTER WHERE name = '" + name + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void supprComputer(int id) {
		try {
			statement = connection.createStatement();
			result = statement.executeQuery("DELETE * FROM COMPUTER WHERE id = '" + id + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateComputer(int id, String name, String introduced, String discontinued, int companyId) {
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(
					"UPDATE COMPUTER SET name = '" + name + "', introduced = '" + introduced + "', discontinued = '"
							+ discontinued + "', companyId = '" + companyId + "' WHERE id = '" + id + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void creerComputer(String name, String introduced, String discontinued, int companyId) {
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(
					"INSERT INTO COMPUTER  VALUES ('','"+ name + "', '" + introduced + "', '"+ discontinued + "', '" + companyId + "');");
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
