package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;
import pages.LoginPage.ButtonType;
import pages.LoginPage.EnterText;
import utils.ExtentReportManager;
import utils.Log;
import actiondriver.ActionDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.lang.reflect.Method;
import java.time.Duration;

public class LoginTest extends BaseTest {
	
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
	public void testLoginWithValidCred() {
	    Log.info("Logging in...");
	    test.info("Logging in...");
	    loginPage.populateLoginFields("john.doe@example.com", "Password123!");
	    loginPage.clickButton(ButtonType.LOGIN);
	    Log.info("Validating Sign in messages");
	    test.info("Validating Sign in messages");
	    Assert.assertTrue(loginPage.verifyMessage("logoutButton", "Logout john"));
	    ExtentReportManager.captureAndAttachScreenshot(driver, test, "LoginWithValidCred", "Screenshot after LoginWithValidCred", true);
	    Log.info("Logging out the user...");
	    test.info("Logging out the user...");
	    loginPage.logoutAndVerify();
	    Log.info("Successfully validated login and logged out.");
	    test.info("Successfully validated login and logged out.");
	}
	
	@Test
	public void testCaseSensitivityEmail() {
	    Log.info("Logging in...");
	    test.info("Logging in...");
	    loginPage.populateLoginFields("JOHN.DOE@EXAMPLE.COM", "Password123!");
	    loginPage.clickButton(ButtonType.LOGIN);
	    Log.info("Validating Sign in messages");
	    test.info("Validating Sign in messages");
	    Assert.assertTrue(loginPage.verifyMessage("logoutButton", "Logout john"));
	    ExtentReportManager.captureAndAttachScreenshot(driver, test, "CaseSensitivityEmail", "Screenshot after CaseSensitivityEmail", true);
	    Log.info("Logging out the user...");
	    test.info("Logging out the user...");
	    loginPage.logoutAndVerify();
	    Log.info("Successfully validated case sensitivity and logged out.");
	    test.info("Successfully validated case sensitivity and logged out.");
	}
	
