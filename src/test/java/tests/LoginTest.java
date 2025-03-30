package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;
import pages.LoginPage.ActionType;
import pages.LoginPage.ElementLocators;
import utils.ExtentReportManager;
import utils.Log;

import java.lang.reflect.Method;

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
	}
	
	@Test
	public void testValidLogin() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("john.doe@example.com", "Password123!");
		loginPage.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.LOGOUT, "Logout john"));
	}

	@Test
	public void testLoginWithInvalidCred() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("admin@yourstore.com", "admin");
		loginPage.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(loginPage.isMessageDisplayed(ElementLocators.SIGN_IN_FORM_CONTAINER));
		Assert.assertTrue(
				loginPage.verifyMessage(ElementLocators.SIGN_IN_FORM_MESSAGE, "Incorrect email or password."));
	}

	@Test
	public void testCaseSensitivityEmail() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("JOHN.DOE@EXAMPLE.COM", "Password123!");
		loginPage.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.LOGOUT, "Logout john"));
	}

	@Test
	public void testPassVisToggle() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("john.doe@example.com", "Password123!");
		Assert.assertTrue(loginPage.verifyAttribute(ElementLocators.PASSWORD_VISIBILITY_TOGGLE, "type", "password"),
				"Expected password field to be type='password' initially");
		loginPage.performAction(ActionType.CLICK, ElementLocators.PASS_VIS_TOGGLE);
		Assert.assertTrue(loginPage.verifyAttribute(ElementLocators.PASSWORD_VISIBILITY_TOGGLE, "type", "text"),
				"Expected password field to be type='text' after toggling visibility");
		Log.info("Validating text");
		test.info("Validating text");
	}

	@Test
	public void testSpecialCharEmail() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("john.doe+test@example.com", "Password123!");
		loginPage.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.LOGOUT, "Logout john"));
	}

	@Test
	public void testSignInWithRememberMe() {
		Log.info("Logging in with Remember Me...");
		test.info("Logging in with Remember Me...");
		loginPage.populateLoginFields("john.doe@example.com", "Password123!");
		loginPage.performAction(ActionType.CLICK, ElementLocators.REMEMBER_ME);
		loginPage.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.LOGOUT, "Logout john"));
		loginPage.performAction(ActionType.CLICK, ElementLocators.LOGOUT);
	    Assert.assertTrue(loginPage.verifyAttribute(ElementLocators.EMAIL_TEXT, "value", "john.doe@example.com"),
				"Expected password field to be type='text' after toggling visibility");
	}

	
	@Test
	public void testMaxEmailAndPassLength() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields(
				"averylongemailaddresswithmultiplecharactersandrandomtexttotestvalidationpurpose@exampledomainfor.com",
				"A1b2C3d4E5f6G7h8I9j0K!L@M#N$O%P^Q&R*S(T)U_V+W=X-Y[Z]{a}b|c~d:e;f");
		loginPage.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.LOGOUT, "Logout FirstNameJonathanAlexanderMaximillianChristopherAn"));
	}

	@Test
	public void testLoginWithEmptyCred() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.EMAIL_MESSAGE, "Email is required."));
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.PASSWORD_MESSAGE, "Password is required."));
	}
	
	@Test
	public void testLoginWithInvalidPassword() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("john.doe@example.com", "Password124!");
		loginPage.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.SIGN_IN_FORM_MESSAGE, "Incorrect email or password."));
	}

	@Test
	public void testNavSignUpPage() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.performAction(ActionType.CLICK, ElementLocators.NAV_SIGN_UP);
		Log.info("Validating messages on the registration page");
		test.info("Validating messages on the registration page");
		Assert.assertTrue(loginPage.isMessageDisplayed(ElementLocators.REGISTRATION_PAGE));
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.REGISTRATION_PAGE, "Create an account"));
	}
	
	@Test
	public void clickSignUpButton() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.performAction(ActionType.CLICK, ElementLocators.SIGN_UP);
		Log.info("Validating messages on the registration page");
		test.info("Validating messages on the registration page");
		Assert.assertTrue(loginPage.isMessageDisplayed(ElementLocators.REGISTRATION_PAGE));
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.REGISTRATION_PAGE, "Create an account"));
	}

	@Test
	public void testNavForgotPassword() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.performAction(ActionType.CLICK, ElementLocators.FORGOT_PASSWORD);
		Log.info("Validating messages on the ForgotPassword page");
		test.info("Validating messages on the ForgotPassword page");
		Assert.assertTrue(loginPage.isMessageDisplayed(ElementLocators.FORGOT_PASSWORD_PAGE));
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.FORGOT_PASSWORD_PAGE, "Forgot password?"));
	}
	
	@Test
	public void testSqlInjectionAttemptEmail() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("'1'='1'", "Password123!");
		loginPage.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.EMAIL_MESSAGE, "Enter a valid email address."));
	}
	
	@Test
	public void testXSSAttemptEmail() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("<script>alert('XSS')</script>", "Password123!");
		loginPage.performAction(ActionType.CLICK, ElementLocators.LOGIN);
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
			loginPage.performAction(ActionType.CLICK, ElementLocators.LOGIN);
			// Validate the "Incorrect email or password" error message
			Log.info("Validating error message for attempt " + attempt);
			test.info("Validating error message for attempt " + attempt);
			Assert.assertTrue(loginPage.verifyMessage(ElementLocators.SIGN_IN_FORM_MESSAGE, "Incorrect email or password."));			
		}
	}
	@Test
	public void testSqlInjectionAttemptPassword() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("john.doe@example.com", "'1'='1'");
		loginPage.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.SIGN_IN_FORM_MESSAGE, "Incorrect email or password."));
	}
	

	@Test
	public void testXSSAttemptPassword() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("john.doe@example.com", "<script>alert('XSS')</script>");
		loginPage.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.SIGN_IN_FORM_MESSAGE, "Incorrect email or password."));
	}
	@Test
	public void testNavigateToGooleAuth() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.performAction(ActionType.CLICK, ElementLocators.GOOGLE_AUTH);
		Log.info("Validating Google Sign In Page");
		test.info("Validating Microsoft Sign In Page");
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.GOOGLE_SIGN_IN, "Sign in"));
	}
	@Test
	public void testNavigateToMicrosoftAuth() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.performAction(ActionType.CLICK, ElementLocators.MICROSOFT_AUTH);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.MICROSOFT_SIGN_IN, "Sign in"));
	}
	@Test
	public void testToolongEmailAndPasswordLength() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields(
				"aaverylongemailaddresswithmultiplecharactersandrandomtexttotestvalidationpurpose@exampledomainfor.com",
				"A1b2C3d4E5f6G7h8I9j0K!L@M#N$O%P^Q&R*S(T)U_V+W=X-Y[Z]{a}b|c~d:e;fa");
		loginPage.performAction(ActionType.CLICK, ElementLocators.LOGIN);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(loginPage.verifyMessage(ElementLocators.EMAIL_MESSAGE, "Email cannot exceed 100 characters."));	
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