package com.excilys.formation.java.computerDatabase.persistence;

import java.io.Serializable;
import java.sql.*;

@SuppressWarnings("serial")
public class SQLCompany implements Serializable {

	private static SQLCompany _instance = null;
	Connection connection;

	private SQLCompany() {
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

	public ResultSet recupCompanies() {
		Statement statement;
		ResultSet result = null;
		try {
			statement = connection.createStatement();
			result = statement.executeQuery("SELECT * FROM company ;");
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

	synchronized public static SQLCompany getInstance() {
		if (_instance == null) {
			_instance = new SQLCompany();
		}
		return _instance;
	}

}
