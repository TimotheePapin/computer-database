package com.excilys.formation.java.computerdatabase.webapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.formation.java.computerdatabase.model.Company;
import com.excilys.formation.java.computerdatabase.model.Computer;
import com.excilys.formation.java.computerdatabase.service.ComputerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = {"classpath:/spring-context.xml"})
public class TestSelenium {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TestSelenium.class);

	boolean acceptNextAlert = true;
	
	@Autowired
	private ComputerService serviceComputer;

	private WebDriver driver;
	
	@Before
	public void setUp() {
		serviceComputer.deleteByName("TestSelenium");
		driver = new FirefoxDriver();
		driver.get("http://localhost:7575/computer-database/dashboard");
	}

	@After
	public void close() {
		serviceComputer.deleteByName("TestSelenium");
		if (driver != null) {
			driver.close();
		}
	}

	@Test
	public void testDashboardAccess() {
		LOGGER.info("testDashboardAccess");
		assertEquals("Computer Database", driver.getTitle());
	}

	@Test
	public void testSize() {
		LOGGER.info("testSize");
		driver.findElement(By.id("orderCompanyName")).click();
		driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
		assertEquals(100, driver.findElements(By.name("computer")).size());
	}

	@Test
	public void testNameOrdering() {
		LOGGER.info("testNameOrdering");
		boolean sup = false;
		driver.findElement(By.id("orderComputerName")).click();
		String un = driver.findElements(By.name("computer")).get(0).getText();
		String deux = driver.findElements(By.name("computer")).get(5).getText();
		if (un.compareTo(deux) <= 0) {
			sup = true;
		}
		assertTrue(sup);
	}

	@Test
	public void testCompanyOrdering() {
		LOGGER.info("testCompanyOrdering");
		boolean sup = false;
		driver.findElement(By.id("orderCompanyName")).click();
		driver.findElement(By.id("orderCompanyName")).click();
		String un = driver.findElements(By.name("company")).get(0).getText();
		String deux = driver.findElements(By.name("company")).get(5).getText();
		if (un.compareTo(deux) < 0) {
			sup = true;
		}
		assertFalse(sup);
	}

	@Test
	public void testAddComputer() {
		LOGGER.info("testAddComputer");
		driver.findElement(By.id("addComputer")).click();
		driver.findElement(By.id("computerName")).clear();
		driver.findElement(By.id("computerName")).sendKeys("TestSelenium");
		driver.findElement(By.id("introduced")).clear();
		driver.findElement(By.id("introduced")).sendKeys("50/01/2016");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		assertEquals("Some field isn't fill correctly",
				closeAlertAndGetItsText());
		driver.findElement(By.id("introduced")).clear();
		driver.findElement(By.id("introduced")).sendKeys("27/01/2016");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		driver.findElement(By.id("searchbox")).clear();
		driver.findElement(By.id("searchbox")).sendKeys("TestSelenium");
		driver.findElement(By.id("searchsubmit")).click();
		assertEquals(1, driver.findElements(By.name("computer")).size());
		assertEquals("27/01/2016",
				driver.findElements(By.name("introduced")).get(0).getText());
	}

	@Test
	public void testEditComputer() {
		LOGGER.info("testEditComputer");
		serviceComputer.create(new Computer(0, "TestSelenium", null, null,
				new Company(1, "")));
		driver.findElement(By.id("searchbox")).clear();
		driver.findElement(By.id("searchbox")).sendKeys("TestSelenium");
		driver.findElement(By.id("searchsubmit")).click();
		driver.findElement(By.linkText("TestSelenium")).click();
		driver.findElement(By.id("discontinued")).clear();
		driver.findElement(By.id("discontinued")).sendKeys("27/01/2016");
		new Select(driver.findElement(By.id("companyId")))
				.selectByVisibleText("Tandy Corporation");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		driver.findElement(By.id("searchbox")).clear();
		driver.findElement(By.id("searchbox")).sendKeys("TestSelenium");
		driver.findElement(By.id("searchsubmit")).click();
		assertEquals(1, driver.findElements(By.name("computer")).size());
		assertEquals("27/01/2016",
				driver.findElements(By.name("discontinued")).get(0).getText());
		assertEquals("Tandy Corporation",
				driver.findElements(By.name("company")).get(0).getText());
	}

	@Test
	public void testDeleteComputer() {
		LOGGER.info("testDeleteComputer");
		boolean sup = false;
		System.out.println(serviceComputer.create(new Computer(0, "TestSelenium", null, null,
				new Company(1, ""))));
		driver.findElement(By.cssSelector("a.navbar-brand")).click();
		String start = driver.findElements(By.id("homeTitle")).get(0).getText();
		driver.findElement(By.id("searchbox")).clear();
		driver.findElement(By.id("searchbox")).sendKeys("Testselenium");
		driver.findElement(By.id("searchsubmit")).click();
		driver.findElement(By.id("editComputer")).click();
		driver.findElement(By.id("selectall")).click();
		driver.findElement(By.xpath("//a[@id='deleteSelected']/i")).click();
		assertTrue(closeAlertAndGetItsText().matches(
				"^Are you sure you want to delete the selected computers[\\s\\S]$"));
		String end = driver.findElements(By.id("homeTitle")).get(0).getText();
		if (start.compareTo(end) > 0) {
			sup = true;
		}
		assertTrue(sup);
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				acceptNextAlert = false;
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
