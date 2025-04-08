package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.CreateAccountPage;
import enums.ActionTypes.ActionType;
import enums.CreateAccountLocatorEnum.ElementLocators;
import enums.DashboardPageLocators.DashboardLocators;
import utils.ApiRequestHandler;
import utils.ExcelDataProvider;
import utils.ExtentReportManager;
import utils.Log;
import utils.RetryAnalyzer;
import java.lang.reflect.Method;
import java.util.Map;

public class CreateAccountTest extends BaseTest {

	private CreateAccountPage createAccountPage;

	// mvn test -Dtest=CreateAccountTest
	@BeforeMethod
	public void setupPage(Method method) {
		// Use reflection to get the test method name
		String testMethodName = method.getName();
		// Set the test title dynamically
		test = ExtentReportManager.createTest(testMethodName);
		test.info("Navigating to Sign up page");
		createAccountPage = new CreateAccountPage(getDriver(), getActionDriver());
		createAccountPage.navigateToCreateAccountPage();
		Log.info("Navigating to Creating Account Page...");
		test.info("Navigating to Creating Account Page...");
		System.out.println("Thread ID: " + Thread.currentThread().getId());
	}
	protected void populateAccountFields(Map<String, String> data) {
        createAccountPage.populateCreateAccountFields(
            data.get("First Name"), 
            data.get("Last Name"),
            data.get("Email"),
            data.get("Password"),
            data.get("Confirm Password")
        );
    }

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testSuccessfulAccountCreation(Map<String, String> data) throws Exception {
		populateAccountFields(data);
		createAccountPage.clickButton();
		Log.info("Validating Account Registration");
		test.info("Validating Account Registration");
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, " + data.get("Expected Result")));
		ApiRequestHandler.deleteUser(data.get("Email"));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testMaximumFieldLengths(Map<String, String> data) throws Exception {
		populateAccountFields(data);
		createAccountPage.clickButton();
		Log.info("Validating Account Registration");
		test.info("Validating Account Registration");
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, " + data.get("Expected Result")));
		ApiRequestHandler.deleteUser(data.get("Email"));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testNavigateToSignInByLink(Map<String, String> data) {
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.SIGN_IN);
		Log.info("Validating Sign Up Page");
		test.info("Validating Sign Up Page");
		Assert.assertTrue(getActionDriver().isElementDisplayed(ElementLocators.SIGN_IN_PAGE));
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.SIGN_IN_PAGE, data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testNavigateToLoginButton(Map<String, String> data) {
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.NAV_LOGIN);
		Log.info("Validating Sign Up Page");
		test.info("Validating Sign Up Page");
		Assert.assertTrue(getActionDriver().isElementDisplayed(ElementLocators.SIGN_IN_PAGE));
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.SIGN_IN_PAGE, data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testMinimumPasswordLength(Map<String, String> data) throws Exception {
		populateAccountFields(data);
		createAccountPage.clickButton();
		Log.info("Validating Account Registration");
		test.info("Validating Account Registration");
		Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE,
				"Hello, " + data.get("Expected Result")));
		ApiRequestHandler.deleteUser(data.get("Email"));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testEmptyFirstName(Map<String, String> data) {
		populateAccountFields(data);
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(
				getActionDriver().verifyMessage(ElementLocators.VER_ERROR_FIRST_NAME, data.get("Expected Result")));
		Assert.assertTrue(
				getActionDriver().verifyMessage(ElementLocators.VER_ERROR_LAST_NAME, data.get("Expected Result")));
		Assert.assertTrue(
				getActionDriver().verifyMessage(ElementLocators.VER_ERROR_EMAIL_ID, data.get("Expected Result")));
		Assert.assertTrue(
				getActionDriver().verifyMessage(ElementLocators.VER_ERROR_PASSWORD, data.get("Expected Result")));
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_CONFIRM_PASSWORD,
				data.get("Expected Result")));
		Assert.assertTrue(
				getActionDriver().verifyMessage(ElementLocators.VER_ERROR_TNC_CHECK_BOX, data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testEmailAlreadyRegistered(Map<String, String> data) {
		populateAccountFields(data);
		createAccountPage.clickButton();
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.ERROR_MESSAGE, data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testPasswordMismatch(Map<String, String> data) {
		populateAccountFields(data);
		createAccountPage.clickButton();
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_CONFIRM_PASSWORD,
				data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testSQLInjectionAttemptAllFields(Map<String, String> data) {
		String[] expectedResults = data.get("Expected Result").split("\n");
		String expectedErrorFirstName = expectedResults[0];
		String expectedErrorLastName = expectedResults[1];
		String expectedErrorEmail = expectedResults[2];
		String expectedErrorPassword = expectedResults[3];
		populateAccountFields(data);
		createAccountPage.clickButton();
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(
				getActionDriver().verifyMessage(ElementLocators.VER_ERROR_FIRST_NAME, expectedErrorFirstName));
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_LAST_NAME, expectedErrorLastName));
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_EMAIL_ID, expectedErrorEmail));
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_PASSWORD, expectedErrorPassword));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testXSSAttemptAllFields(Map<String, String> data) {
		String[] expectedResults = data.get("Expected Result").split("\n");
		String expectedErrorFirstName = expectedResults[0];
		String expectedErrorLastName = expectedResults[1];
		String expectedErrorEmail = expectedResults[2];
		String expectedErrorPassword = expectedResults[3];
		populateAccountFields(data);
		createAccountPage.clickButton();
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(
				getActionDriver().verifyMessage(ElementLocators.VER_ERROR_FIRST_NAME, expectedErrorFirstName));
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_LAST_NAME, expectedErrorLastName));
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_EMAIL_ID, expectedErrorEmail));
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_PASSWORD, expectedErrorPassword));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testVerifyToggle(Map<String, String> data) {
		String[] expectedResults = data.get("Expected Result").split("\n");
		String expectedInitialType = expectedResults[0];
		String expectedToggledType = expectedResults[1];
		populateAccountFields(data);
		Assert.assertTrue(getActionDriver().verifyAttribute(ElementLocators.PASSWORD, "type", expectedInitialType),
				"Expected password field to be type='password' initially");
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.PASSWORD_TOGGLE);
		Assert.assertTrue(getActionDriver().verifyAttribute(ElementLocators.PASSWORD, "type", expectedToggledType),
				"Expected password field to be type='text' after toggling visibility");
		Assert.assertTrue(getActionDriver().verifyAttribute(ElementLocators.CONFIRM_PASSWORD, "type", expectedInitialType),
				"Expected password field to be type='password' initially");
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.CONFIRM_PASSWORD_TOGGLE);
		Assert.assertTrue(getActionDriver().verifyAttribute(ElementLocators.CONFIRM_PASSWORD, "type", expectedToggledType),
				"Expected password field to be type='text' after toggling visibility");
		Log.info("Validating Account Registration");
		test.info("Validating Account Registration");
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testSpecialCharacterFirstName(Map<String, String> data) {
		String[] expectedResults = data.get("Expected Result").split("\n");
		String expectedErrorFirstName = expectedResults[0];
		String expectedErrorLastName = expectedResults[1];
		populateAccountFields(data);
		createAccountPage.clickButton();
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_FIRST_NAME, expectedErrorFirstName));
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_LAST_NAME, expectedErrorLastName));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testPasswordBelowMinimum(Map<String, String> data) {
		populateAccountFields(data);
		createAccountPage.clickButton();
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_PASSWORD,
				data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testFirstAndLastNameContainingOnlySpaces(Map<String, String> data) {
		String[] expectedResults = data.get("Expected Result").split("\n");
		String expectedErrorFirstName = expectedResults[0];
		String expectedErrorLastName = expectedResults[1];
		populateAccountFields(data);
		createAccountPage.clickButton();
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_FIRST_NAME,
				expectedErrorFirstName));
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_LAST_NAME,
				expectedErrorLastName));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testMinimumLengthFirstAndLastName(Map<String, String> data) {
		String[] expectedResults = data.get("Expected Result").split("\n");
		String expectedErrorFirstName = expectedResults[0];
		String expectedErrorLastName = expectedResults[1];
		populateAccountFields(data);
		createAccountPage.clickButton();
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_FIRST_NAME,
				expectedErrorFirstName));
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_LAST_NAME,
				expectedErrorLastName));
	}
