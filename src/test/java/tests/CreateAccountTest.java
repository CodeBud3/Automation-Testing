package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.CreateAccountPage;
import pages.CreateAccountPage.ActionType;
import pages.CreateAccountPage.ElementLocators;
import utils.ApiRequestHandler;
import utils.ExtentReportManager;
import utils.Log;

import java.lang.reflect.Method;


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
		createAccountPage = new CreateAccountPage(driver);
		createAccountPage.navigateToCreateAccountPage();
	}
	@Test
	public void testSuccessfulAccountCreation() throws Exception {
		Log.info("Navigating to Creating Account Page...");
		test.info("Navigating to Creating Account Page...");
		createAccountPage.populateCreateAccountFields("Avinash", "Noop", "Avinash.Noop@example.com", "Password123!", "Password123!");
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Account Registration");
		test.info("Validating Account Registration");		
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.LOGOUT, "Logout Avinash"));
		ApiRequestHandler.deleteUser("Avinash.Noop@example.com");
	}
	
	@Test
	public void testMaximumFieldLengths() throws Exception  {
		Log.info("Navigating to Creating Account Page...");
		test.info("Navigating to Creating Account Page...");
		createAccountPage.populateCreateAccountFields("FirstNameWithMaxLengthForTestingABCDEFGHIJABCDEFAA", "LastNameWithMaxLengthForTestingABCDEFGHIJABCDEFGAA", "longemailaddresswithmultiplecharactersandnumbers1234567890abcdefghijklmnopqrstuvwx@domainexample.com", "ApplebananaCherrydogLemonfishGrapeHorseiglooJuice7!xyz1ABCD1234A", "ApplebananaCherrydogLemonfishGrapeHorseiglooJuice7!xyz1ABCD1234A");
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Account Registration");
		test.info("Validating Account Registration");
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.LOGOUT, "Logout FirstNameWithMaxLengthForTestingABCDEFGHIJABCDEFAA"));
		ApiRequestHandler.deleteUser("longemailaddresswithmultiplecharactersandnumbers1234567890abcdefghijklmnopqrstuvwx@domainexample.com");
	}
		
	@Test
	public void testNavigateToSignInByLink()  {
		Log.info("Navigating to Creating Account Page...");
		test.info("Navigating to Creating Account Page...");
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.SIGN_IN);
		Log.info("Validating Sign Up Page");
		test.info("Validating Sign Up Page");
		Assert.assertTrue(createAccountPage.isMessageDisplayed(ElementLocators.SIGN_IN_PAGE));
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.SIGN_IN_PAGE, "Sign in to your account"));
	}
	@Test
	public void testNavigateToLoginButton()  {
		Log.info("Navigating to Creating Account Page...");
		test.info("Navigating to Creating Account Page...");
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.NAV_LOGIN);
		Log.info("Validating Sign Up Page");
		test.info("Validating Sign Up Page");
		Assert.assertTrue(createAccountPage.isMessageDisplayed(ElementLocators.SIGN_IN_PAGE));
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.SIGN_IN_PAGE, "Sign in to your account"));
	}
	@Test
	public void testMinimumPasswordLength() throws Exception  {
		Log.info("Navigating to Creating Account Page...");
		test.info("Navigating to Creating Account Page...");
		createAccountPage.populateCreateAccountFields("Avinash", "NoopDoddu", "Avinash.NoopDoddu@example.com", "Pass123!", "Pass123!");
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Account Registration");
		test.info("Validating Account Registration");
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.LOGOUT, "Logout Avinash"));
		ApiRequestHandler.deleteUser("Avinash.NoopDoddu@example.com");
	}
	@Test
	public void testEmailContainingSpecialCharacters() throws Exception {
		Log.info("Navigating to Creating Account Page...");
		test.info("Navigating to Creating Account Page...");
		createAccountPage.populateCreateAccountFields("Avinash", "NoopDoddu", "Avinash.Noop+Doddu@example.com", "Password123!", "Password123!");
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Account Registration");
		test.info("Validating Account Registration");
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.LOGOUT, "Logout Avinash"));
		ApiRequestHandler.deleteUser("Avinash.Noop+Doddu@example.com");
	}
	@Test
	public void testEmptyFirstName()  {
		Log.info("Navigating to Creating Account Page...");
		test.info("Navigating to Creating Account Page...");
		createAccountPage.populateCreateAccountFields("", "", "", "", "");
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.VER_ERROR_FIRST_NAME, "First name is required."));	
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.VER_ERROR_LAST_NAME, "Last name is required."));
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.VER_ERROR_EMAIL_ID, "Email is required."));
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.VER_ERROR_PASSWORD, "Password is required."));
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.VER_ERROR_CONFIRM_PASSWORD, "Please confirm your password."));
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.VER_ERROR_TNC_CHECK_BOX, "You must agree to the Terms and Conditions."));
	}
	

	@Test
	public void testInvalidEmailFormat()  {
		Log.info("Navigating to Creating Account Page...");
		test.info("Navigating to Creating Account Page...");
		createAccountPage.populateCreateAccountFields("John", "doe", "john.doe@exampl", "Password123!", "Password123!");
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.VER_ERROR_EMAIL_ID, "Enter a valid email address."));
	}
	@Test
	public void testEmailAlreadyRegistered()  {
		Log.info("Navigating to Creating Account Page...");
		test.info("Navigating to Creating Account Page...");
		createAccountPage.populateCreateAccountFields("John", "doe", "john.doe@example.com", "Password123!", "Password123!");
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.ERROR_MESSAGE, "Email already registered"));
	}
	@Test
	public void testPasswordMismatch()  {
		Log.info("Navigating to Creating Account Page...");
		test.info("Navigating to Creating Account Page...");
		createAccountPage.populateCreateAccountFields("John", "doe", "john.doe@example.com", "Password123!", "Password124!");
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.VER_ERROR_PASSWORD,"Passwords do not match."));
	}
	@Test
	public void testSQLInjectionAttemptEmail()  {
		Log.info("Navigating to Creating Account Page...");
		test.info("Navigating to Creating Account Page...");
		createAccountPage.populateCreateAccountFields("John", "doe", "'1'='1'", "Password123!", "Password123!");
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.VER_ERROR_EMAIL_ID, "Enter a valid email address."));
	}
	@Test
	public void testXSSAttemptFirstName()  {
		Log.info("Navigating to Creating Account Page...");
		test.info("Navigating to Creating Account Page...");
		createAccountPage.populateCreateAccountFields("<script>alert('XSS')</script>", "doe", "john.doe@example.com", "Password123!", "Password123!");
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.VER_ERROR_FIRST_NAME, "Only letters, spaces, hyphens (-), and apostrophes (') are allowed."));
	}
	@Test
	public void testXSSAttemptLastName()  {
		Log.info("Navigating to Creating Account Page...");
		test.info("Navigating to Creating Account Page...");
		createAccountPage.populateCreateAccountFields("John", "<script>alert('XSS')</script>", "john.doe@example.com", "Password123!", "Password123!");
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.VER_ERROR_LAST_NAME, "Only letters, spaces, hyphens (-), and apostrophes (') are allowed."));
	}
	@Test
	public void testVerifyToggle()  {
		Log.info("Navigating to Creating Account Page...");
		test.info("Navigating to Creating Account Page...");
		createAccountPage.populateCreateAccountFields("John", "doe", "john.doe@example.com", "Password123!", "Password123!");		
		Assert.assertTrue(createAccountPage.verifyAttribute(ElementLocators.PASSWORD, "type", "password"),
				"Expected password field to be type='password' initially");
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.PASSWORD_TOGGLE);
		Assert.assertTrue(createAccountPage.verifyAttribute(ElementLocators.PASSWORD, "type", "text"),
				"Expected password field to be type='text' after toggling visibility");
		Assert.assertTrue(createAccountPage.verifyAttribute(ElementLocators.CONFIRM_PASSWORD, "type", "password"),
				"Expected password field to be type='password' initially");
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.CONFIRM_PASSWORD_TOGGLE);
		Assert.assertTrue(createAccountPage.verifyAttribute(ElementLocators.CONFIRM_PASSWORD, "type", "text"),
				"Expected password field to be type='text' after toggling visibility");	
		Log.info("Validating Account Registration");
		test.info("Validating Account Registration");
	}
	@Test
	public void testSpecialCharacterFirstName()  {
		Log.info("Navigating to Creating Account Page...");
		test.info("Navigating to Creating Account Page...");
		createAccountPage.populateCreateAccountFields("Jöhn", "döe", "john.doe@example.com", "Password123!", "Password123!");
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.VER_ERROR_FIRST_NAME,"Only letters, spaces, hyphens (-), and apostrophes (') are allowed."));
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.VER_ERROR_LAST_NAME,"Only letters, spaces, hyphens (-), and apostrophes (') are allowed."));
	}
	@Test
	public void testPasswordBelowMinimum()  {
		Log.info("Navigating to Creating Account Page...");
		test.info("Navigating to Creating Account Page...");
		createAccountPage.populateCreateAccountFields("John", "doe", "john.doe@example.com", "Pas123!", "Pas123!");
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.VER_ERROR_PASSWORD,"Password must be at least 8 characters long."));
	}
	@Test
	public void testSQLInjectionAttemptPassword()  {
		Log.info("Navigating to Creating Account Page...");
		test.info("Navigating to Creating Account Page...");
		createAccountPage.populateCreateAccountFields("John", "doe", "john.doe@example.com", "'1'='1'", "'1'='1'");
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.VER_ERROR_PASSWORD,"Password must be at least 8 characters long."));
	}
	@Test
	public void testFirstAndLastNameContainingOnlySpaces()  {
		Log.info("Navigating to Creating Account Page...");
		test.info("Navigating to Creating Account Page...");
		createAccountPage.populateCreateAccountFields("  ", "  ", "john.doeA@example.com", "Password123!", "Password123!");
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.VER_ERROR_FIRST_NAME,"Must be at least 2 characters long."));
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.VER_ERROR_LAST_NAME,"Must be at least 2 characters long."));
	}
	
	@Test
	public void testMinimumLengthFirstName()  {
		Log.info("Navigating to Creating Account Page...");
		test.info("Navigating to Creating Account Page...");
		createAccountPage.populateCreateAccountFields("O", "O", "john.doeA@example.com", "Password123!", "Password123!");
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.VER_ERROR_FIRST_NAME,"Must be at least 2 characters long."));
		Assert.assertTrue(createAccountPage.verifyMessage(ElementLocators.VER_ERROR_LAST_NAME,"Must be at least 2 characters long."));
	}
	/*
	@Test
	public void testWeakPassword()  {
		Log.info("Navigating to Creating Account Page...");
		test.info("Navigating to Creating Account Page...");
		createAccountPage.populateCreateAccountFields("Joaahnaaaa", "doeaaaa", "john.doaaaaa@example.com", "Abcdef1!", "Abcdef1!");
		createAccountPage.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		Log.info("Validating Account Registration");
		test.info("Validating Account Registration");
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	*/
}