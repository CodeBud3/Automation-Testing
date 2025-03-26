package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;

import base.BaseTest;
import pages.LoginPage;
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
	    Log.info("Clearing cookies before test...");
	    test.info("Clearing cookies before test...");
	    driver.manage().deleteAllCookies();
	    Log.info("Logging in...");
	    test.info("Logging in...");
	    loginPage.populateLoginFields("john.doe@example.com", "Password123!");
	    loginPage.clickLoginButton();
	    Log.info("Validating Sign in messages");
	    test.info("Validating Sign in messages");
	    Assert.assertTrue(loginPage.verifyMessage("logoutButton", "Logout john"));
	    String screenshotPath = ExtentReportManager.captureScreenshot(driver, "ValidCredLogin_" + System.currentTimeMillis());
	    Log.info("Screenshot captured after login at: " + screenshotPath);
	    test.pass("Screenshot after login", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	    Log.info("Logging out the user...");
	    test.info("Logging out the user...");
	    loginPage.clickLogoutButton();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    wait.until(ExpectedConditions.urlContains("/login"));
	    Assert.assertTrue(loginPage.isMessageDisplayed("emailTextBox"), 
	        "Expected to be redirected to the login page after logout.");
	    Log.info("Successfully validated login and logged out.");
	    test.info("Successfully validated login and logged out.");
	}
	@Test
	public void testCaseSensitivityEmail() {
	    Log.info("Clearing cookies before test...");
	    test.info("Clearing cookies before test...");
	    driver.manage().deleteAllCookies();
	    Log.info("Logging in...");
	    test.info("Logging in...");
	    loginPage.populateLoginFields("JOHN.DOE@EXAMPLE.COM", "Password123!");
	    loginPage.clickLoginButton();
	    Log.info("Validating Sign in messages");
	    test.info("Validating Sign in messages");
	    Assert.assertTrue(loginPage.verifyMessage("logoutButton", "Logout john"));
	    String screenshotPath = ExtentReportManager.captureScreenshot(driver, "CaseSensitivityLogin_" + System.currentTimeMillis());
	    Log.info("Screenshot captured after login at: " + screenshotPath);
	    test.pass("Screenshot after login", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	    Log.info("Logging out the user...");
	    test.info("Logging out the user...");
	    loginPage.clickLogoutButton();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    wait.until(ExpectedConditions.urlContains("/login"));
	    Assert.assertTrue(loginPage.isMessageDisplayed("emailTextBox"), 
	        "Expected to be redirected to the login page after logout.");
	    Log.info("Successfully validated case sensitivity and logged out.");
	    test.info("Successfully validated case sensitivity and logged out.");
	}
	@Test
	public void testPassVisToggle() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("john.doe@example.com", "Password123!");
		loginPage.verifyToggleMessage("password");
		loginPage.clickpassVisToggle();
		loginPage.verifyToggleMessage("text");
		Log.info("Validating text");
		test.info("Validating text");
		Assert.assertTrue(loginPage.verifyToggleMessage("text"));
		String screenshotPath = ExtentReportManager.captureScreenshot(driver, "CaseSensitivityLogin_" + System.currentTimeMillis());
	    Log.info("Screenshot captured after login at: " + screenshotPath);
	    test.pass("Screenshot after login", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		
	}
	@Test
	public void testSpecialCharEmail() {
		Log.info("Clearing cookies before test...");
	    test.info("Clearing cookies before test...");
	    driver.manage().deleteAllCookies();
	    Log.info("Logging in...");
	    test.info("Logging in...");
		loginPage.populateLoginFields("john.doe+test@example.com", "Password123!");
		loginPage.clickLoginButton();
	    Log.info("Validating Sign in messages");
	    test.info("Validating Sign in messages");
		Assert.assertTrue(loginPage.verifyMessage("logoutButton", "Logout john"));
	    String screenshotPath = ExtentReportManager.captureScreenshot(driver, "CaseSensitivityLogin_" + System.currentTimeMillis());
	    Log.info("Screenshot captured after login at: " + screenshotPath);
	    test.pass("Screenshot after login", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	    Log.info("Logging out the user...");
	    test.info("Logging out the user...");
	    loginPage.clickLogoutButton();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    wait.until(ExpectedConditions.urlContains("/login"));
	    Assert.assertTrue(loginPage.isMessageDisplayed("emailTextBox"), 
	        "Expected to be redirected to the login page after logout.");
	    Log.info("Successfully validated case sensitivity and logged out.");
	    test.info("Successfully validated case sensitivity and logged out.");
	}
	@Test
	public void testSignInWithRememberMe() {
	    // Step 1: Initialize LoginPage
	    loginPage = new LoginPage(driver);

	    // Step 2: Log in with "Remember Me" enabled
	    Log.info("Logging in with Remember Me...");
	    test.info("Logging in with Remember Me...");
	    loginPage.populateLoginFields("john.doe@example.com", "Password123!");
	    loginPage.clickrememberMeButton();
	    loginPage.clickLoginButton();

	    // Step 3: Verify initial login
	    Log.info("Validating initial login...");
	    test.info("Validating initial login...");
	    Assert.assertTrue(loginPage.verifyMessage("logoutButton", "Logout john"), 
	        "Expected 'Logout john' button to be displayed after initial login.");

	    // Step 4: Close the browser
	    Log.info("Closing the browser...");
	    test.info("Closing the browser...");
	    staticWait(2);
	    driver.quit();
	    driver = null;

	    // Step 5: Reopen the browser
	    Log.info("Reopening the browser...");
	    test.info("Reopening the browser...");
	    initDriver();
	    configureBrowser();

	    // Step 6: Reinitialize LoginPage
	    loginPage = new LoginPage(driver);
	    actionDriver = new ActionDriver(driver);

	    // Step 7: Wait for redirect to the logged-in page
	    actionDriver.waitForPageLoad(30);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));

	    // Step 8: Verify the user is still logged in
	    Log.info("Validating user is still logged in...");
	    test.info("Validating user is still logged in...");
	    Assert.assertTrue(loginPage.verifyMessage("logoutButton", "Logout john"), 
	        "Expected 'Logout john' button to be displayed after reopening the browser, indicating the user is still logged in.");
	    String screenshotPath = ExtentReportManager.captureScreenshot(driver, "CaseSensitivityLogin_" + System.currentTimeMillis());
	    Log.info("Screenshot captured after login at: " + screenshotPath);
	    test.pass("Screenshot after login", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

	    // Step 9: Log out the user
	    Log.info("Logging out the user...");
	    test.info("Logging out the user...");
	    loginPage.clickLogoutButton();
	    //wait.until(ExpectedConditions.urlContains("/login"));
	    //Assert.assertTrue(loginPage.isMessageDisplayed("emailTextBox"), 
	    //    "Expected to be redirected to the login page after logout.");

	    Log.info("Successfully validated Remember Me functionality and logged out.");
	    test.info("Successfully validated Remember Me functionality and logged out.");
	}
	
	@Test
	public void testSignInWithGoogleAuth() {
	    // Step 1: Clear cookies to ensure a clean session
	    Log.info("Clearing cookies before test...");
	    test.info("Clearing cookies before test...");
	    driver.manage().deleteAllCookies();

	    // Step 2: Click the Google Auth button
	    Log.info("Logging in with Google Auth...");
	    test.info("Logging in with Google Auth...");
	    loginPage.clickgoogleAuthButton();

	    // Step 3: Switch to the Google login window
	    loginPage.switchToGoogleLoginWindow();

	    // Step 4: Enter email and click Next
	    loginPage.googleLoginEmailFields("punith7760kumar@gmail.com");
	    loginPage.clickgoogleSignInbutton();

	    // Step 5: Enter password and click Next
	    loginPage.googleLoginPassFields("7760502966punitH");
	    loginPage.clickgoogleSignInbutton();

	    // Step 6: Switch back to the main window
	    loginPage.switchToMainWindow();

	    // Step 7: Wait for the redirect to the logged-in page
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    wait.until(ExpectedConditions.urlContains("/dashboard"));

	    // Step 8: Verify the user is logged in
	    Log.info("Validating login...");
	    test.info("Validating login...");
	    Assert.assertTrue(loginPage.verifyMessage("logoutButton", "Logout punith"), 
	        "Expected 'Logout punith' button to be displayed after Google Auth login.");
		String screenshotPath = ExtentReportManager.captureScreenshot(driver, "MicrosoftAuthLogin_" + System.currentTimeMillis());
		Log.info("Screenshot captured after Microsoft Auth login at: " + screenshotPath);
		test.pass("Screenshot after Microsoft Auth login", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	    // Step 9: Log out the user
	    Log.info("Logging out the user...");
	    test.info("Logging out the user...");
	    loginPage.clickLogoutButton();

	    Log.info("Successfully validated Google Auth login and logged out.");
	    test.info("Successfully validated Google Auth login and logged out.");
	}
	@Test
	public void testSignInWithMicroSoftAuth() {
	    // Step 1: Clear cookies to ensure a clean session
	    Log.info("Clearing cookies before test...");
	    test.info("Clearing cookies before test...");
	    driver.manage().deleteAllCookies();

	    // Step 2: Click the Microsoft Auth button
	    Log.info("Logging in with Microsoft Auth...");
	    test.info("Logging in with Microsoft Auth...");
	    loginPage.clickMicrosoftAuthButton();

	    // Step 3: Switch to the Microsoft login window
	    loginPage.switchToAuthWindow("Microsoft");

	    // Step 4: Enter email and click Next
	    loginPage.microsoftLoginEmailFields("punith.y0909@outlook.com");
	    loginPage.clickMicrosoftNextInbutton();

	    // Step 5: Enter password and click Sign In
	    loginPage.microsoftLoginPassFields("7760502966punitH");
	    loginPage.clickMicrosoftSignInbutton();

	    // Step 6: Handle "Stay signed in" prompt (if it appears)
	    try {
	        loginPage.clickMicrosoftStaySignedInbutton();
	    } catch (Exception e) {
	        Log.info("Stay signed in prompt did not appear: " + e.getMessage());
	        test.info("Stay signed in prompt did not appear: " + e.getMessage());
	    }

	    // Step 7: Switch back to the main window
	    loginPage.switchToMainWindow();

	    // Step 8: Wait for the redirect to the logged-in page
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    wait.until(ExpectedConditions.urlContains("/dashboard"));

	    // Step 9: Verify the user is logged in and capture screenshot
	    Log.info("Validating login...");
	    test.info("Validating login...");
	    Assert.assertTrue(loginPage.verifyMessage("logoutButton", "Logout Punith"), 
	        "Expected 'Logout punith' button to be displayed after Microsoft Auth login.");
	    String screenshotPath = ExtentReportManager.captureScreenshot(driver, "MicrosoftAuthLogin_" + System.currentTimeMillis());
	    Log.info("Screenshot captured after Microsoft Auth login at: " + screenshotPath);
	    test.pass("Screenshot after Microsoft Auth login", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

	    // Step 10: Log out the user and verify
	    Log.info("Logging out the user...");
	    test.info("Logging out the user...");
	    loginPage.clickLogoutButton();
	    //wait.until(ExpectedConditions.urlContains("/login"));
	    //Assert.assertTrue(loginPage.isMessageDisplayed("emailTextBox"), 
	    //    "Expected to be redirected to the login page after logout.");

	    Log.info("Successfully validated Microsoft Auth login and logged out.");
	    test.info("Successfully validated Microsoft Auth login and logged out.");
	}
	@Test
	public void testMaxEmailAndPassLength() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("averylongemailaddresswithmultiplecharactersandrandomtexttotestvalidationpurpose@exampledomainfor.com", "A1b2C3d4E5f6G7h8I9j0K!L@M#N$O%P^Q&R*S(T)U_V+W=X-Y[Z]{a}b|c~d:e;f");
		loginPage.clickLoginButton();
		Log.info("Validating Error messages");
		test.info("Validating Error messages");
		Assert.assertTrue(loginPage.verifyMessage("logoutButton", "Logout FirstNameJonathanAlexanderMaximillianChristopherAn"));
	    String screenshotPath = ExtentReportManager.captureScreenshot(driver, "CaseSensitivityLogin_" + System.currentTimeMillis());
	    Log.info("Screenshot captured after login at: " + screenshotPath);
	    test.pass("Screenshot after login", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	    Log.info("Logging out the user...");
	    test.info("Logging out the user...");
	    loginPage.clickLogoutButton();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    wait.until(ExpectedConditions.urlContains("/login"));
	    Assert.assertTrue(loginPage.isMessageDisplayed("emailTextBox"), 
	        "Expected to be redirected to the login page after logout.");
	    Log.info("Successfully validated case sensitivity and logged out.");
	    test.info("Successfully validated case sensitivity and logged out.");
	}
	@Test
	public void testLoginWithInvalidCred() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("admin@yourstore.com", "admin");
		loginPage.clickLoginButton();
		Log.info("Validating Sign in error message");
		test.info("Validating Sign in error message");
		Assert.assertTrue(loginPage.isErrorMessagedDisplayed("signInFormErrorContainer"));
		Assert.assertTrue(loginPage.verifyErrorMessage("signInFormErrorMessage", "Incorrect email or password."));
		String screenshotPath = ExtentReportManager.captureScreenshot(driver, "CaseSensitivityLogin_" + System.currentTimeMillis());
	    Log.info("Screenshot captured after login at: " + screenshotPath);
	    test.pass("Screenshot after login", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	}
	
	@Test
	public void testLoginWithEmptyCred() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.clickLoginButton();
		Log.info("Validating Error messages");
		test.info("Validating Error messages");
		Assert.assertTrue(loginPage.verifyErrorMessage("emailErrorMessage", "Email is required."));
		Assert.assertTrue(loginPage.verifyErrorMessage("passwordErrorMessage", "Password is required."));
		String screenshotPath = ExtentReportManager.captureScreenshot(driver, "CaseSensitivityLogin_" + System.currentTimeMillis());
	    Log.info("Screenshot captured after login at: " + screenshotPath);
	    test.pass("Screenshot after login", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	}
	@Test
	public void testLoginWithInvalidEmail() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("johna.doe@example.com", "Password123!");
		loginPage.clickLoginButton();
		Log.info("Validating Error messages");
		test.info("Validating Error messages");
		Assert.assertTrue(loginPage.verifyErrorMessage("signInFormErrorMessage", "Incorrect email or password."));
		String screenshotPath = ExtentReportManager.captureScreenshot(driver, "CaseSensitivityLogin_" + System.currentTimeMillis());
	    Log.info("Screenshot captured after login at: " + screenshotPath);
	    test.pass("Screenshot after login", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	}
	@Test
	public void testLoginWithInvalidPassword() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("john.doe@example.com", "Password124!");
		loginPage.clickLoginButton();
		Log.info("Validating Error messages");
		test.info("Validating Error messages");
		Assert.assertTrue(loginPage.verifyErrorMessage("signInFormErrorMessage", "Incorrect email or password."));
		String screenshotPath = ExtentReportManager.captureScreenshot(driver, "CaseSensitivityLogin_" + System.currentTimeMillis());
	    Log.info("Screenshot captured after login at: " + screenshotPath);
	    test.pass("Screenshot after login", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	}
	@Test
	public void testNavSignUpPage() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.clickNavSignUpButton();
		Log.info("Validating messages on the registration page");
		test.info("Validating messages on the registration page");
		Assert.assertTrue(loginPage.isMessageDisplayed("registrationPage"));
		Assert.assertTrue(loginPage.verifyMessage("registrationPage", "Create an account"));
		String screenshotPath = ExtentReportManager.captureScreenshot(driver, "CaseSensitivityLogin_" + System.currentTimeMillis());
	    Log.info("Screenshot captured after login at: " + screenshotPath);
	    test.pass("Screenshot after login", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	}
	@Test
	public void clickSignUpButton() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.clickNavSignUpButton();
		Log.info("Validating messages on the registration page");
		test.info("Validating messages on the registration page");
		Assert.assertTrue(loginPage.isMessageDisplayed("registrationPage"));
		Assert.assertTrue(loginPage.verifyMessage("registrationPage", "Create an account"));
		String screenshotPath = ExtentReportManager.captureScreenshot(driver, "CaseSensitivityLogin_" + System.currentTimeMillis());
	    Log.info("Screenshot captured after login at: " + screenshotPath);
	    test.pass("Screenshot after login", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	}
	@Test
	public void testSqlInjectionAttemptEmail() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("'1'='1'", "Password123!");
		loginPage.clickLoginButton();
		Log.info("Validating Error messages");
		test.info("Validating Error messages");
		Assert.assertTrue(loginPage.verifyErrorMessage("emailErrorMessage", "Enter a valid email address."));
		String screenshotPath = ExtentReportManager.captureScreenshot(driver, "CaseSensitivityLogin_" + System.currentTimeMillis());
	    Log.info("Screenshot captured after login at: " + screenshotPath);
	    test.pass("Screenshot after login", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	}
	@Test
	public void testXSSAttemptEmail() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("<script>alert('XSS')</script>", "Password123!");
		loginPage.clickLoginButton();
		Log.info("Validating Error messages");
		test.info("Validating Error messages");
		Assert.assertTrue(loginPage.verifyErrorMessage("emailErrorMessage", "Enter a valid email address."));
		String screenshotPath = ExtentReportManager.captureScreenshot(driver, "CaseSensitivityLogin_" + System.currentTimeMillis());
	    Log.info("Screenshot captured after login at: " + screenshotPath);
	    test.pass("Screenshot after login", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	}
	@Test
	public void testMultipleFailedSignin() {
		for (int attempt = 1; attempt <= 5; attempt++) {
	        Log.info("Attempt " + attempt + ": Logging in with incorrect credentials...");
	        test.info("Attempt " + attempt + ": Logging in with incorrect credentials...");
	        loginPage.populateLoginFields("john.doe@example.com", "Password124!");
	        loginPage.clickLoginButton();
	        // Validate the "Incorrect email or password" error message
	        Log.info("Validating error message for attempt " + attempt);
	        test.info("Validating error message for attempt " + attempt);
		Assert.assertTrue(loginPage.verifyErrorMessage("signInFormErrorMessage", "Incorrect email or password."));
		String screenshotPath = ExtentReportManager.captureScreenshot(driver, "CaseSensitivityLogin_" + System.currentTimeMillis());
	    Log.info("Screenshot captured after login at: " + screenshotPath);
	    test.pass("Screenshot after login", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
	}
	@Test
	public void testSqlInjectionAttemptPass() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("john.doe@example.com", "'1'='1'");
		loginPage.clickLoginButton();
		Log.info("Validating Error messages");
		test.info("Validating Error messages");
		Assert.assertTrue(loginPage.verifyErrorMessage("signInFormErrorMessage", "Incorrect email or password."));
		String screenshotPath = ExtentReportManager.captureScreenshot(driver, "CaseSensitivityLogin_" + System.currentTimeMillis());
	    Log.info("Screenshot captured after login at: " + screenshotPath);
	    test.pass("Screenshot after login", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	}
	
	@Test
	public void testXSSAttemptPass() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("john.doe@example.com", "<script>alert('XSS')</script>");
		loginPage.clickLoginButton();
		Log.info("Validating Error messages");
		test.info("Validating Error messages");
		Assert.assertTrue(loginPage.verifyErrorMessage("signInFormErrorMessage", "Incorrect email or password."));
		String screenshotPath = ExtentReportManager.captureScreenshot(driver, "CaseSensitivityLogin_" + System.currentTimeMillis());
	    Log.info("Screenshot captured after login at: " + screenshotPath);
	    test.pass("Screenshot after login", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	}
}