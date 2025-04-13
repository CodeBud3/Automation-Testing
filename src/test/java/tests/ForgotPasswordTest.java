package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.ForgotPasswordPage;
import utils.ExcelDataProvider;
import utils.ExtentReportManager;
import utils.Log;
import utils.RetryAnalyzer;
import java.lang.reflect.Method;
import java.util.Map;
import enums.ActionTypes.ActionType;
import enums.ForgotPasswordLocators.ForgotPasswordPageLocators;
import enums.CommonLocators.CommonPageLocators;
import enums.CreateAccountLocatorEnum.ElementLocators;
import enums.SideNavLocators.SideNavLocator;

public class ForgotPasswordTest extends BaseTest {
	// mvn test -Dtest=ForgotPasswordTest
	private ForgotPasswordPage forgotPasswordPage;
	private String[] extractFieldValues(Map<String, String> data, String fieldName) {
        return data.getOrDefault(fieldName, "").split("\n");
    }

	@BeforeMethod
	public void setupPage(Method method) {
		// Use reflection to get the test method name
		String testMethodName = method.getName();
		// Set the test title dynamically
		test = ExtentReportManager.createTest(testMethodName);
		test.info("Navigating to Forgot Password Page");
		forgotPasswordPage = new ForgotPasswordPage(getDriver(), getActionDriver());
		forgotPasswordPage.navigateToForgotPasswordPage();
		Log.info("Running " + testMethodName + " on Thread ID: " + Thread.currentThread().getId());
	}

