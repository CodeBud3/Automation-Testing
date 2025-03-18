package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.createAccount;
import utils.ExtentReportManager;
import utils.Log;


public class createAccountTest extends BaseTest {
	
	private createAccount createAccount;
	@BeforeMethod
	public void setupPage() {
		test = ExtentReportManager.createTest("Login test");
		test.info("Navigating to login page");
		createAccount = new createAccount(driver);
	}
	
	@Test
	public void testSuccessfulAccountCreation() {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.create("John", "doe", "john.doe@example.com", "Password123!", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Assert.assertEquals(driver.getTitle(), "To Do");
	}
}