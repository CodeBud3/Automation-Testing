package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;
import utils.ExcelDataProvider;
import utils.ConfigReader;
import utils.ExtentReportManager;
import utils.Log;
import utils.RetryAnalyzer;

import java.lang.reflect.Method;
import java.util.Map;
import enums.ActionTypes.ActionType;
import enums.DashboardPageLocators.DashboardLocators;
import enums.LoginPageLocatorEnum.ElementLocators;

public class LoginTest extends BaseTest {
	// mvn test -Dtest=LoginTest
	private LoginPage loginPage;

	@BeforeMethod
	public void setupPage(Method method) {
		// Use reflection to get the test method name
		String testMethodName = method.getName();
		// Set the test title dynamically
		test = ExtentReportManager.createTest(testMethodName);
		test.info("Navigating to login page");
		loginPage = new LoginPage(getDriver(), getActionDriver());
		Log.info("Navigating to login page");
		test.info("Navigating to login page");
		System.out.println("Thread ID: " + Thread.currentThread().getId());
	}
	protected void performLoginAction(Map<String, String> data) {
		loginPage.populateLoginFields(
			data.get("Email"), 
			data.get("Password")
        );
    }
	
	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testValidLogin(Map<String, String> data) {
		performLoginAction(data);
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, " + data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testLoginWithInvalidCred(Map<String, String> data) {
		performLoginAction(data);
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(getActionDriver().isElementDisplayed(ElementLocators.SIGN_IN_FORM_CONTAINER));
		Assert.assertTrue(
				getActionDriver().verifyMessage(ElementLocators.SIGN_IN_FORM_MESSAGE, data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testCaseSensitivityEmail(Map<String, String> data) {
		performLoginAction(data);
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, " + data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testPassVisToggle(Map<String, String> data) {
		// Split the expected result into two parts: password and text
		String[] expectedResults = data.get("Expected Result").split("\n");
		String expectedInitialType = expectedResults[0]; // "password"
		String expectedToggledType = expectedResults[1]; // "text"
		performLoginAction(data);
		Assert.assertTrue(getActionDriver().verifyAttribute(ElementLocators.PASSWORD_VISIBILITY_TOGGLE, "type",
				expectedInitialType), "Expected password field to be type='password' initially");
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.PASS_VIS_TOGGLE);
		Assert.assertTrue(getActionDriver().verifyAttribute(ElementLocators.PASSWORD_VISIBILITY_TOGGLE, "type",
				expectedToggledType), "Expected password field to be type='text' after toggling visibility");
		Log.info("Validating text");
		test.info("Validating text");
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testSpecialCharEmail(Map<String, String> data) {
		performLoginAction(data);
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, " + data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
	public void testSignInWithRememberMe(Map<String, String> data) {
		String[] expectedResults = data.get("Expected Result").split("\n");
		String expectedWelcomeMessage = expectedResults[0];
		String expectedEmailText = expectedResults[1];
		performLoginAction(data);
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.REMEMBER_ME);
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, " + expectedWelcomeMessage));
		loginPage.performLogoutAction();
		Assert.assertTrue(getActionDriver().verifyAttribute(ElementLocators.EMAIL_TEXT, "value", expectedEmailText));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testMaxEmailAndPassLength(Map<String, String> data) {
		performLoginAction(data);
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, " + data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testLoginWithEmptyCred(Map<String, String> data) {
		String[] expectedResults = data.get("Expected Result").split("\n");
		String expectedEmailErrorMessage = expectedResults[0];
		String expectedPasswordErrorMessage = expectedResults[1];
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.EMAIL_MESSAGE, expectedEmailErrorMessage));
		Assert.assertTrue(
				getActionDriver().verifyMessage(ElementLocators.PASSWORD_MESSAGE, expectedPasswordErrorMessage));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testLoginWithInvalidPassword(Map<String, String> data) {
		performLoginAction(data);
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(
				getActionDriver().verifyMessage(ElementLocators.SIGN_IN_FORM_MESSAGE, data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testNavSignUpPage(Map<String, String> data) {
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.NAV_SIGN_UP);
		Log.info("Validating messages on the registration page");
		test.info("Validating messages on the registration page");
		Assert.assertTrue(getActionDriver().isElementDisplayed(ElementLocators.REGISTRATION_PAGE));
		Assert.assertTrue(
				getActionDriver().verifyMessage(ElementLocators.REGISTRATION_PAGE, data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testClickSignUpButton(Map<String, String> data) {
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.SIGN_UP);
		Log.info("Validating messages on the registration page");
		test.info("Validating messages on the registration page");
		Assert.assertTrue(getActionDriver().isElementDisplayed(ElementLocators.REGISTRATION_PAGE));
		Assert.assertTrue(
				getActionDriver().verifyMessage(ElementLocators.REGISTRATION_PAGE, data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testNavForgotPassword(Map<String, String> data) {
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.FORGOT_PASSWORD);
		Log.info("Validating messages on the ForgotPassword page");
		test.info("Validating messages on the ForgotPassword page");
		Assert.assertTrue(getActionDriver().isElementDisplayed(ElementLocators.FORGOT_PASSWORD_PAGE));
		Assert.assertTrue(
				getActionDriver().verifyMessage(ElementLocators.FORGOT_PASSWORD_PAGE, data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testSqlInjectionAttemptEmail(Map<String, String> data) {
		performLoginAction(data);
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.EMAIL_MESSAGE, data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testXSSAttemptEmail(Map<String, String> data) {
		performLoginAction(data);
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.EMAIL_MESSAGE, data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testMultipleFailedSignin(Map<String, String> data) {
		for (int attempt = 1; attempt <= 5; attempt++) {
			Log.info("Attempt " + attempt + ": Logging in with incorrect credentials...");
			test.info("Attempt " + attempt + ": Logging in with incorrect credentials...");
			performLoginAction(data);
			getActionDriver().performAction(ActionType.CLICK, ElementLocators.LOGIN);
			// Validate the "Incorrect email or password" error message
			Log.info("Validating error message for attempt " + attempt);
			test.info("Validating error message for attempt " + attempt);
			Assert.assertTrue(
					getActionDriver().verifyMessage(ElementLocators.SIGN_IN_FORM_MESSAGE, data.get("Expected Result")));
		}
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testSqlInjectionAttemptPassword(Map<String, String> data) {
		performLoginAction(data);
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(
				getActionDriver().verifyMessage(ElementLocators.SIGN_IN_FORM_MESSAGE, data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testXSSAttemptPassword(Map<String, String> data) {
		performLoginAction(data);
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(
				getActionDriver().verifyMessage(ElementLocators.SIGN_IN_FORM_MESSAGE, data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testNavigateToGooleAuth(Map<String, String> data) {
		String[] expectedResults = data.get("Expected Result").split("\n");
		String expectedSignInTitle = expectedResults[0];
		String expectedEmailTextlabel = expectedResults[1];
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.GOOGLE_AUTH);
		Log.info("Validating Google Sign In Page");
		test.info("Validating Microsoft Sign In Page");
		Assert.assertTrue(getActionDriver().isElementDisplayed(ElementLocators.GOOGLE_SIGN_IN));
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.GOOGLE_SIGN_IN, expectedSignInTitle));
		Assert.assertTrue(getActionDriver().isElementDisplayed(ElementLocators.GOOGLE_EMAIL));
		Assert.assertTrue(
				getActionDriver().verifyAttribute(ElementLocators.GOOGLE_EMAIL, "type", expectedEmailTextlabel),
				"Expected Email field to be type='email' after Navigating to Google Login Page");
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testNavigateToMicrosoftAuth(Map<String, String> data) {
		String[] expectedResults = data.get("Expected Result").split("\n");
		String expectedSignInTitle = expectedResults[0];
		String expectedEmailTextlabel = expectedResults[1];
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.MICROSOFT_AUTH);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(getActionDriver().isElementDisplayed(ElementLocators.MICROSOFT_SIGN_IN));
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.MICROSOFT_SIGN_IN, expectedSignInTitle));
		Assert.assertTrue(getActionDriver().isElementDisplayed(ElementLocators.MICROSOFT_EMAIL));
		Assert.assertTrue(
				getActionDriver().verifyAttribute(ElementLocators.MICROSOFT_EMAIL, "type", expectedEmailTextlabel),
				"Expected Email field to be type='email' after Navigating to Microsoft Login Page");
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testToolongEmailAndPasswordLength(Map<String, String> data) {
		performLoginAction(data);
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.EMAIL_MESSAGE, data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void verifyResetPasswordNotAccessibleAfterLogin(Map<String, String> data) {
		performLoginAction(data);
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, " + data.get("Expected Result")));
		String baseUrl = ConfigReader.getProperty("url");
		Log.info("navigating to reset password");
		String resetPasswordUrl = baseUrl + "/reset-password";
		getDriver().navigate().to(resetPasswordUrl);
		// Assert.assertFalse(getActionDriver().isElementDisplayed(ElementLocators.SAVE_PASSWORD.getLocator()));
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, " + data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void verifySignUpNotAccessibleAfterLogin(Map<String, String> data) {
		performLoginAction(data);
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, " + data.get("Expected Result")));
		String baseUrl = ConfigReader.getProperty("url");
		Log.info("navigating to sing up page");
		String singUpUrl = baseUrl + "/signup";
		getDriver().navigate().to(singUpUrl);
		// Assert.assertFalse(getActionDriver().isElementDisplayed(ElementLocators.CREATE_ACCOUNT.getLocator()));
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, " + data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void verifyLoginPageNotAccessibleAfterLogin(Map<String, String> data) {
		performLoginAction(data);
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, " + data.get("Expected Result")));
		getDriver().navigate().to(ConfigReader.getProperty("url"));
		// Assert.assertFalse(getActionDriver().isElementDisplayed(ElementLocators.LOGIN.getLocator()));
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, " + data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void verifyDashoardPageNotAccessibleBeforeLogin(Map<String, String> data) {
		String baseUrl = ConfigReader.getProperty("url");
		Log.info("Navigating to Dashboard Page");
		String dashboardUrl = baseUrl + "/dashboard";
		getDriver().navigate().to(dashboardUrl);
		// Assert.assertFalse(getActionDriver().isElementDisplayed(ElementLocators.LOGOUT.getLocator()));
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.LOGIN, data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void verifyResetPasswordTokenNotAccessibleAfterLogin(Map<String, String> data) {
		performLoginAction(data);
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, " + data.get("Expected Result")));
		String baseUrl = ConfigReader.getProperty("url");
		String resetPasswordkeyUrl = baseUrl + "/reset-password?token=333";
		getDriver().navigate().to(resetPasswordkeyUrl);
		// Assert.assertFalse(getActionDriver().isElementDisplayed(ElementLocators.SAVE_PASSWORD.getLocator()));
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, " + data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void verifyResetPasswordTokenkeyNotAccessibleAfterLogin(Map<String, String> data) {
		performLoginAction(data);
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, " + data.get("Expected Result")));
		String baseUrl = ConfigReader.getProperty("url");
		String token = ConfigReader.getProperty("ResetPasswordUserToken");
		String resetPasswordkeyUrl = baseUrl + "/reset-password?token=" + token;
		getDriver().navigate().to(resetPasswordkeyUrl);
		// Assert.assertFalse(getActionDriver().isElementDisplayed(ElementLocators.SAVE_PASSWORD.getLocator()));
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, " + data.get("Expected Result")));
	}

//	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
//	public void testSignInWithGoogleAuth(Map<String, String> data) {
//		// Click the Google Auth button
//		Log.info("Logging in with Google Auth...");
//		test.info("Logging in with Google Auth...");
//		loginPage.clickButton(ButtonType.GOOGLE_AUTH);
//
//		// Enter email and click Next
//		loginPage.enterText(EnterText.GOOGLE_EMAIL, "punith7760kumar@gmail.com");
//		loginPage.clickButton(ButtonType.GOOGLE_SIGN_IN);
//
//		// Enter password and click Next
//		loginPage.enterText(EnterText.GOOGLE_PASSWORD, "7760502966punitH");
//		loginPage.clickButton(ButtonType.GOOGLE_SIGN_IN);
//
//		// Wait for the redirect to the logged-in page
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.urlContains("/dashboard"));
//
//		// Verify the user is logged in
//		Log.info("Validating login...");
//		test.info("Validating login...");
//		Assert.assertTrue(getActionDriver().verifyMessage("logoutButton", "Logout punith"),
//				"Expected 'Logout punith' button to be displayed after Google Auth login.");
//		ExtentReportManager.captureAndAttachScreenshot(driver, test, "SignInWithGoogleAuth",
//				"Screenshot after SignInWithGoogleAuth", true);
//		// Log out the user
//		Log.info("Logging out the user...");
//		test.info("Logging out the user...");
//		loginPage.clickButton(ButtonType.LOGOUT);
//		loginPage.logoutAndVerify();
//		Log.info("Successfully validated Google Auth login and logged out.");
//		test.info("Successfully validated Google Auth login and logged out.");
//	}
//
//	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
//	public void testSignInWithMicroSoftAuth(Map<String, String> data) {
//
//		// Click the Microsoft Auth button
//		Log.info("Logging in with Microsoft Auth...");
//		test.info("Logging in with Microsoft Auth...");
//		loginPage.clickButton(ButtonType.MICROSOFT_AUTH);
//
//		// Enter email and click Next
//		loginPage.enterText(EnterText.MICROSOFT_EMAIL, "punith.y0909@outlook.com");
//		loginPage.clickButton(ButtonType.MICROSOFT_NEXT);
//
//		// Enter password and click Sign In
//		loginPage.enterText(EnterText.MICROSOFT_PASSWORD, "7760502966punitH");
//		loginPage.clickButton(ButtonType.MICROSOFT_SIGN_IN);
//
//		// Handle "Stay signed in" prompt (if it appears)
//		try {
//			loginPage.clickButton(ButtonType.MICROSOFT_STAY_SIGNED_IN);
//		} catch (Exception e) {
//			Log.info("Stay signed in prompt did not appear: " + e.getMessage());
//			test.info("Stay signed in prompt did not appear: " + e.getMessage());
//		}
//
//		// Wait for the redirect to the logged-in page
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.urlContains("/dashboard"));
//
//		// Verify the user is logged in and capture screenshot
//		Log.info("Validating login...");
//		test.info("Validating login...");
//		Assert.assertTrue(getActionDriver().verifyMessage("logoutButton", "Logout Punith"),
//				"Expected 'Logout punith' button to be displayed after Microsoft Auth login.");
//		ExtentReportManager.captureAndAttachScreenshot(driver, test, "SignInWithMicroSoftAuth",
//				"SignInWithMicroSoftAuth", true);
//
//		// Log out the user and verify
//		Log.info("Logging out the user...");
//		test.info("Logging out the user...");
//		loginPage.logoutAndVerify();
//
//		Log.info("Successfully validated Microsoft Auth login and logged out.");
//		test.info("Successfully validated Microsoft Auth login and logged out.");
//	}
}