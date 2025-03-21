/*package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LogoutPage;
import utils.ExtentReportManager;
import utils.Log;

import java.lang.reflect.Method;

public class LogoutTest extends BaseTest {
	
	private LogoutPage logoutPage;
	@BeforeMethod
	public void setupPage(Method method) {
		// Use reflection to get the test method name
        String testMethodName = method.getName();
        // Set the test title dynamically
        test = ExtentReportManager.createTest(testMethodName);
		test.info("Navigating to login page");
		logoutPage = new LogoutPage(driver);
	}
	@Test
	public void testLogoutFromDashboard() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		logoutPage.login("john.doe@example.com", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Assert.assertEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/login");
	}
}*/