package com.excilys.formation.java.computerDatabase.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.excilys.formation.java.computerDatabase.model.Company;
import com.excilys.formation.java.computerDatabase.persistence.CompanyDAO;

public class TestServiceCompany {
	private ServiceCompany serviceCompany;
	private CompanyDAO daoCompany;
	private List<Company> list;

	public TestServiceCompany() {
		this.serviceCompany = ServiceCompany.getInstance();
	}


	@Before
	public void setUp() {
		daoCompany = Mockito.mock(CompanyDAO.class);
		list = new ArrayList<Company>();
	}

	@After
	public void tearDown() {
		daoCompany = null;
	}
	
	@Test
	public void testGetAll() {
		
		list.add(new Company(1, "test"));
		list.add(new Company(2, ""));

		when(daoCompany.getAll()).thenReturn(list);
		serviceCompany.setDaoCompany(daoCompany);
		assertEquals(list, serviceCompany.getAll());
	}
	
	@Test
	public void testGetAllNull() {

		CompanyDAO daoCompany = Mockito.mock(CompanyDAO.class);

		when(daoCompany.getAll()).thenReturn(list);
		serviceCompany.setDaoCompany(daoCompany);
		assertEquals(list, serviceCompany.getAll());
	}
}
