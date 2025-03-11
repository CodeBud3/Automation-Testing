package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExtentReportManager;
import utils.Log;


public class LoginTest extends BaseTest {
	
	private LoginPage loginPage;
	@BeforeMethod
	public void setupPage() {
		test = ExtentReportManager.createTest("Login test");
		test.info("Navigating to login page");
		loginPage = new LoginPage(driver);
	}
	
	@Test
	public void testValidLogin() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.login("admin@yourstore.com", "admin");
		Log.info("Validating title");
		test.info("Validating title");
		Assert.assertEquals(driver.getTitle(), "Just a moment...");
	}
	
	@Test
	public void testInvalidLogin() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.login("admin@yourstore.com", "admin");
		Log.info("Validating title");
		test.info("Validating title");
		Assert.assertEquals(driver.getTitle(), "Just a moment..");
	}
}
