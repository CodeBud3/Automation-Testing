package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.CreateAccountPage;
import enums.ActionTypes.ActionType;
import enums.CreateAccountLocatorEnum.ElementLocators;
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
		Log.info("Navigating to Creating Account Page...");
		test.info("Navigating to Creating Account Page...");
	}
	@Test
	public void testSuccessfulAccountCreation() throws Exception {
		createAccountPage.populateCreateAccountFields("Avinash", "Noop", "Avinash.Noop@example.com", "Password123!", "Password123!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		actionDriver.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Account Registration");
		test.info("Validating Account Registration");		
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.LOGOUT, "Logout Avinash"));
		ApiRequestHandler.deleteUser("Avinash.Noop@example.com");
	}
	
	@Test
	public void testMaximumFieldLengths() throws Exception  {
		createAccountPage.populateCreateAccountFields("FirstNameWithMaxLengthForTestingABCDEFGHIJABCDEFAA", "LastNameWithMaxLengthForTestingABCDEFGHIJABCDEFGAA", "longemailaddresswithmultiplecharactersandnumbers1234567890abcdefghijklmnopqrstuvwx@domainexample.com", "ApplebananaCherrydogLemonfishGrapeHorseiglooJuice7!xyz1ABCD1234A", "ApplebananaCherrydogLemonfishGrapeHorseiglooJuice7!xyz1ABCD1234A");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		actionDriver.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Account Registration");
		test.info("Validating Account Registration");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.LOGOUT, "Logout FirstNameWithMaxLengthForTestingABCDEFGHIJABCDEFAA"));
		ApiRequestHandler.deleteUser("longemailaddresswithmultiplecharactersandnumbers1234567890abcdefghijklmnopqrstuvwx@domainexample.com");
	}
		
	@Test
	public void testNavigateToSignInByLink()  {
		actionDriver.performAction(ActionType.CLICK, ElementLocators.SIGN_IN);
		Log.info("Validating Sign Up Page");
		test.info("Validating Sign Up Page");
		Assert.assertTrue(actionDriver.isMessageDisplayed(ElementLocators.SIGN_IN_PAGE));
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.SIGN_IN_PAGE, "Sign in to your account"));
	}
	@Test
	public void testNavigateToLoginButton()  {
		actionDriver.performAction(ActionType.CLICK, ElementLocators.NAV_LOGIN);
		Log.info("Validating Sign Up Page");
		test.info("Validating Sign Up Page");
		Assert.assertTrue(actionDriver.isMessageDisplayed(ElementLocators.SIGN_IN_PAGE));
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.SIGN_IN_PAGE, "Sign in to your account"));
	}
	@Test
	public void testMinimumPasswordLength() throws Exception  {
		createAccountPage.populateCreateAccountFields("Avinash", "NoopDoddu", "Avinash.NoopDoddu@example.com", "Pass123!", "Pass123!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		actionDriver.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Account Registration");
		test.info("Validating Account Registration");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.LOGOUT, "Logout Avinash"));
		ApiRequestHandler.deleteUser("Avinash.NoopDoddu@example.com");
	}
	@Test
	public void testEmailContainingSpecialCharacters() throws Exception {
		createAccountPage.populateCreateAccountFields("Avinash", "NoopDoddu", "Avinash.Noop+Doddu@example.com", "Password123!", "Password123!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		actionDriver.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Account Registration");
		test.info("Validating Account Registration");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.LOGOUT, "Logout Avinash"));
		ApiRequestHandler.deleteUser("Avinash.Noop+Doddu@example.com");
	}
	@Test
	public void testEmptyFirstName()  {
		createAccountPage.populateCreateAccountFields("", "", "", "", "");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.VER_ERROR_FIRST_NAME, "First name is required."));	
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.VER_ERROR_LAST_NAME, "Last name is required."));
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.VER_ERROR_EMAIL_ID, "Email is required."));
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.VER_ERROR_PASSWORD, "Password is required."));
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.VER_ERROR_CONFIRM_PASSWORD, "Please confirm your password."));
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.VER_ERROR_TNC_CHECK_BOX, "You must agree to the Terms and Conditions."));
	}
	

	@Test
	public void testInvalidEmailFormat()  {
		createAccountPage.populateCreateAccountFields("John", "doe", "john.doe@exampl", "Password123!", "Password123!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		actionDriver.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.VER_ERROR_EMAIL_ID, "Enter a valid email address."));
	}
	@Test
	public void testEmailAlreadyRegistered()  {
		createAccountPage.populateCreateAccountFields("John", "doe", "john.doe@example.com", "Password123!", "Password123!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		actionDriver.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.ERROR_MESSAGE, "Email already registered"));
	}
	@Test
	public void testPasswordMismatch()  {
		createAccountPage.populateCreateAccountFields("John", "doe", "john.doe@example.com", "Password123!", "Password124!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		actionDriver.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.VER_ERROR_CONFIRM_PASSWORD,"Passwords do not match."));
	}
	@Test
	public void testSQLInjectionAttemptEmail()  {
		createAccountPage.populateCreateAccountFields("John", "doe", "'1'='1'", "Password123!", "Password123!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		actionDriver.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.VER_ERROR_EMAIL_ID, "Enter a valid email address."));
	}
	@Test
	public void testXSSAttemptFirstName()  {
		createAccountPage.populateCreateAccountFields("<script>alert('XSS')</script>", "doe", "john.doe@example.com", "Password123!", "Password123!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		actionDriver.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.VER_ERROR_FIRST_NAME, "Only letters, spaces, hyphens (-), and apostrophes (') are allowed."));
	}
	@Test
	public void testXSSAttemptLastName()  {
		createAccountPage.populateCreateAccountFields("John", "<script>alert('XSS')</script>", "john.doe@example.com", "Password123!", "Password123!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		actionDriver.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.VER_ERROR_LAST_NAME, "Only letters, spaces, hyphens (-), and apostrophes (') are allowed."));
	}
	@Test
	public void testVerifyToggle()  {
		createAccountPage.populateCreateAccountFields("John", "doe", "john.doe@example.com", "Password123!", "Password123!");		
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
		Log.info("Validating Account Registration");
		test.info("Validating Account Registration");
	}
	@Test
	public void testSpecialCharacterFirstName()  {
		createAccountPage.populateCreateAccountFields("Jöhn", "döe", "john.doe@example.com", "Password123!", "Password123!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		actionDriver.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.VER_ERROR_FIRST_NAME,"Only letters, spaces, hyphens (-), and apostrophes (') are allowed."));
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.VER_ERROR_LAST_NAME,"Only letters, spaces, hyphens (-), and apostrophes (') are allowed."));
	}
	@Test
	public void testPasswordBelowMinimum()  {
		createAccountPage.populateCreateAccountFields("John", "doe", "john.doe@example.com", "Pas123!", "Pas123!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		actionDriver.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.VER_ERROR_PASSWORD,"Password must be at least 8 characters long."));
	}
	@Test
	public void testSQLInjectionAttemptPassword()  {
		createAccountPage.populateCreateAccountFields("John", "doe", "john.doe@example.com", "'1'='1'", "'1'='1'");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		actionDriver.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.VER_ERROR_PASSWORD,"Password must be at least 8 characters long."));
	}
	@Test
	public void testFirstAndLastNameContainingOnlySpaces()  {
		createAccountPage.populateCreateAccountFields("  ", "  ", "john.doeA@example.com", "Password123!", "Password123!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		actionDriver.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.VER_ERROR_FIRST_NAME,"Must be at least 2 characters long."));
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.VER_ERROR_LAST_NAME,"Must be at least 2 characters long."));
	}
	
	@Test
	public void testMinimumLengthFirstAndLastName()  {
		createAccountPage.populateCreateAccountFields("O", "O", "john.doeA@example.com", "Password123!", "Password123!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		actionDriver.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
		Log.info("Validating Error Message");
		test.info("Validating Error Message");
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.VER_ERROR_FIRST_NAME,"Must be at least 2 characters long."));
		Assert.assertTrue(actionDriver.verifyMessage(ElementLocators.VER_ERROR_LAST_NAME,"Must be at least 2 characters long."));
	}
	/*
	@Test
	public void testWeakPassword()  {
		createAccountPage.populateCreateAccountFields("Joaahnaaaa", "doeaaaa", "john.doaaaaa@example.com", "Abcdef1!", "Abcdef1!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		Log.info("Validating Account Registration");
		test.info("Validating Account Registration");
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	*/
}