package com.excilys.formation.java.computerdatabase.database;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;

@Repository
public class FakeDatabase {
	private static final String CREATE_DB_FILE = "createDB.sql";

	@Autowired
	private DataSource dataSource;
	
	public void initTestDb(){
		try (Connection connection = dataSource.getConnection()) {
	      ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
	      rdp.addScript(new ClassPathResource(CREATE_DB_FILE));
	      rdp.populate(connection);
	   } catch(SQLException e){
	   }
	}
}
