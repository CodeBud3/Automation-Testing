package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.ForgotPasswordPage;
import pages.ForgotPasswordPage.ActionType;
import pages.ForgotPasswordPage.ElementLocators;
import utils.ExtentReportManager;
import utils.Log;

import java.lang.reflect.Method;

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
	}

	@Test
	public void testSignUpButton() {
		Log.info("Navigating to Forgot Password Page...");
		test.info("Navigating to Forgot Password Page...");
		forgotPasswordPage.performAction(ActionType.CLICK, ElementLocators.SIGN_UP);
		Log.info("Navigating to Registration Page");
		test.info("Navigating to Registration Page");
		Assert.assertTrue(forgotPasswordPage.verifyMessage(ElementLocators.CREATE_ACCOUNT_PAGE, "Create an account"));
	}

	@Test
	public void testLoginPageLink() {
		Log.info("Navigating to Forgot Password Page...");
		test.info("Navigating to Forgot Password Page...");
		forgotPasswordPage.performAction(ActionType.CLICK, ElementLocators.LOGIN_PAGE);
		Log.info("Navigating to Sign in Page");
		test.info("Navigating to Sign in Page");
		Assert.assertTrue(forgotPasswordPage.verifyMessage(ElementLocators.SIGN_IN_PAGE, "Sign in to your account"));
	}

	@Test
	public void testPasswordReset() {
		Log.info("Navigating to Forgot Password Page...");
		test.info("Navigating to Forgot Password Page...");
		forgotPasswordPage.performAction(ActionType.ENTER_TEXT, ElementLocators.EMAIL_FIELD,
				"punith7760kumar@gmail.com");
		forgotPasswordPage.performAction(ActionType.CLICK, ElementLocators.PASSWORD_RESET);
		forgotPasswordPage.performAction(ActionType.CLICK, ElementLocators.CONFIRM_RESET);
		Log.info("Navigating to Sign in Page");
		test.info("Navigating to Sign in Page");
		Assert.assertTrue(forgotPasswordPage.verifyMessage(ElementLocators.SIGN_IN_PAGE, "Sign in to your account"));
	}
	@Test
	public void testEmailContainingSpecialCharacters() throws Exception {
		Log.info("Navigating to Forgot Password Page...");
		test.info("Navigating to Forgot Password Page...");
		forgotPasswordPage.performAction(ActionType.ENTER_TEXT, ElementLocators.EMAIL_FIELD,
				"Avinash.Noop+Doddu@example.com");
		forgotPasswordPage.performAction(ActionType.CLICK, ElementLocators.PASSWORD_RESET);
		forgotPasswordPage.performAction(ActionType.CLICK, ElementLocators.CONFIRM_RESET);
		Log.info("Navigating to Sign in Page");
		test.info("Navigating to Sign in Page");
		Assert.assertTrue(forgotPasswordPage.verifyMessage(ElementLocators.SIGN_IN_PAGE, "Sign in to your account"));
	}
	@Test
	public void testEmptyEmailField()  {
		Log.info("Navigating to Forgot Password Page...");
		test.info("Navigating to Forgot Password Page...");
		forgotPasswordPage.performAction(ActionType.ENTER_TEXT, ElementLocators.EMAIL_FIELD, "");
		forgotPasswordPage.performAction(ActionType.CLICK, ElementLocators.PASSWORD_RESET);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(forgotPasswordPage.verifyMessage(ElementLocators.VER_ERROR_EMAIL_ID, "Email is required."));
		}
	@Test
	public void testInvalidEmailFormat()  {
		Log.info("Navigating to Forgot Password Page...");
		test.info("Navigating to Forgot Password Page...");
		forgotPasswordPage.performAction(ActionType.ENTER_TEXT, ElementLocators.EMAIL_FIELD, "john.doe@exampl");
		forgotPasswordPage.performAction(ActionType.CLICK, ElementLocators.PASSWORD_RESET);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(forgotPasswordPage.verifyMessage(ElementLocators.VER_ERROR_EMAIL_ID, "Enter a valid email address."));
	}
	
	/*
	// To Run the below Test Cases, we need password reset token link which will be
	// sent to user Email ID
	@Test
	public void testResetPasswordPage() {
		Log.info("Navigating to Reset Password Page...");
		test.info("Navigating to Reset Password Page...");
		forgotPasswordPage.performAction(ActionType.ENTER_TEXT, ElementLocators.PASSWORD, "Password123!");
		forgotPasswordPage.performAction(ActionType.ENTER_TEXT, ElementLocators.CONFIRM_PASSWORD, "Password123!");
		forgotPasswordPage.performAction(ActionType.CLICK, ElementLocators.SAVE_PASSWORD);
		forgotPasswordPage.performAction(ActionType.CLICK, ElementLocators.CONFIRM_RESET);
		Log.info("Navigating to Registration Page");
		test.info("Navigating to Registration Page");
		Assert.assertTrue(forgotPasswordPage.verifyMessage(ElementLocators.SIGN_IN_PAGE, "Sign in to your account"));
	}

	// Password Reset page SIGN UP and LOGIN LINK
	@Test
	public void testSignUpPasswordResetPage() {
		Log.info("Navigating to Reset Password Page...");
		test.info("Navigating to Reset Password Page...");
		forgotPasswordPage.performAction(ActionType.CLICK, ElementLocators.SIGN_UP);
		Log.info("Navigating to Registration Page");
		test.info("Navigating to Registration Page");
		Assert.assertTrue(forgotPasswordPage.verifyMessage(ElementLocators.CREATE_ACCOUNT_PAGE, "Create an account"));
	}
	
	// Reset your password page SIGN UP and LOGIN LINK
	@Test
	public void testLoginPasswordResetPageLink() {
		Log.info("Navigating to Reset Password Page...");
		test.info("Navigating to Reset Password Page...");
		forgotPasswordPage.performAction(ActionType.CLICK, ElementLocators.LOGIN_PAGE);
		Log.info("Navigating to Sign in Page");
		test.info("Navigating to Sign in Page");
		Assert.assertTrue(forgotPasswordPage.verifyMessage(ElementLocators.SIGN_IN_PAGE, "Sign in to your account"));
	}
	
	@Test
	public void testMinimumPasswordLength() {
		Log.info("Navigating to Reset Password Page...");
		test.info("Navigating to Reset Password Page...");
		forgotPasswordPage.performAction(ActionType.ENTER_TEXT, ElementLocators.PASSWORD, "Pass123!");
		forgotPasswordPage.performAction(ActionType.ENTER_TEXT, ElementLocators.CONFIRM_PASSWORD, "Pass123!");
		forgotPasswordPage.performAction(ActionType.CLICK, ElementLocators.SAVE_PASSWORD);
		forgotPasswordPage.performAction(ActionType.CLICK, ElementLocators.CONFIRM_RESET);
		Log.info("Navigating to Sign in Page");
		test.info("Navigating to Sign in Page");
		Assert.assertTrue(forgotPasswordPage.verifyMessage(ElementLocators.SIGN_IN_PAGE, "Sign in to your account"));
	}
	
	@Test
	public void testEmptyPasswordField() {
		Log.info("Navigating to Reset Password Page...");
		test.info("Navigating to Reset Password Page...");
		forgotPasswordPage.performAction(ActionType.ENTER_TEXT, ElementLocators.PASSWORD, "");
		forgotPasswordPage.performAction(ActionType.ENTER_TEXT, ElementLocators.CONFIRM_PASSWORD, "");
		forgotPasswordPage.performAction(ActionType.CLICK, ElementLocators.SAVE_PASSWORD);
		Assert.assertTrue(
				forgotPasswordPage.verifyMessage(ElementLocators.VER_ERROR_PASSWORD, "Password is required."));
		Assert.assertTrue(forgotPasswordPage.verifyMessage(ElementLocators.VER_ERROR_CONFIRM_PASSWORD,
				"Please confirm your password."));
	}
	
	@Test
	public void testVerifyToggle() {
		Log.info("Navigating to Forgot Password Page...");
		test.info("Navigating to Forgot Password Page...");
		forgotPasswordPage.performAction(ActionType.ENTER_TEXT, ElementLocators.PASSWORD, "Password123!");
		forgotPasswordPage.performAction(ActionType.ENTER_TEXT, ElementLocators.CONFIRM_PASSWORD, "Password123!");
		Assert.assertTrue(forgotPasswordPage.verifyAttribute(ElementLocators.PASSWORD, "type", "password"),
				"Expected password field to be type='password' initially");
		forgotPasswordPage.performAction(ActionType.CLICK, ElementLocators.PASSWORD_TOGGLE);
		Assert.assertTrue(forgotPasswordPage.verifyAttribute(ElementLocators.PASSWORD, "type", "text"),
				"Expected password field to be type='text' after toggling visibility");
		Assert.assertTrue(forgotPasswordPage.verifyAttribute(ElementLocators.CONFIRM_PASSWORD, "type", "password"),
				"Expected password field to be type='password' initially");
		forgotPasswordPage.performAction(ActionType.CLICK, ElementLocators.CONFIRM_PASSWORD_TOGGLE);
		Assert.assertTrue(forgotPasswordPage.verifyAttribute(ElementLocators.CONFIRM_PASSWORD, "type", "text"),
				"Expected password field to be type='text' after toggling visibility");
	}

	@Test
	public void testPasswordMismatch() {
		Log.info("Navigating to Forgot Password Page...");
		test.info("Navigating to Forgot Password Page...");
		forgotPasswordPage.performAction(ActionType.ENTER_TEXT, ElementLocators.PASSWORD, "Password123!");
		forgotPasswordPage.performAction(ActionType.ENTER_TEXT, ElementLocators.CONFIRM_PASSWORD, "Password124!");
		forgotPasswordPage.performAction(ActionType.CLICK, ElementLocators.SAVE_PASSWORD);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(forgotPasswordPage.verifyMessage(ElementLocators.VER_ERROR_PASSWORD, "Passwords do not match."));
	}

	@Test
	public void testPasswordTooShort() {
		Log.info("Navigating to Forgot Password Page...");
		test.info("Navigating to Forgot Password Page...");
		forgotPasswordPage.performAction(ActionType.ENTER_TEXT, ElementLocators.PASSWORD, "Pas123!");
		forgotPasswordPage.performAction(ActionType.ENTER_TEXT, ElementLocators.CONFIRM_PASSWORD, "Pas124!");
		forgotPasswordPage.performAction(ActionType.CLICK, ElementLocators.SAVE_PASSWORD);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(forgotPasswordPage.verifyMessage(ElementLocators.VER_ERROR_PASSWORD,
				"Password must be at least 8 characters long."));
	}

	@Test
	public void testSQLInjectionAttemptPassword() {
		Log.info("Navigating to Forgot Password Page...");
		test.info("Navigating to Forgot Password Page...");
		forgotPasswordPage.performAction(ActionType.ENTER_TEXT, ElementLocators.PASSWORD, "'1'='1'");
		forgotPasswordPage.performAction(ActionType.ENTER_TEXT, ElementLocators.CONFIRM_PASSWORD, "'1'='1'");
		forgotPasswordPage.performAction(ActionType.CLICK, ElementLocators.SAVE_PASSWORD);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(forgotPasswordPage.verifyMessage(ElementLocators.VER_ERROR_PASSWORD,
				"Password must be at least 8 characters long."));
	}*/
}