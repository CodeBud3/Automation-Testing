/*package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExtentReportManager;
import utils.Log;

import java.lang.reflect.Method;

public class LoginTest extends BaseTest {
	
	private LoginPage loginPage;
	@BeforeMethod
	public void setupPage(Method method) {
		// Use reflection to get the test method name
        String testMethodName = method.getName();
        // Set the test title dynamically
        test = ExtentReportManager.createTest(testMethodName);
		test.info("Navigating to login page");
		loginPage = new LoginPage(driver);
	}
	@Test
	public void testValidLogin() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.login("john.doe@example.com", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(6000);
		Assert.assertEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testInvalidEmail() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.login("invalid.email", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(6000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testInvalidPassword() throws InterruptedException  {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.login("john.doe@example.com", "WrongPass123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(6000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testInvalidRememberMe() throws InterruptedException  {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.login("john.doe@example.com", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(6000);
		Assert.assertEquals(driver.getTitle(), "To Do");
	}
	@Test
	public void testEmptyEmailField() throws InterruptedException  {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.login("", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(6000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testEmptyPasswordField() throws InterruptedException  {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.login("john.doe@example.com", "");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(6000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testSQLInjectionAttemptEmail() throws InterruptedException  {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.login("'1'='1'", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(6000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testXSSAttemptEmail() throws InterruptedException  {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.login("<script>alert('XSS')</script>", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(6000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testCaseSensitivityEmail() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.login("JOHN.DOG@example.com", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(6000);
		Assert.assertEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testSpecialCharactersEmail() throws InterruptedException  {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.login("john.doe+test@example.com", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(6000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testSQLInjectionAttemptPassword() throws InterruptedException  {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.login("john.doe@example.com", "'1'='1'");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(6000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testXSSAttemptPassword() throws InterruptedException  {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.login("john.doe@example.com", "<script>alert('XSS')</script>");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(6000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testMaximumEmailLength() throws InterruptedException  {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.login("longemailaddresswithmultiplecharactersandnumbers1234567890abcdefghijklmnopqrstuvwx@domainexample.com", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(6000);
		Assert.assertEquals(driver.getTitle(), "To Do");
	}
	@Test
	public void testMaximumPasswordLength() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.login("johnb.doe@example.com", "ApplebananaCherrydogLemonfishGrapeHorseiglooJuice7!xyz1ABCD1234");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(6000);
		Assert.assertEquals(driver.getTitle(), "To Do");
	}
}*/