/*
	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testPasswordErrorChecks(Map<String, String> data) throws Exception {
		String[] passwords = { "", "password", "PASSWORD", "Password", "Password123" };

		String[] errorMessages = { "Password is required.", "Must include at least one uppercase letter (A-Z).",
				"Must include at least one lowercase letter (a-z).", "Must include at least one number (0-9).",
				"Must include at least one special character (@$!%*?&)." };
		for (int attempt = 0; attempt < passwords.length; attempt++) {
			createAccountPage.populateCreateAccountFields("Avinash", "Noop", "Avinash.Noop@example.com",
					passwords[attempt], "");
			createAccountPage.clickButton();
			Log.info("Validating Error Message for attempt " + (attempt + 1) + ": " + errorMessages[attempt]);
			test.info("Validating Error Message for password: " + passwords[attempt]);
			Assert.assertTrue(
					getActionDriver().verifyMessage(ElementLocators.VER_ERROR_PASSWORD, errorMessages[attempt]),
					"Error message validation failed for password: " + passwords[attempt]);
		}
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testInvalidEmailFormat(Map<String, String> data) throws Exception {
		String[] invalidEmails = { "john.doe@exampl", "john.doe", "@example.com", "john.doe@.com",
				"john..doe@example.com" };
		String expectedErrorMessage = "Enter a valid email address.";
		for (int attempt = 0; attempt < invalidEmails.length; attempt++) {
			String currentEmail = invalidEmails[attempt];
			createAccountPage.populateCreateAccountFields("John", "Doe", currentEmail, "Password123!", "Password123!");
			createAccountPage.clickButton();
			Log.info("Validating Error Message for email: " + currentEmail);
			test.info("Validating Error Message for email: " + currentEmail);
			Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_EMAIL_ID, expectedErrorMessage),
					"Error message validation failed for email: " + currentEmail);
		}
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
	public void testEmailContainingSpecialCharacters(Map<String, String> data) throws Exception {
		String[] emailsWithSpecialChars = { "Avinash.Noop_Doddu@example.com", "Avinash.Noop-Doddu@example.com",
				"Avinash.Noop+Doddu@example.com" };
		for (int attempt = 0; attempt < emailsWithSpecialChars.length; attempt++) {
			String currentEmail = emailsWithSpecialChars[attempt];
			createAccountPage.navigateToCreateAccountPage();
			createAccountPage.populateCreateAccountFields("Avinash", "NoopDoddu", currentEmail, "Password123!",
					"Password123!");
			createAccountPage.clickButton();
			Log.info("Validating Account Registration for email: " + currentEmail);
			test.info("Validating Account Registration for email: " + currentEmail);
			Assert.assertTrue(
					getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE, "Hello, Avinash"),
					"Account registration failed for email: " + currentEmail);
			getActionDriver().performAction(ActionType.CLICK, SideNavLocator.USER_PROFILE);
			getActionDriver().performAction(ActionType.CLICK, SideNavLocator.LOGOUT);
			ApiRequestHandler.deleteUser(currentEmail);
		}
	}
*/
//	  @Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class) 
//	public void testWeakPassword(Map<String, String> data)  {
//	  createAccountPage.populateCreateAccountFields(data.get("First Name"), data.get("Last Name"), data.get("Email"), data.get("Password"), data.get("Confirm Password"));
//	  createAccountPage.clickButton(); Log.info("Validating Account Registration");
//	  test.info("Validating Account Registration");
//	  
//	  Assert.assertEquals(driver.getCurrentUrl(),
//	  "https://to-do-ggau.onrender.com/");
//	  }
}