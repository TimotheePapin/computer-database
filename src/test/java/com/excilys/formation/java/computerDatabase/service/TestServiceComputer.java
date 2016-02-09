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
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.java.computerdatabase.model.Company;
import com.excilys.formation.java.computerdatabase.model.Computer;
import com.excilys.formation.java.computerdatabase.persistence.ComputerDAO;
import com.excilys.formation.java.computerdatabase.service.impl.ComputerServiceImpl;

public class TestServiceComputer {
	@Autowired
	private ComputerServiceImpl serviceComputer;
	private ComputerDAO daoComputer;
	private List<Computer> list;
	private LocalDateTime date = LocalDateTime.of(2016, 01, 15, 0, 0, 0, 0);
	private Computer computer = new Computer(1, "test", date, null, new Company(1, "test"));

	@Before
	public void setUp() {
		daoComputer = Mockito.mock(ComputerDAO.class);
		list = new ArrayList<>();
	}

	@After
	public void tearDown() {
		daoComputer = null;
	}

	@Test
	public void testGetAll() {
		list.add(new Computer(1, "test", null, date, new Company(1, "test")));
		list.add(new Computer(2, "", date, null, new Company(2, "")));

		when(daoComputer.getAll()).thenReturn(list);
		serviceComputer.setDaoComputer(daoComputer);
		assertEquals(list, serviceComputer.getAll());
	}

	@Test
	public void testGetAllNull() {
		when(daoComputer.getAll()).thenReturn(list);
		serviceComputer.setDaoComputer(daoComputer);
		assertEquals(list, serviceComputer.getAll());
	}

	@Test
	public void testGetByName() {
		when(daoComputer.getByName("test")).thenReturn(computer);
		serviceComputer.setDaoComputer(daoComputer);
		assertEquals(computer, serviceComputer.getByName("test"));
	}

	@Test
	public void testGetById() {
		when(daoComputer.getById(1)).thenReturn(computer);
		serviceComputer.setDaoComputer(daoComputer);
		assertEquals(computer, serviceComputer.getById(1));
	}
	
	@Test
	public void testUpdate() {
		when(daoComputer.update(computer)).thenReturn(computer);
		serviceComputer.setDaoComputer(daoComputer);
		assertEquals(computer, serviceComputer.update(computer));
	}
	
	@Test
	public void testAdd() {
		when(daoComputer.add(computer)).thenReturn(computer);
		serviceComputer.setDaoComputer(daoComputer);
		assertEquals(computer, serviceComputer.create(computer));
	}
}
