package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LogoutPage;
import utils.ExtentReportManager;
import utils.Log;

import java.lang.reflect.Method;
import enums.ActionTypes.ActionType;
import enums.LoginPageLocatorEnum.ElementLocators;

public class LogoutTest extends BaseTest {
	// mvn test -Dtest=LogoutTest
	private LogoutPage logoutPage;

	@BeforeMethod
	public void setupPage(Method method) {
		// Use reflection to get the test method name
		String testMethodName = method.getName();
		// Set the test title dynamically
		test = ExtentReportManager.createTest(testMethodName);
		test.info("Navigating to login page");
		logoutPage = new LogoutPage(driver);
		Log.info("Navigating to login page");
		test.info("Navigating to login page");
	}
	
	@Test
	public void testValidLogin() {
		logoutPage.populateLoginFields("john.doe@example.com", "Password123!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		actionDriver.performAction(ActionType.CLICK, ElementLocators.LOGOUT);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(logoutPage.verifyMessage(ElementLocators.SIGN_IN_PAGE, "Sign in to your account"));	
	}	
}