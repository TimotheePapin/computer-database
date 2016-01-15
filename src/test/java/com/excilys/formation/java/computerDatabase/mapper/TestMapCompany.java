package com.excilys.formation.java.computerDatabase.mapper;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.java.computerDatabase.model.Company;

public class TestMapCompany {
	private Company company;
	@Before
	public void beforeTest (){
		company = new Company();
	}
	@Test
	public void addCompany () {
		
	}
	
	@After
	public void afterTest () {
		company =null;
	}
}
