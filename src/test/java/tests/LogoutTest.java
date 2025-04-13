package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import enums.ActionTypes.ActionType;
import enums.CommonLocators.CommonPageLocators;
import enums.DashboardPageLocators.DashboardLocators;
import utils.ConfigReader;
import utils.ExcelDataProvider;
import utils.ExtentReportManager;
import utils.Log;
import utils.RetryAnalyzer;
import pages.LogoutPage;
import enums.SideNavLocators.SideNavLocator;
import java.lang.reflect.Method;
import java.util.Map;


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
		logoutPage = new LogoutPage(getDriver(), getActionDriver());
		logoutPage.navigateToLogoutPage();
		Log.info("Navigating to Creating Account Page...");
		test.info("Navigating to Creating Account Page...");
		Log.info("Running " + testMethodName + " on Thread ID: " + Thread.currentThread().getId());
	}
	
	protected void populateLoginFields(Map<String, String> data){
		logoutPage.populateLoginFields(data.get("Email"), data.get("Password"));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
	public void testLogoutPage(Map<String, String>data) {
		String[] expectedResults = data.get("Expected Result").split("\n");
		String expectedDashboardMessgae = expectedResults[0];
		String expectedSignInPageTitle = expectedResults[1];
		populateLoginFields(data);
		getActionDriver().performAction(ActionType.CLICK, CommonPageLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE, "Hello, "+expectedDashboardMessgae));
		getActionDriver().performAction(ActionType.CLICK, SideNavLocator.USER_PROFILE);
		getActionDriver().performAction(ActionType.CLICK, SideNavLocator.LOGOUT);
		Log.info("Validating Successful Logout");
		test.info("Validating Successful Logout");
		Assert.assertTrue(getActionDriver().verifyMessage(CommonPageLocators.SIGN_IN_PAGE, expectedSignInPageTitle));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
	public void verifyResetPasswordPageNotAccessableAfterLogin(Map<String, String> data) {
		populateLoginFields(data);
		getActionDriver().performAction(ActionType.CLICK, CommonPageLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, "+data.get("Expected Result")));
		String baseUrl = ConfigReader.getProperty("url");
		Log.info("navigating to reset password");
		String resetPasswordUrl = baseUrl + "/reset-password";
		getDriver().navigate().to(resetPasswordUrl);
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, "+data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
	public void verifySignUpNotAccessibleAfterLogin(Map<String, String> data) {
		populateLoginFields(data);
		getActionDriver().performAction(ActionType.CLICK, CommonPageLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, " + data.get("Expected Result")));
		String baseUrl = ConfigReader.getProperty("url");
		Log.info("navigating to sing up page");
		String singUpUrl = baseUrl + "/signup";
		getDriver().navigate().to(singUpUrl);
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, " + data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void verifyLoginPageNotAccessibleAfterLogin(Map<String, String> data) {
		populateLoginFields(data);
		getActionDriver().performAction(ActionType.CLICK, CommonPageLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, " + data.get("Expected Result")));
		getDriver().navigate().to(ConfigReader.getProperty("url"));
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, " + data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void verifyDashoardPageNotAccessibleBeforeLogin(Map<String, String> data) {
		String baseUrl = ConfigReader.getProperty("url");
		Log.info("Navigating to Dashboard Page");
		String dashboardUrl = baseUrl + "/dashboard";
		getDriver().navigate().to(dashboardUrl);
		Assert.assertTrue(getActionDriver().verifyMessage(CommonPageLocators.SIGN_IN_PAGE, data.get("Expected Result")));
	}
}	