	@Test
	public void testPassVisToggle() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("john.doe@example.com", "Password123!");
		loginPage.verifyToggleMessage("password");
		loginPage.clickButton(ButtonType.PASS_VIS_TOGGLE);
		loginPage.verifyToggleMessage("text");
		Log.info("Validating text");
		test.info("Validating text");
		Assert.assertTrue(loginPage.verifyToggleMessage("text"));
		ExtentReportManager.captureAndAttachScreenshot(driver, test, "PassVisToggle", "Screenshot after PassVisToggle", true);		
	}
	
	@Test
	public void testSpecialCharEmail() {
	    Log.info("Logging in...");
	    test.info("Logging in...");
		loginPage.populateLoginFields("john.doe+test@example.com", "Password123!");
		loginPage.clickButton(ButtonType.LOGIN);
	    Log.info("Validating Sign in messages");
	    test.info("Validating Sign in messages");
		Assert.assertTrue(loginPage.verifyMessage("logoutButton", "Logout john"));
	    ExtentReportManager.captureAndAttachScreenshot(driver, test, "SpecialCharEmail", "Screenshot after SpecialCharEmail", true);
	    Log.info("Logging out the user...");
	    test.info("Logging out the user...");
	    loginPage.clickButton(ButtonType.LOGOUT);
	    loginPage.logoutAndVerify();
	    Log.info("Successfully validated case sensitivity and logged out.");
	    test.info("Successfully validated case sensitivity and logged out.");
	}
	@Test
	public void testSignInWithRememberMe() {
	    Log.info("Logging in with Remember Me...");
	    test.info("Logging in with Remember Me...");
	    loginPage.populateLoginFields("john.doe@example.com", "Password123!");
	    loginPage.clickButton(ButtonType.REMEMBER_ME);
	    loginPage.clickButton(ButtonType.LOGIN);
	    Log.info("Validating initial login...");
	    test.info("Validating initial login...");
	    Assert.assertTrue(loginPage.verifyMessage("logoutButton", "Logout john"), 
	        "Expected 'Logout john' button to be displayed after initial login.");
	    Log.info("Closing the browser...");
	    test.info("Closing the browser...");
	    staticWait(2);
	    driver.quit();
	    driver = null;
	    Log.info("Reopening the browser...");
	    test.info("Reopening the browser...");
	    initDriver();
	    configureBrowser();
	    loginPage = new LoginPage(driver);
	    actionDriver = new ActionDriver(driver);
	    actionDriver.waitForPageLoad(10);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));
	    Log.info("Validating user is still logged in...");
	    test.info("Validating user is still logged in...");
	    Assert.assertTrue(loginPage.verifyMessage("logoutButton", "Logout john"), 
	        "Expected 'Logout john' button to be displayed after reopening the browser, indicating the user is still logged in.");
	    ExtentReportManager.captureAndAttachScreenshot(driver, test, "SignInWithRememberMe", "SignInWithRememberMe", true);
	    Log.info("Logging out the user...");
	    test.info("Logging out the user...");
	    loginPage.logoutAndVerify();
	    Log.info("Successfully validated Remember Me functionality and logged out.");
	    test.info("Successfully validated Remember Me functionality and logged out.");
	}

	@Test
	public void testSignInWithGoogleAuth() {
	    // Click the Google Auth button
	    Log.info("Logging in with Google Auth...");
	    test.info("Logging in with Google Auth...");
	    loginPage.clickButton(ButtonType.GOOGLE_AUTH);

	    // Enter email and click Next
	    loginPage.enterText(EnterText.GOOGLE_EMAIL, "punith7760kumar@gmail.com");
	    loginPage.clickButton(ButtonType.GOOGLE_SIGN_IN);

	    // Enter password and click Next
	    loginPage.enterText(EnterText.GOOGLE_PASSWORD, "7760502966punitH");
	    loginPage.clickButton(ButtonType.GOOGLE_SIGN_IN);

	    // Wait for the redirect to the logged-in page
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.urlContains("/dashboard"));

	    // Verify the user is logged in
	    Log.info("Validating login...");
	    test.info("Validating login...");
	    Assert.assertTrue(loginPage.verifyMessage("logoutButton", "Logout punith"), 
	        "Expected 'Logout punith' button to be displayed after Google Auth login.");
		ExtentReportManager.captureAndAttachScreenshot(driver, test, "SignInWithGoogleAuth", "Screenshot after SignInWithGoogleAuth", true);
	    // Log out the user
	    Log.info("Logging out the user...");
	    test.info("Logging out the user...");
	    loginPage.clickButton(ButtonType.LOGOUT);
	    loginPage.logoutAndVerify();
	    Log.info("Successfully validated Google Auth login and logged out.");
	    test.info("Successfully validated Google Auth login and logged out.");
	}
	

	@Test
	public void testSignInWithMicroSoftAuth() {

	    // Click the Microsoft Auth button
	    Log.info("Logging in with Microsoft Auth...");
	    test.info("Logging in with Microsoft Auth...");
	    loginPage.clickButton(ButtonType.MICROSOFT_AUTH);

	    // Enter email and click Next
	    loginPage.enterText(EnterText.MICROSOFT_EMAIL, "punith.y0909@outlook.com");
	    loginPage.clickButton(ButtonType.MICROSOFT_NEXT);

	    // Enter password and click Sign In
	    loginPage.enterText(EnterText.MICROSOFT_PASSWORD, "7760502966punitH");
	    loginPage.clickButton(ButtonType.MICROSOFT_SIGN_IN);

	    // Handle "Stay signed in" prompt (if it appears)
	    try {
	    	loginPage.clickButton(ButtonType.MICROSOFT_STAY_SIGNED_IN);
	    } catch (Exception e) {
	        Log.info("Stay signed in prompt did not appear: " + e.getMessage());
	        test.info("Stay signed in prompt did not appear: " + e.getMessage());
	    }

	    // Wait for the redirect to the logged-in page
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.urlContains("/dashboard"));

	    // Verify the user is logged in and capture screenshot
	    Log.info("Validating login...");
	    test.info("Validating login...");
	    Assert.assertTrue(loginPage.verifyMessage("logoutButton", "Logout Punith"), 
	        "Expected 'Logout punith' button to be displayed after Microsoft Auth login.");
	    ExtentReportManager.captureAndAttachScreenshot(driver, test, "SignInWithMicroSoftAuth", "SignInWithMicroSoftAuth", true);

	    // Log out the user and verify
	    Log.info("Logging out the user...");
	    test.info("Logging out the user...");
	    loginPage.logoutAndVerify();

	    Log.info("Successfully validated Microsoft Auth login and logged out.");
	    test.info("Successfully validated Microsoft Auth login and logged out.");
	}
	
	
	@Test
	public void testMaxEmailAndPassLength() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("averylongemailaddresswithmultiplecharactersandrandomtexttotestvalidationpurpose@exampledomainfor.com", "A1b2C3d4E5f6G7h8I9j0K!L@M#N$O%P^Q&R*S(T)U_V+W=X-Y[Z]{a}b|c~d:e;f");
		loginPage.clickButton(ButtonType.LOGIN);
		Log.info("Validating Sign in messages");
		test.info("Validating Sign in messages");
		Assert.assertTrue(loginPage.verifyMessage("logoutButton", "Logout FirstNameJonathanAlexanderMaximillianChristopherAn"));
	    ExtentReportManager.captureAndAttachScreenshot(driver, test, "MaxEmailAndPassLength", "Screenshot after MaxEmailAndPassLength", true);
	    Log.info("Logging out the user...");
	    test.info("Logging out the user...");
	    loginPage.logoutAndVerify();
	    Log.info("Successfully validated case sensitivity and logged out.");
	    test.info("Successfully validated case sensitivity and logged out.");
	}
	@Test
	public void testLoginWithInvalidCred() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("admin@yourstore.com", "admin");
		loginPage.clickButton(ButtonType.LOGIN);
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(loginPage.isMessageDisplayed("signInFormErrorContainer"));
		Assert.assertTrue(loginPage.verifyMessage("signInFormErrorMessage", "Incorrect email or password."));
		ExtentReportManager.captureAndAttachScreenshot(driver, test, "LoginWithInvalidCred", "Screenshot after LoginWithInvalidCred", true);
	}
	
	@Test
	public void testLoginWithEmptyCred() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.clickButton(ButtonType.LOGIN);
		Log.info("Validating Error messages");
		test.info("Validating Error messages");
		Assert.assertTrue(loginPage.verifyMessage("emailErrorMessage", "Email is required."));
		Assert.assertTrue(loginPage.verifyMessage("passwordErrorMessage", "Password is required."));
		ExtentReportManager.captureAndAttachScreenshot(driver, test, "LoginWithEmptyCred", "Screenshot after LoginWithEmptyCred", true);
	}
	@Test
	public void testLoginWithInvalidPassword() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("john.doe@example.com", "Password124!");
		loginPage.clickButton(ButtonType.LOGIN);
		Log.info("Validating Error messages");
		test.info("Validating Error messages");
		Assert.assertTrue(loginPage.verifyMessage("signInFormErrorMessage", "Incorrect email or password."));
		ExtentReportManager.captureAndAttachScreenshot(driver, test, "LoginWithInvalidPassword", "Screenshot after LoginWithInvalidPassword", true);
	}
	@Test
	public void testNavSignUpPage() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.clickButton(ButtonType.NAV_SIGN_UP);
		Log.info("Validating messages on the registration page");
		test.info("Validating messages on the registration page");
		Assert.assertTrue(loginPage.isMessageDisplayed("registrationPage"));
		Assert.assertTrue(loginPage.verifyMessage("registrationPage", "Create an account"));
		ExtentReportManager.captureAndAttachScreenshot(driver, test, "NavSignUpPage", "Screenshot after NavSignUpPage", true);
	}
	@Test
	public void clickSignUpButton() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.clickButton(ButtonType.SIGN_UP);
		Log.info("Validating messages on the registration page");
		test.info("Validating messages on the registration page");
		Assert.assertTrue(loginPage.isMessageDisplayed("registrationPage"));
		Assert.assertTrue(loginPage.verifyMessage("registrationPage", "Create an account"));
		ExtentReportManager.captureAndAttachScreenshot(driver, test, "SignUpButton", "Screenshot after SignUpButton", true);
	}
	@Test
	public void testNavForgotPassword() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.clickButton(ButtonType.FORGOT_PASSWORD);
		Log.info("Validating messages on the ForgotPassword page");
		test.info("Validating messages on the ForgotPassword page");
		Assert.assertTrue(loginPage.isMessageDisplayed("forgotPasswordPage"));
		Assert.assertTrue(loginPage.verifyMessage("forgotPasswordPage", "Forgot password?"));
		ExtentReportManager.captureAndAttachScreenshot(driver, test, "NavSignUpPage", "Screenshot after NavSignUpPage", true);
	}
	
	@Test
	public void testSqlInjectionAttemptEmail() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("'1'='1'", "Password123!");
		loginPage.clickButton(ButtonType.LOGIN);
		Log.info("Validating Error messages");
		test.info("Validating Error messages");
		Assert.assertTrue(loginPage.verifyMessage("emailErrorMessage", "Enter a valid email address."));
		ExtentReportManager.captureAndAttachScreenshot(driver, test, "SqlInjectionAttemptEmail", "Screenshot after SqlInjectionAttemptEmail", true);
	}
	@Test
	public void testXSSAttemptEmail() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("<script>alert('XSS')</script>", "Password123!");
		loginPage.clickButton(ButtonType.LOGIN);
		Log.info("Validating Error messages");
		test.info("Validating Error messages");
		Assert.assertTrue(loginPage.verifyMessage("emailErrorMessage", "Enter a valid email address."));
		ExtentReportManager.captureAndAttachScreenshot(driver, test, "XSSAttemptEmail", "Screenshot after XSSAttemptEmail", true);
	}
	@Test
	public void testMultipleFailedSignin() {
		for (int attempt = 1; attempt <= 5; attempt++) {
	        Log.info("Attempt " + attempt + ": Logging in with incorrect credentials...");
	        test.info("Attempt " + attempt + ": Logging in with incorrect credentials...");
	        loginPage.populateLoginFields("john.doe@example.com", "Password124!");
	        loginPage.clickButton(ButtonType.LOGIN);
	        // Validate the "Incorrect email or password" error message
	        Log.info("Validating error message for attempt " + attempt);
	        test.info("Validating error message for attempt " + attempt);
		Assert.assertTrue(loginPage.verifyMessage("signInFormErrorMessage", "Incorrect email or password."));
		ExtentReportManager.captureAndAttachScreenshot(driver, test, "MultipleFailedSignin", "Screenshot after MultipleFailedSignin", true);
		}
	}
	@Test
	public void testSqlInjectionAttemptPassword() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("john.doe@example.com", "'1'='1'");
		loginPage.clickButton(ButtonType.LOGIN);
		Log.info("Validating Error messages");
		test.info("Validating Error messages");
		Assert.assertTrue(loginPage.verifyMessage("signInFormErrorMessage", "Incorrect email or password."));
		ExtentReportManager.captureAndAttachScreenshot(driver, test, "SqlInjectionAttemptPassword", "Screenshot after SqlInjection", true);
	}
	
	@Test	
	public void testXSSAttemptPassword() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("john.doe@example.com", "<script>alert('XSS')</script>");
		loginPage.clickButton(ButtonType.LOGIN);
		Log.info("Validating Error messages");
		test.info("Validating Error messages");
		Assert.assertTrue(loginPage.verifyMessage("signInFormErrorMessage", "Incorrect email or password."));
		ExtentReportManager.captureAndAttachScreenshot(driver, test, "XSSAttemptPassword", "Screenshot after XSS attempt", true);
	}
}