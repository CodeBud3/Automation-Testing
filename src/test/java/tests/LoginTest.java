package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;
import utils.ExtentReportManager;
import utils.Log;

import java.lang.reflect.Method;
import enums.ActionTypes.ActionType;
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
		loginPage = new LoginPage(driver);
		Log.info("Navigating to login page");
		test.info("Navigating to login page");
	}

	@Test
	public void testValidLogin() {
		loginPage.populateLoginFields("john.doe@example.com", "Password123!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.LOGOUT, "Logout john"));
	}

	@Test
	public void testLoginWithInvalidCred() {
		loginPage.populateLoginFields("admin@yourstore.com", "admin");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(loginPage.isMessageDisplayed(ElementLocators.SIGN_IN_FORM_CONTAINER));
		Assert.assertTrue(
				loginPage.verifyMessage(ElementLocators.SIGN_IN_FORM_MESSAGE, "Incorrect email or password."));
	}

	@Test
	public void testCaseSensitivityEmail() {
		loginPage.populateLoginFields("JOHN.DOE@EXAMPLE.COM", "Password123!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.LOGOUT, "Logout john"));
	}

	@Test
	public void testPassVisToggle() {
		loginPage.populateLoginFields("john.doe@example.com", "Password123!");
		Assert.assertTrue(loginPage.verifyAttribute(ElementLocators.PASSWORD_VISIBILITY_TOGGLE, "type", "password"),
				"Expected password field to be type='password' initially");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.PASS_VIS_TOGGLE);
		Assert.assertTrue(loginPage.verifyAttribute(ElementLocators.PASSWORD_VISIBILITY_TOGGLE, "type", "text"),
				"Expected password field to be type='text' after toggling visibility");
		Log.info("Validating text");
		test.info("Validating text");
	}

	@Test
	public void testSpecialCharEmail() {
		loginPage.populateLoginFields("john.doe+test@example.com", "Password123!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.LOGOUT, "Logout john"));
	}

	@Test
	public void testSignInWithRememberMe() {
		loginPage.populateLoginFields("john.doe@example.com", "Password123!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.REMEMBER_ME);
		actionDriver.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.LOGOUT, "Logout john"));
		actionDriver.performAction(ActionType.CLICK, ElementLocators.LOGOUT);
		Assert.assertTrue(loginPage.verifyAttribute(ElementLocators.EMAIL_TEXT, "value", "john.doe@example.com"),
				"Expected password field to be type='text' after toggling visibility");
	}

	@Test
	public void testMaxEmailAndPassLength() {
		loginPage.populateLoginFields(
				"averylongemailaddresswithmultiplecharactersandrandomtexttotestvalidationpurpose@exampledomainfor.com",
				"A1b2C3d4E5f6G7h8I9j0K!L@M#N$O%P^Q&R*S(T)U_V+W=X-Y[Z]{a}b|c~d:e;f");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.LOGOUT,
				"Logout FirstNameJonathanAlexanderMaximillianChristopherAn"));
	}

	@Test
	public void testLoginWithEmptyCred() {
		actionDriver.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.EMAIL_MESSAGE, "Email is required."));
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.PASSWORD_MESSAGE, "Password is required."));
	}

	@Test
	public void testLoginWithInvalidPassword() {
		loginPage.populateLoginFields("john.doe@example.com", "Password124!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(
				loginPage.verifyMessage(ElementLocators.SIGN_IN_FORM_MESSAGE, "Incorrect email or password."));
	}

	@Test
	public void testNavSignUpPage() {
		actionDriver.performAction(ActionType.CLICK, ElementLocators.NAV_SIGN_UP);
		Log.info("Validating messages on the registration page");
		test.info("Validating messages on the registration page");
		Assert.assertTrue(loginPage.isMessageDisplayed(ElementLocators.REGISTRATION_PAGE));
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.REGISTRATION_PAGE, "Create an account"));
	}

	@Test
	public void clickSignUpButton() {
		actionDriver.performAction(ActionType.CLICK, ElementLocators.SIGN_UP);
		Log.info("Validating messages on the registration page");
		test.info("Validating messages on the registration page");
		Assert.assertTrue(loginPage.isMessageDisplayed(ElementLocators.REGISTRATION_PAGE));
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.REGISTRATION_PAGE, "Create an account"));
	}

	@Test
	public void testNavForgotPassword() {
		actionDriver.performAction(ActionType.CLICK, ElementLocators.FORGOT_PASSWORD);
		Log.info("Validating messages on the ForgotPassword page");
		test.info("Validating messages on the ForgotPassword page");
		Assert.assertTrue(loginPage.isMessageDisplayed(ElementLocators.FORGOT_PASSWORD_PAGE));
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.FORGOT_PASSWORD_PAGE, "Forgot password?"));
	}

	@Test
	public void testSqlInjectionAttemptEmail() {
		loginPage.populateLoginFields("'1'='1'", "Password123!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.EMAIL_MESSAGE, "Enter a valid email address."));
	}

	@Test
	public void testXSSAttemptEmail() {
		loginPage.populateLoginFields("<script>alert('XSS')</script>", "Password123!");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.EMAIL_MESSAGE, "Enter a valid email address."));
	}

	@Test
	public void testMultipleFailedSignin() {
		for (int attempt = 1; attempt <= 5; attempt++) {
			Log.info("Attempt " + attempt + ": Logging in with incorrect credentials...");
			test.info("Attempt " + attempt + ": Logging in with incorrect credentials...");
			loginPage.populateLoginFields("john.doe@example.com", "Password124!");
			actionDriver.performAction(ActionType.CLICK, ElementLocators.LOGIN);
			// Validate the "Incorrect email or password" error message
			Log.info("Validating error message for attempt " + attempt);
			test.info("Validating error message for attempt " + attempt);
			Assert.assertTrue(
					loginPage.verifyMessage(ElementLocators.SIGN_IN_FORM_MESSAGE, "Incorrect email or password."));
		}
	}

	@Test
	public void testSqlInjectionAttemptPassword() {
		loginPage.populateLoginFields("john.doe@example.com", "'1'='1'");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(
				loginPage.verifyMessage(ElementLocators.SIGN_IN_FORM_MESSAGE, "Incorrect email or password."));
	}

	@Test
	public void testXSSAttemptPassword() {
		loginPage.populateLoginFields("john.doe@example.com", "<script>alert('XSS')</script>");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(
				loginPage.verifyMessage(ElementLocators.SIGN_IN_FORM_MESSAGE, "Incorrect email or password."));
	}

	@Test
	public void testNavigateToGooleAuth() {
		actionDriver.performAction(ActionType.CLICK, ElementLocators.GOOGLE_AUTH);
		Log.info("Validating Google Sign In Page");
		test.info("Validating Microsoft Sign In Page");
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.GOOGLE_SIGN_IN, "Sign in"));
	}

	@Test
	public void testNavigateToMicrosoftAuth() {
		actionDriver.performAction(ActionType.CLICK, ElementLocators.MICROSOFT_AUTH);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.MICROSOFT_SIGN_IN, "Sign in"));
	}

	@Test
	public void testToolongEmailAndPasswordLength() {
		loginPage.populateLoginFields(
				"aaverylongemailaddresswithmultiplecharactersandrandomtexttotestvalidationpurpose@exampledomainfor.com",
				"A1b2C3d4E5f6G7h8I9j0K!L@M#N$O%P^Q&R*S(T)U_V+W=X-Y[Z]{a}b|c~d:e;fa");
		actionDriver.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(
				loginPage.verifyMessage(ElementLocators.EMAIL_MESSAGE, "Email cannot exceed 100 characters."));
	}

//	@Test
//	public void testSignInWithGoogleAuth() {
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
//		Assert.assertTrue(loginPage.verifyMessage("logoutButton", "Logout punith"),
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
//	@Test
//	public void testSignInWithMicroSoftAuth() {
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
//		Assert.assertTrue(loginPage.verifyMessage("logoutButton", "Logout Punith"),
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