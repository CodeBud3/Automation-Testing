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
	public void testLoginWithInvalidCred() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("admin@yourstore.com", "admin");
		loginPage.clickLoginButton();
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(loginPage.isErrorMessagedDisplayed("signInFormErrorContainer"));
		Assert.assertTrue(loginPage.verifyErrorMessage("signInFormErrorMessage", "Incorrect email or password."));
	}
	
	@Test
	public void testLoginWithEmptyCred() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.clickLoginButton();
		Log.info("Validating Error messages");
		test.info("Validating Error messages");
		Assert.assertTrue(loginPage.verifyErrorMessage("emailErrorMessage", "Email is required."));
		Assert.assertTrue(loginPage.verifyErrorMessage("passwordErrorMessage", "Password is required."));
	}
}
