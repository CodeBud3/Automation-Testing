package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import enums.ActionTypes.ActionType;
import enums.DashboardPageLocators.DashboardLocators;
import utils.ExtentReportManager;
import utils.Log;
import pages.LogoutPage;
import enums.LogoutPageLocatorEnum.ElementLocators;
import enums.SideNavLocators.SideNavLocator;
import factory.DriverFactory;

import java.lang.reflect.Method;


public class LogoutTest extends BaseTest {
	
	private LogoutPage logoutPage;
	// mvn test -Dtest=LogoutTest
	@BeforeMethod
	public void setupPage(Method method) {
		// Use reflection to get the test method name
        String testMethodName = method.getName();
        // Set the test title dynamically
        test = ExtentReportManager.createTest(testMethodName);
		test.info("Navigating to Sign up page");
		logoutPage = new LogoutPage(DriverFactory.getDriver(), actionDriver);
		logoutPage.navigateToLogoutPage();
		Log.info("Navigating to Creating Account Page...");
		test.info("Navigating to Creating Account Page...");
		System.out.println("Thread ID: " + Thread.currentThread().getId());
	}
	
	@Test
	public void testLogoutPage() {
		logoutPage.populateLoginFields("john.doe@example.com", "Password123!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(actionDriver.verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE, "Hello, john"));
		actionDriver.performAction(ActionType.CLICK, SideNavLocator.USER_PROFILE);
		actionDriver.performAction(ActionType.CLICK, SideNavLocator.LOGOUT);
		Log.info("Validating Successful Logout");
		test.info("Validating Successful Logout");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.SIGN_IN_PAGE, "Sign in to your account"));
	}
}