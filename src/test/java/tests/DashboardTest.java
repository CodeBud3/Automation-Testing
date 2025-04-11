package tests;

import java.lang.reflect.Method;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import enums.ActionTypes.ActionType;
import enums.DashboardPageLocators.DashboardLocators;
import enums.SideNavLocators.SideNavLocator;
import pages.DashboardPage;
import utils.ExcelDataProvider;
import utils.ExtentReportManager;
import utils.Log;

public class DashboardTest extends BaseTest {
	// mvn test -Dtest=DashboardTest
	private DashboardPage dashboardPage;
	
	@BeforeMethod
	public void setupPage(Method method) {
		// Use reflection to get the test method name
		String testMethodName = method.getName();
		// Set the test title dynamically
		test = ExtentReportManager.createTest(testMethodName);
		test.info("Navigating to login page");
		dashboardPage = new DashboardPage(getDriver(), getActionDriver());
		Log.info("Navigating to login page");
		test.info("Navigating to login page");
		System.out.println("Thread ID: " + Thread.currentThread().getId());
	}
	protected void performLoginAction(Map<String, String> data) {
		dashboardPage.populateLoginFields(
			data.get("Email"), 
			data.get("Password")
        );
    }
	
	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testDashboardPage(Map<String, String> data) {
		performLoginAction(data);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, "+data.get("Expected Result")));
	}
	
	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testTaskHomeProfilePage(Map<String, String>data) {
		String[] expectedResults = data.get("Expected Result").split("\n");
		String expectedTaskPageTitle = expectedResults[0];
		String expectedHomePageTitle = expectedResults[1];
		String expectedSginInPageTitle = expectedResults[2];
		performLoginAction(data);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		getActionDriver().performAction(ActionType.CLICK, SideNavLocator.USER_TASK_BUTTON);
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.TASK_TAB_PAGE, expectedTaskPageTitle));
		getActionDriver().performAction(ActionType.CLICK, SideNavLocator.USER_HOME_BUTTON);
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,"Hello, "+expectedHomePageTitle));
		getActionDriver().performAction(ActionType.CLICK, SideNavLocator.USER_PROFILE);
		getActionDriver().performAction(ActionType.CLICK, SideNavLocator.LOGOUT);
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.SIGN_IN_PAGE,expectedSginInPageTitle));	
	}
	
	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testVerfiySideButton(Map<String, String>data) {
		performLoginAction(data);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.PROFILE_EMAIL, data.get("Expected Result")));
		getActionDriver().performAction(ActionType.CLICK, SideNavLocator.SIDE_NAV_BUTTON);	
		Assert.assertFalse(getActionDriver().verifyMessage(DashboardLocators.PROFILE_EMAIL, data.get("Expected Result")));
	}
	
	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testVerfiyAddTaskButton(Map<String, String>data) {
		String[] expectedResults = data.get("Expected Result").split("\n");
		String expectedHomePageTitle = expectedResults[0];
		String expectedTaskPageTitle = expectedResults[1];
		performLoginAction(data);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,"Hello, "+expectedHomePageTitle));
		getActionDriver().performAction(ActionType.CLICK, SideNavLocator.USER_TASK_BUTTON);
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.TASK_TAB_PAGE, expectedTaskPageTitle));
		getActionDriver().performAction(ActionType.CLICK, DashboardLocators.ADD_TASK);
		Assert.assertFalse(getActionDriver().verifyMessage(DashboardLocators.TASK_TITLE, ""));
	}	
}