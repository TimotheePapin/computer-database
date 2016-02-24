package com.excilys.formation.java.computerdatabase.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.annotation.PostConstruct;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.formation.java.computerdatabase.CompanyDAO;
import com.excilys.formation.java.computerdatabase.CompanyService;
import com.excilys.formation.java.computerdatabase.ComputerService;
import com.excilys.formation.java.computerdatabase.database.FakeDatabase;
import com.excilys.formation.java.computerdatabase.model.Company;
import com.excilys.formation.java.computerdatabase.model.Computer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = {"classpath:/spring-test-context.xml"})
public class TestServiceCompany {
	
	@Autowired
	private FakeDatabase fakeDatabase;
	
	@Autowired
	private ComputerService computerService;
	
	@Autowired
	private CompanyService companyService;
	
	@PostConstruct
	public void init() {
		fakeDatabase.initTestDb();
	}
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFailTransaction() {
		Company company = companyService.create(new Company (0,"CompanyTest"));
		Computer computer = computerService.create(new Computer(0,"ComputerTest",null,null,company));
		assertEquals(computer, computerService.getById(computer.getId()));
		CompanyDAO companyDAO = Mockito.mock(CompanyDAO.class);
		Mockito.doThrow(new RuntimeException()).when(companyDAO).deleteById(company.getId());
		
		companyService.setCompanyDAO(companyDAO);
		try {
			companyService.deleteById(company.getId());
			fail("no exception");
		} catch (RuntimeException expected) {
			
		}
		assertEquals(computer, computerService.getById(computer.getId()));
	}
	
}
