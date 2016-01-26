package com.excilys.formation.java.computerDatabase.webapp;


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestFirefox {

	private WebDriver driver;

	@Before
	public void setUp() {
		driver = new FirefoxDriver();
		driver.get("http://localhost:7575/computer-database/dashboard");
	}

	@After
	public void close() {
		if (driver != null) {
			driver.close();
		}
	}

	@Test
	public void test() {
		assertEquals("567 Computers found", driver.findElement(By.id("homeTitle")).getText());
		// selenium.open("/computer-database/dashboard");
		// assertTrue(selenium.isTextPresent("Computers found"));
	}

	/*
	 * private WebDriver driver; private String baseUrl; private boolean
	 * acceptNextAlert = true; private StringBuffer verificationErrors = new
	 * StringBuffer();
	 * 
	 * @Before public void setUp() throws Exception { driver = new
	 * FirefoxDriver(); baseUrl = "http://localhost:7575/";
	 * driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); }
	 * 
	 * @Test public void testFirefox() throws Exception { driver.get(baseUrl +
	 * "/computer-database/dashboard");
	 * driver.findElement(By.cssSelector("a.navbar-brand")).click();
	 * driver.findElement(By.id("orderCompanyName")).click();
	 * driver.findElement(By.id("orderCompanyName")).click();
	 * driver.findElement(By.id("addComputer")).click();
	 * driver.findElement(By.id("computerName")).clear();
	 * driver.findElement(By.id("computerName")).sendKeys("test");
	 * driver.findElement(By.id("introduced")).clear();
	 * driver.findElement(By.id("introduced")).sendKeys("25/01/2016");
	 * driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	 * 
	 * 
	 * driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
	 * driver.findElement(By.id("switchPageAdd2")).click();
	 * driver.findElement(By.id("switchPageAdd2")).click();
	 * driver.findElement(By.linkText("»")).click();
	 * driver.findElement(By.linkText("test")).click();
	 * driver.findElement(By.id("discontinued")).clear();
	 * driver.findElement(By.id("discontinued")).sendKeys("50/56/3695");
	 * driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	 * assertEquals("Some field isn't fill correctly",
	 * closeAlertAndGetItsText());
	 * driver.findElement(By.id("discontinued")).clear();
	 * driver.findElement(By.id("discontinued")).sendKeys("25/01/2016");
	 * driver.findElement(By.id("introduced")).clear();
	 * driver.findElement(By.id("introduced")).sendKeys(""); //new
	 * Select(driver.findElement(By.id("companyId"))).selectByVisibleText(
	 * "Commodore International");
	 * driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	 * driver.findElement(By.id("searchbox")).clear();
	 * driver.findElement(By.id("searchbox")).sendKeys("test");
	 * driver.findElement(By.id("searchsubmit")).click();
	 * driver.findElement(By.id("editComputer")).click();
	 * driver.findElement(By.xpath("(//input[@name='cb'])[3]")).click();
	 * driver.findElement(By.xpath("//a[@id='deleteSelected']/i")).click();
	 * assertTrue(closeAlertAndGetItsText().matches(
	 * "^Are you sure you want to delete the selected computers[\\s\\S]$"));
	 * driver.findElement(By.id("searchbox")).clear();
	 * driver.findElement(By.id("searchbox")).sendKeys("ppl");
	 * driver.findElement(By.id("searchsubmit")).click();
	 * driver.findElement(By.id("orderComputerName")).click();
	 * driver.findElement(By.id("orderComputerName")).click(); }
	 * 
	 * @After public void tearDown() throws Exception { driver.quit(); String
	 * verificationErrorString = verificationErrors.toString(); if
	 * (!"".equals(verificationErrorString)) { fail(verificationErrorString); }
	 * }
	 * 
	 * private boolean isElementPresent(By by) { try { driver.findElement(by);
	 * return true; } catch (NoSuchElementException e) { return false; } }
	 * 
	 * private boolean isAlertPresent() { try { driver.switchTo().alert();
	 * return true; } catch (NoAlertPresentException e) { return false; } }
	 * 
	 * private String closeAlertAndGetItsText() { try { Alert alert =
	 * driver.switchTo().alert(); String alertText = alert.getText(); if
	 * (acceptNextAlert) { alert.accept(); } else { alert.dismiss(); } return
	 * alertText; } finally { acceptNextAlert = true; } }
	 */
}
