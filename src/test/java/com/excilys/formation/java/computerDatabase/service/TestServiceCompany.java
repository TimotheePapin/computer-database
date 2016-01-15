package com.excilys.formation.java.computerDatabase.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.excilys.formation.java.computerDatabase.model.Company;
import com.excilys.formation.java.computerDatabase.persistence.DaoCompany;

public class TestServiceCompany {
	private ServiceCompany serviceCompany;

	public TestServiceCompany() {
		this.serviceCompany = ServiceCompany.getInstance();
	}

	@Test
	public void testGetAll() {

		DaoCompany daoCompany = Mockito.mock(DaoCompany.class);

		List<Company> list = new ArrayList<>();
		list.add(new Company(1, "test"));
		list.add(new Company(2, ""));

		when(daoCompany.getAll()).thenReturn(list);
		serviceCompany.setDaoCompany(daoCompany);
		assertEquals(list, serviceCompany.getAll());
	}
	
	@Test
	public void testGetAllNull() {

		DaoCompany daoCompany = Mockito.mock(DaoCompany.class);

		List<Company> list = new ArrayList<>();

		when(daoCompany.getAll()).thenReturn(list);
		serviceCompany.setDaoCompany(daoCompany);
		assertEquals(list, serviceCompany.getAll());
	}
}
