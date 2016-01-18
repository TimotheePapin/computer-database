package com.excilys.formation.java.computerDatabase.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.excilys.formation.java.computerDatabase.model.Company;
import com.excilys.formation.java.computerDatabase.model.Computer;
import com.excilys.formation.java.computerDatabase.persistence.DaoComputer;

public class TestServiceComputer {
	private ServiceComputer serviceComputer;
	private DaoComputer daoComputer;

	public TestServiceComputer() {
		this.serviceComputer = ServiceComputer.getInstance();
	}

	@Before
	public void setUp() {
		daoComputer = Mockito.mock(DaoComputer.class);
	}

	@After
	public void tearDown() {
		daoComputer = null;
	}

	@Test
	public void testGetAll() {
		LocalDateTime date = LocalDateTime.of(2016, 01, 15, 0, 0, 0, 0);
		List<Computer> list = new ArrayList<>();
		list.add(new Computer(1, "test", null, date, new Company(1, "test")));
		list.add(new Computer(2, "", date, null, new Company(2, "")));

		when(daoComputer.getAll()).thenReturn(list);
		serviceComputer.setDaoComputer(daoComputer);
		assertEquals(list, serviceComputer.getAll());
	}

	@Test
	public void testGetAllNull() {

		List<Computer> list = new ArrayList<>();

		when(daoComputer.getAll()).thenReturn(list);
		serviceComputer.setDaoComputer(daoComputer);
		assertEquals(list, serviceComputer.getAll());
	}

	@Test
	public void testGetByName() {
		LocalDateTime date = LocalDateTime.of(2016, 01, 15, 0, 0, 0, 0);
		Computer computer = new Computer(1, "test", date, null, new Company(1, "test"));

		when(daoComputer.getByName("test")).thenReturn(computer);
		serviceComputer.setDaoComputer(daoComputer);
		assertEquals(computer, serviceComputer.getByName("test"));
	}

	@Test
	public void testGetById() {
		LocalDateTime date = LocalDateTime.of(2016, 01, 15, 0, 0, 0, 0);
		Computer computer = new Computer(1, "test", date, null, new Company(1, "test"));

		when(daoComputer.getById(1)).thenReturn(computer);
		serviceComputer.setDaoComputer(daoComputer);
		assertEquals(computer, serviceComputer.getById(1));
	}
	
	@Test
	public void testUpdate() {
		LocalDateTime date = LocalDateTime.of(2016, 01, 15, 0, 0, 0, 0);
		Computer computer = new Computer(1, "test", date, null, new Company(1, "test"));

		when(daoComputer.update(computer)).thenReturn(computer);
		serviceComputer.setDaoComputer(daoComputer);
		assertEquals(computer, serviceComputer.update(computer));
	}
	
	@Test
	public void testAdd() {
		LocalDateTime date = LocalDateTime.of(2016, 01, 15, 0, 0, 0, 0);
		Computer computer = new Computer(1, "test", date, null, new Company(1, "test"));

		when(daoComputer.add(computer)).thenReturn(computer);
		serviceComputer.setDaoComputer(daoComputer);
		assertEquals(computer, serviceComputer.create(computer));
	}
}
