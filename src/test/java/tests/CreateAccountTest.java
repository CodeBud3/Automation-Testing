package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.CreateAccountPage;
import enums.ActionTypes.ActionType;
import enums.CreateAccountLocatorEnum.ElementLocators;
import enums.DashboardPageLocators.DashboardLocators;
import enums.SideNavLocators.SideNavLocator;
import utils.ApiRequestHandler;
import utils.ExcelDataProvider;
import utils.ExtentReportManager;
import utils.Log;
import utils.RetryAnalyzer;
import java.lang.reflect.Method;
import java.util.Map;

public class CreateAccountTest extends BaseTest {

	private CreateAccountPage createAccountPage;
	private String[] extractFieldValues(Map<String, String> data, String fieldName) {
        return data.getOrDefault(fieldName, "").split("\n");
    }
	

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
	public void testEmptyAllFields(Map<String, String> data) {
		String[] expectedResults = data.get("Expected Result").split("\n");
		String expectedErrorFirstName = expectedResults[0];
		String expectedErrorLastName = expectedResults[1];
		String expectedErrorEmail = expectedResults[2];
		String expectedErrorPassword = expectedResults[3];
		String expectedErrorConfirmPassword = expectedResults[4];
		String expectedErrorTermAndCondition = expectedResults[5];
		populateAccountFields(data);
		getActionDriver().performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(
				getActionDriver().verifyMessage(ElementLocators.VER_ERROR_FIRST_NAME, expectedErrorFirstName));
		Assert.assertTrue(
				getActionDriver().verifyMessage(ElementLocators.VER_ERROR_LAST_NAME, expectedErrorLastName));
		Assert.assertTrue(
				getActionDriver().verifyMessage(ElementLocators.VER_ERROR_EMAIL_ID, expectedErrorEmail));
		Assert.assertTrue(
				getActionDriver().verifyMessage(ElementLocators.VER_ERROR_PASSWORD, expectedErrorPassword));
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_CONFIRM_PASSWORD,
				expectedErrorConfirmPassword));
		Assert.assertTrue(
				getActionDriver().verifyMessage(ElementLocators.VER_ERROR_TNC_CHECK_BOX, expectedErrorTermAndCondition));
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
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_FIRST_NAME, expectedErrorFirstName));
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
	
	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
    public void testPasswordErrorChecks(Map<String, String> data) throws Exception {
        String[] passwords = extractFieldValues(data, "Password");
        String[] errorMessages = extractFieldValues(data, "Expected Result");

        int maxAttempts = Math.min(passwords.length, errorMessages.length);

        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            String currentPassword = passwords[attempt];
            String expectedError = errorMessages[attempt];

            createAccountPage.populateCreateAccountFields(
                data.get("First Name"), 
                data.get("Last Name"), 
                data.get("Email"), 
                currentPassword, 
                ""
            );
            createAccountPage.clickButton();

            String logMessage = String.format("Validating Error Message for attempt %d: %s", attempt + 1, expectedError);
            Log.info(logMessage);
            test.info("Validating Error Message for password: " + currentPassword);

            boolean isErrorValid = getActionDriver().verifyMessage(ElementLocators.VER_ERROR_PASSWORD, expectedError);
            Assert.assertTrue(isErrorValid, "Error message validation failed for password: " + currentPassword);
        }
    }


	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testInvalidEmailFormat(Map<String, String> data) throws Exception {
		String[] emailData = extractFieldValues(data, "Email");
		for (int attempt = 0; attempt < emailData.length; attempt++) {
			String currentEmail = emailData[attempt];
			createAccountPage.populateCreateAccountFields(data.get("First Name"), data.get("Last Name"), currentEmail, data.get("Password"), data.get("Confirm Password"));
			createAccountPage.clickButton();
			Log.info("Validating Error Message for email: " + currentEmail);
			test.info("Validating Error Message for email: " + currentEmail);
			Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_EMAIL_ID, data.get("Expected Result")));
		}
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
	public void testEmailContainingSpecialCharacters(Map<String, String> data) throws Exception {
		String[] emailData = extractFieldValues(data, "Email");
		for (int attempt = 0; attempt < emailData.length; attempt++) {
			String currentEmail = emailData[attempt];
			createAccountPage.navigateToCreateAccountPage();
			createAccountPage.populateCreateAccountFields(data.get("First Name"), data.get("Last Name"), currentEmail, data.get("Password"), data.get("Confirm Password"));
			createAccountPage.clickButton();
			Log.info("Validating Account Registration for email: " + currentEmail);
			test.info("Validating Account Registration for email: " + currentEmail);
			Assert.assertTrue(getActionDriver().verifyMessage(DashboardLocators.DASHBOARD_WELCOME_MESSAGE, "Hello, "+data.get("Expected Result")));
			getActionDriver().performAction(ActionType.CLICK, SideNavLocator.USER_PROFILE);
			getActionDriver().performAction(ActionType.CLICK, SideNavLocator.LOGOUT);
			ApiRequestHandler.deleteUser(currentEmail);
		}
	}
	
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