package com.excilys.formation.java.computerDatabase.persistence.impl;

import java.io.File;
import java.nio.charset.StandardCharsets;

import javax.sql.DataSource;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Mockito;

import com.excilys.formation.java.computerdatabase.mapper.MapCompany;
import com.excilys.formation.java.computerdatabase.persistence.CompanyDAO;
import com.excilys.formation.java.computerdatabase.persistence.impl.CompanyDAOImpl;

public class TestDAOCompanyImpl {
	
	private static final String JDBC_DRIVER = org.h2.Driver.class.getName();
	private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
	private static final String USER = "sa";
	private static final String PASSWORD = "";
	
	private CompanyDAO daoCompanyImpl;
	private MapCompany mapCompany;
	
	public TestDAOCompanyImpl() {
		this.daoCompanyImpl = CompanyDAOImpl.getInstance();
	}
	

	@BeforeClass
	public static void createSchema() throws Exception {
		RunScript.execute(JDBC_URL, USER, PASSWORD, "/home/excilys/Documents/Workspace/computer-database/src/test/resources/schema.sql",StandardCharsets.UTF_8, false);
	}

	@Before
	public void importDataSet() throws Exception {
		MapCompany mapCompany = Mockito.mock(MapCompany.class);
		IDataSet dataSet = readDataSet();
		cleanlyInsert(dataSet);
	}
	
	@After
	public void tearDown() {
		mapCompany = null;
	}

	private IDataSet readDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new File("/home/excilys/Documents/Workspace/computer-database/src/test/resources/Database.xml"));
	}

	private void cleanlyInsert(IDataSet dataSet) throws Exception {
		IDatabaseTester databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD);
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
	}

	//@Test
	//public void findsAndReadsExistingPersonByFirstName() throws Exception {
		
		
//		PersonRepository repository = new PersonRepository(dataSource());
//		Person charlie = repository.findPersonByFirstName("Charlie");
//
//		assertThat(charlie.getFirstName(), is("Charlie"));
//		assertThat(charlie.getLastName(), is("Brown"));
//		assertThat(charlie.getAge(), is(42));
	//}

//	@Test
//	public void returnsNullWhenPersonCannotBeFoundByFirstName() throws Exception {
//		PersonRepository repository = new PersonRepository(dataSource());
//		Person person = repository.findPersonByFirstName("iDoNotExist");
//
//		assertThat(person, is(nullValue()));
//	}

	private DataSource dataSource() {
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setURL(JDBC_URL);
		dataSource.setUser(USER);
		dataSource.setPassword(PASSWORD);
		return dataSource;
	}
}