	protected void populatePasswordRestFields(Map<String, String> data) {
		forgotPasswordPage.populatePasswordRestFields(
            data.get("Password"),
            data.get("Confirm Password")
        );
    }
	
	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testSignUpButton(Map<String, String> data) {
		getActionDriver().performAction(ActionType.CLICK, CommonPageLocators.SIGN_UP);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(getActionDriver().verifyMessage(CommonPageLocators.SIGN_UP_PAGE, data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testLoginPageLink(Map<String, String> data) {
		getActionDriver().performAction(ActionType.CLICK, ForgotPasswordPageLocators.LOGIN_PAGE);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(getActionDriver().verifyMessage(CommonPageLocators.SIGN_UP_PAGE, data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testEmptyEmailField(Map<String, String> data) {
		getActionDriver().performAction(ActionType.ENTER_TEXT, CommonPageLocators.EMAIL_TEXT,data.get("Email"));
		getActionDriver().performAction(ActionType.CLICK, ForgotPasswordPageLocators.PASSWORD_RESET);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_EMAIL_ID, data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testInvalidEmailFormat(Map<String, String> data) {
		getActionDriver().performAction(ActionType.ENTER_TEXT, CommonPageLocators.EMAIL_TEXT,data.get("Email"));
		getActionDriver().performAction(ActionType.CLICK, ForgotPasswordPageLocators.PASSWORD_RESET);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_EMAIL_ID, data.get("Expected Result")));
	}

	
	
	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testValidEmailFormat(Map<String, String> data) {
		String[] emailData = extractFieldValues(data, "Email");
		for (int attempt = 0; attempt < emailData.length; attempt++) {
			String currentEmail = emailData[attempt];
		forgotPasswordPage.navigateToForgotPasswordPage();
		getActionDriver().performAction(ActionType.ENTER_TEXT, CommonPageLocators.EMAIL_TEXT,currentEmail);
		forgotPasswordPage.performResetPassword();
		Log.info("Navigating to Sign in Page");
		test.info("Navigating to Sign in Page");
		Assert.assertTrue(getActionDriver().verifyMessage(CommonPageLocators.SIGN_IN_PAGE, data.get("Expected Result")));
		}
	}
	
	
	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testResetPasswordPage(Map<String, String> data) {
		forgotPasswordPage.resetPasswordLink();
		populatePasswordRestFields(data);
		forgotPasswordPage.performResetPassword();
		Log.info("Navigating to Registration Page");
		test.info("Navigating to Registration Page");
		Assert.assertTrue(getActionDriver().verifyMessage(CommonPageLocators.SIGN_IN_PAGE, data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testSignUpPasswordResetPage(Map<String, String> data) {
		forgotPasswordPage.resetPasswordLink();
		getActionDriver().performAction(ActionType.CLICK, CommonPageLocators.SIGN_UP);
		Log.info("Navigating to Registration Page");
		test.info("Navigating to Registration Page");
		Assert.assertTrue(getActionDriver().verifyMessage(CommonPageLocators.SIGN_UP_PAGE, data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testLoginPasswordResetPageLink(Map<String, String> data) {
		forgotPasswordPage.resetPasswordLink();
		getActionDriver().performAction(ActionType.CLICK, CommonPageLocators.LOGIN);
		Log.info("Navigating to Sign in Page");
		test.info("Navigating to Sign in Page");
		Assert.assertTrue(getActionDriver().verifyMessage(CommonPageLocators.SIGN_IN_PAGE, data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testMinimumPasswordLength(Map<String, String> data) {
		forgotPasswordPage.resetPasswordLink();
		populatePasswordRestFields(data);
		forgotPasswordPage.performResetPassword();
		Log.info("Navigating to Sign in Page");
		test.info("Navigating to Sign in Page");
		Assert.assertTrue(getActionDriver().verifyMessage(CommonPageLocators.SIGN_IN_PAGE, data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testEmptyPasswordField(Map<String, String> data) {
		String[] expectedResults = data.get("Expected Result").split("\n");
		String expectedPasswordError = expectedResults[0];
		String expectedCONFPasswordError = expectedResults[1];
		forgotPasswordPage.resetPasswordLink();
		Log.info("Navigating to Reset Password Page...");
		test.info("Navigating to Reset Password Page...");
		populatePasswordRestFields(data);
		getActionDriver().performAction(ActionType.CLICK, ForgotPasswordPageLocators.CONFIRM_RESET);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_PASSWORD, expectedPasswordError));
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_CONFIRM_PASSWORD,expectedCONFPasswordError));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testVerifyToggle(Map<String, String> data) {
		String[] expectedResults = data.get("Expected Result").split("\n");
		String expectedInitialType = expectedResults[0];
		String expectedToggledType = expectedResults[1];
		forgotPasswordPage.resetPasswordLink();
		populatePasswordRestFields(data);
		Assert.assertTrue(getActionDriver().verifyAttribute(CommonPageLocators.PASSWORD_TEXT, "type", expectedInitialType));
		getActionDriver().performAction(ActionType.CLICK, CommonPageLocators.PASSWORD_VIS_TOGGLE);
		Assert.assertTrue(getActionDriver().verifyAttribute(CommonPageLocators.PASSWORD_TEXT, "type", expectedToggledType));
		Assert.assertTrue(getActionDriver().verifyAttribute(CommonPageLocators.CONFIRM_PASSWORD, "type", expectedInitialType));
		getActionDriver().performAction(ActionType.CLICK, CommonPageLocators.CONFIRM_PASSWORD_VIS_TOGGLE);
		Assert.assertTrue(getActionDriver().verifyAttribute(CommonPageLocators.CONFIRM_PASSWORD, "type", expectedToggledType));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testPasswordMismatch(Map<String, String> data) {
		forgotPasswordPage.resetPasswordLink();
		populatePasswordRestFields(data);
		getActionDriver().performAction(ActionType.CLICK, ForgotPasswordPageLocators.PASSWORD_RESET);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_CONFIRM_PASSWORD, data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testPasswordTooShort(Map<String, String> data) {
		forgotPasswordPage.resetPasswordLink();
		populatePasswordRestFields(data);
		getActionDriver().performAction(ActionType.CLICK, ForgotPasswordPageLocators.PASSWORD_RESET);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_PASSWORD,
				data.get("Expected Result")));
	}

	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testSQLInjectionAttemptPassword(Map<String, String> data) {
		forgotPasswordPage.resetPasswordLink();
		populatePasswordRestFields(data);
		getActionDriver().performAction(ActionType.CLICK, ForgotPasswordPageLocators.PASSWORD_RESET);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(getActionDriver().verifyMessage(ElementLocators.VER_ERROR_PASSWORD,
				data.get("Expected Result")));
	}
	
	@Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
	public void testPasswordReset(Map<String, String> data) {
		forgotPasswordPage.resetPasswordLink();
		populatePasswordRestFields(data);
		forgotPasswordPage.performResetPassword();
		forgotPasswordPage.populateLoginFields(data.get("Email"), data.get("Password"));
		getActionDriver().performAction(ActionType.CLICK, CommonPageLocators.LOGIN);
		Log.info("Navigating to Sign in Page");
		test.info("Navigating to Sign in Page");
		getActionDriver().performAction(ActionType.CLICK, SideNavLocator.USER_PROFILE);
		getActionDriver().performAction(ActionType.CLICK, SideNavLocator.LOGOUT);
		Assert.assertTrue(getActionDriver().verifyMessage(CommonPageLocators.SIGN_IN_PAGE, data.get("Expected Result")));
	}
}