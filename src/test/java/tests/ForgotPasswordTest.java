package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.ForgotPasswordPage;
import utils.ConfigReader;
import utils.ExtentReportManager;
import utils.Log;
import java.lang.reflect.Method;
import enums.ActionTypes.ActionType;
import enums.ForgotPasswordLocators.ElementLocators;

public class ForgotPasswordTest extends BaseTest {
	// mvn test -Dtest=ForgotPasswordTest
	private ForgotPasswordPage forgotPasswordPage;

	@BeforeMethod
	public void setupPage(Method method) {
		// Use reflection to get the test method name
		String testMethodName = method.getName();
		// Set the test title dynamically
		test = ExtentReportManager.createTest(testMethodName);
		test.info("Navigating to Forgot Password Page");
		forgotPasswordPage = new ForgotPasswordPage(driver);
		forgotPasswordPage.navigateToForgotPasswordPage();
		Log.info("Navigating to forgot password");
		test.info("Navigating to forgot password");
	}
/*
	@Test
	public void testSignUpButton() {
		actionDriver.performAction(ActionType.CLICK, ElementLocators.SIGN_UP);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.CREATE_ACCOUNT_PAGE, "Create an account"));
	}

	@Test
	public void testLoginPageLink() {
		actionDriver.performAction(ActionType.CLICK, ElementLocators.LOGIN_PAGE);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.SIGN_IN_PAGE, "Sign in to your account"));
	}

	@Test
	public void testEmailContainingSpecialCharacters() throws Exception {
		forgotPasswordPage.populateForgotPageEmailFields("Avinash.Noop+Doddu@example.com");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.PASSWORD_RESET);
		actionDriver.performAction(ActionType.CLICK, ElementLocators.CONFIRM_RESET);
		Log.info("Navigating to Sign in Page");
		test.info("Navigating to Sign in Page");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.SIGN_IN_PAGE, "Sign in to your account"));
	}

	@Test
	public void testEmptyEmailField() {
		forgotPasswordPage.populateForgotPageEmailFields("");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.PASSWORD_RESET);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.VER_ERROR_EMAIL_ID, "Email is required."));
	}

	@Test
	public void testInvalidEmailFormat() {
		forgotPasswordPage.populateForgotPageEmailFields("john.doe@exampl");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.PASSWORD_RESET);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(
				actionDriver.verifyMessage(ElementLocators.VER_ERROR_EMAIL_ID, "Enter a valid email address."));
	}

	@Test
	public void testResetPasswordPage() {
		forgotPasswordPage.resetPasswordLink();
		forgotPasswordPage.populatePasswordRestFields("Password123!", "Password123!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.SAVE_PASSWORD);
		actionDriver.performAction(ActionType.CLICK, ElementLocators.CONFIRM_RESET);
		Log.info("Navigating to Registration Page");
		test.info("Navigating to Registration Page");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.SIGN_IN_PAGE, "Sign in to your account"));
	}

	@Test
	public void testSignUpPasswordResetPage() {
		forgotPasswordPage.resetPasswordLink();
		actionDriver.performAction(ActionType.CLICK, ElementLocators.SIGN_UP);
		Log.info("Navigating to Registration Page");
		test.info("Navigating to Registration Page");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.CREATE_ACCOUNT_PAGE, "Create an account"));
	}

	@Test
	public void testLoginPasswordResetPageLink() {
		forgotPasswordPage.resetPasswordLink();
		actionDriver.performAction(ActionType.CLICK, ElementLocators.LOGIN_PAGE);
		Log.info("Navigating to Sign in Page");
		test.info("Navigating to Sign in Page");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.SIGN_IN_PAGE, "Sign in to your account"));
	}

	@Test
	public void testMinimumPasswordLength() {
		forgotPasswordPage.resetPasswordLink();
		forgotPasswordPage.populatePasswordRestFields("Pass123!", "Pass123!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.SAVE_PASSWORD);
		actionDriver.performAction(ActionType.CLICK, ElementLocators.CONFIRM_RESET);
		Log.info("Navigating to Sign in Page");
		test.info("Navigating to Sign in Page");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.SIGN_IN_PAGE, "Sign in to your account"));
	}

	@Test
	public void testEmptyPasswordField() {
		forgotPasswordPage.resetPasswordLink();
		Log.info("Navigating to Reset Password Page...");
		test.info("Navigating to Reset Password Page...");
		forgotPasswordPage.populatePasswordRestFields("", "");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.SAVE_PASSWORD);

		Assert.assertTrue(
				actionDriver.verifyMessage(ElementLocators.VER_ERROR_PASSWORD, "Password is required."));
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.VER_ERROR_CONFIRM_PASSWORD,
				"Please confirm your password."));
	}

	@Test
	public void testVerifyToggle() {
		forgotPasswordPage.resetPasswordLink();
		forgotPasswordPage.populatePasswordRestFields("Password123!", "Password123!");
		Assert.assertTrue(actionDriver.verifyAttribute(ElementLocators.PASSWORD, "type", "password"),
				"Expected password field to be type='password' initially");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.PASSWORD_TOGGLE);
		Assert.assertTrue(actionDriver.verifyAttribute(ElementLocators.PASSWORD, "type", "text"),
				"Expected password field to be type='text' after toggling visibility");
		Assert.assertTrue(actionDriver.verifyAttribute(ElementLocators.CONFIRM_PASSWORD, "type", "password"),
				"Expected password field to be type='password' initially");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.CONFIRM_PASSWORD_TOGGLE);
		Assert.assertTrue(actionDriver.verifyAttribute(ElementLocators.CONFIRM_PASSWORD, "type", "text"),
				"Expected password field to be type='text' after toggling visibility");
	}

	@Test
	public void testPasswordMismatch() {
		forgotPasswordPage.resetPasswordLink();
		forgotPasswordPage.populatePasswordRestFields("Password123!", "password124!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.SAVE_PASSWORD);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.VER_ERROR_CONFIRM_PASSWORD,
				"Passwords do not match."));
	}

	@Test
	public void testPasswordTooShort() {
		forgotPasswordPage.resetPasswordLink();
		forgotPasswordPage.populatePasswordRestFields("Pas123!", "Pas124!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.SAVE_PASSWORD);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.VER_ERROR_PASSWORD,
				"Password must be at least 8 characters long."));
	}

	@Test
	public void testSQLInjectionAttemptPassword() {
		forgotPasswordPage.resetPasswordLink();
		forgotPasswordPage.populatePasswordRestFields("'1'='1'", "'1'='1'");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.SAVE_PASSWORD);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.VER_ERROR_PASSWORD,
				"Password must be at least 8 characters long."));
	}
	*/
	@Test
	public void testPasswordReset() {
		driver.get(ConfigReader.getProperty("url"));
		forgotPasswordPage.populateLoginFields("john.doe@example.com", "Password123!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		String baseUrl = ConfigReader.getProperty("url");
		String resetPasswordUrl = baseUrl + "/reset-password?token=333";
		driver.navigate().to(resetPasswordUrl);
		actionDriver.performAction(ActionType.ENTER_TEXT, ElementLocators.PASSWORD, "Password123!");
		actionDriver.performAction(ActionType.ENTER_TEXT, ElementLocators.CONFIRM_PASSWORD, "Password123!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.SAVE_PASSWORD);
		actionDriver.performAction(ActionType.CLICK, ElementLocators.CONFIRM_RESET);
		Log.info("Navigating to Sign in Page");
		test.info("Navigating to Sign in Page");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.LOGOUT);
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.SIGN_IN_PAGE, "Sign in to your account"));
	}	
}