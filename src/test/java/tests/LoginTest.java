package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExtentReportManager;
import utils.Log;


public class LoginTest extends BaseTest {

	@Test
	public void testValidLogin() {
		test = ExtentReportManager.createTest("Login test");
		test.info("Navigating to login page");
		LoginPage loginPage = new LoginPage(driver);
		test.info("Adding Credentials");
		loginPage.enterUsername("admin@yourstore.com");
		loginPage.enterPassword("admin");
		test.info("Clicking login button");
		loginPage.clickLogin();
		Log.info("Validating title");
		test.info("Validating title");
		Assert.assertEquals(driver.getTitle(), "Just a moment...");
	}
	
	@Test
	public void testInvalidLogin() {
		test = ExtentReportManager.createTest("Login test");
		test.info("Navigating to login page");
		LoginPage loginPage = new LoginPage(driver);
		test.info("Adding Credentials");
		loginPage.enterUsername("admin123@yourstore.com");
		loginPage.enterPassword("admin123");
		test.info("Clicking login button");
		loginPage.clickLogin();
		Log.info("Validating title");
		test.info("Validating title");
		Assert.assertEquals(driver.getTitle(), "Just a moment..");
	}
}
