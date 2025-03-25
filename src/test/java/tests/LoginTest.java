package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;
import utils.ExtentReportManager;
import utils.Log;


import java.lang.reflect.Method;

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
	/*
	@Test
	public void testLoginWithValidCred() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("john.doe@example.com", "Password123!");
		loginPage.clickLoginButton();
		Log.info("Validating Sign in messages");
		test.info("Validating Sign in messages");
		Assert.assertTrue(loginPage.verifyMessage("logoutButton", "Logout john"));
	}
	@Test
	public void testCaseSensitivityEmail() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("JOHN.DOE@EXAMPLE.COM", "Password123!");
		loginPage.clickLoginButton();
		Log.info("Validating Sign in messages");
		test.info("Validating Sign in messages");
		Assert.assertTrue(loginPage.verifyMessage("logoutButton", "Logout john"));
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
	}
	
	
	@Test
	public void testCaseSensitivityEmail() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("john.doe+test@example.com", "Password123!");
		loginPage.clickLoginButton();
		Log.info("Validating Sign in messages");
		test.info("Validating Sign in messages");
		Assert.assertTrue(loginPage.verifyMessage("logoutButton", "Logout john"));
	}
	*/
	@Test
	public void testSignInWithRememberMe() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("john.doe@example.com", "Password123!");
		loginPage.clickrememberMeButton();
		loginPage.clickLoginButton();
		Log.info("Validating Error messages");
		test.info("Validating Error messages");		
		Assert.assertTrue(loginPage.verifyMessage("logoutButton", "Logout john"));
	}
	
	
	
	/*
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
		Assert.assertTrue(loginPage.verifyErrorMessage("passwordErrorMessage", "Enter a valid password address."));
	}
	@Test
	public void testXSSAttemptPass() {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.populateLoginFields("john.doe@example.com", "<script>alert('XSS')</script>");
		loginPage.clickLoginButton();
		Log.info("Validating Error messages");
		test.info("Validating Error messages");
		Assert.assertTrue(loginPage.verifyErrorMessage("passwordErrorMessage", "Enter a valid password address."));
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
	}
	@Test
	public void testSignInWithGoogleAuth() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		loginPage.clickgoogleAuthButton();
		loginPage.googleLoginEmailFields("punith7760kumar@gmail.com");
		Thread.sleep(1000);
		loginPage.clickgoogleSignInbutton();
		Thread.sleep(1000);
		loginPage.googleLoginPassFields("7760502966punitH");
		loginPage.clickgoogleSignInbutton();
		Log.info("Validating Error messages");
		test.info("Validating Error messages");		
		Assert.assertTrue(loginPage.verifyMessage("logoutButton", "Logout john"));
	}*/
}