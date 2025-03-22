package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.createAccount;
import utils.ExtentReportManager;
import utils.Log;

import java.lang.reflect.Method;


public class createAccountTest extends BaseTest {
	
	private createAccount createAccount;
	@BeforeMethod
	public void setupPage(Method method) {
		// Use reflection to get the test method name
        String testMethodName = method.getName();
        // Set the test title dynamically
        test = ExtentReportManager.createTest(testMethodName);
		test.info("Navigating to login page");
		createAccount = new createAccount(driver);
		
	}/*
	@Test
	public void testSuccessfulAccountCreation() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.populate("John", "doe", "johnaa.doe@example.com", "Password123!", "Password123!", true);
		createAccount.clickCreate();
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testMaximumFieldLengths() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.populate("FirstNameWithMaxLengthForTestingABCDEFGHIJABCDEF", "LastNameWithMaxLengthForTestingABCDEFGHIJABCDEFG", "longemailaddresswithmultiplecharactersandnumbers1234567890abcdefghijklmnopqrstuvwx@domainexample.com", "ApplebananaCherrydogLemonfishGrapeHorseiglooJuice7!xyz1ABCD1234", "ApplebananaCherrydogLemonfishGrapeHorseiglooJuice7!xyz1ABCD1234", true);
		createAccount.clickCreate();
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testWeakPassword() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.populate("Joaahnaaaa", "doeaaaa", "john.doaaaaa@example.com", "Abcdef1!", "Abcdef1!", true);
		createAccount.clickCreate();
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testNavigateToSignInByLink() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.navigateToSignInByLink();
		Log.info("Validating title");
		test.info("Validating title");
		Assert.assertEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/login");
	}
	@Test
	public void testNavigateToLoginButton() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.navigateToLoginByButton();
		Log.info("Validating title");
		test.info("Validating title");
		Assert.assertEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/login");
	}
	@Test
	public void testMinimumPasswordLength() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.populate("John", "doe", "john.doe@example.com", "Pass123!", "Pass123!", true);
		createAccount.clickCreate();
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testEmailContainingSpecialCharacters() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.populate("John", "doeA", "john.doe+test@example.com", "Password123!", "Password123!", true);
		createAccount.clickCreate();
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testMaximumPasswordLength() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.populate("Johnaaa", "doeAaaa", "johasdan.doeA@example.com", "ApplebananaCherrydogLemonfishGrapeHorseiglooJuice7!xyz1ABCD1234", "ApplebananaCherrydogLemonfishGrapeHorseiglooJuice7!xyz1ABCD1234", true);
		createAccount.clickCreate();
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	*/
	/*
	@Test
	public void testEmptyFirstName() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.populate("", "doe", "john.doe@example.com", "Password123!", "Password123!", true);
		createAccount.clickCreate();
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertTrue(createAccount.verifyErrorMessageSingUpPage("First name is required."));
	}
	@Test
	public void testEmptyLastName() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.populate("John", "", "john.doe@example.com", "Password123!", "Password123!", true);
		createAccount.clickCreate();
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertTrue(createAccount.verifyErrorMessageSingUpPage("Last name is required."));
	}
	@Test
	public void testInvalidEmailFormat() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.populate("John", "doe", "john.doe@exampl", "Password123!", "Password123!", true);
		createAccount.clickCreate();
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertTrue(createAccount.verifyErrorMessageSingUpPage("Enter a valid email address."));
	}
	@Test
	public void testEmailAlreadyRegistered() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.populate("John", "doe", "john.doe@example.com", "Password123!", "Password123!", true);
		createAccount.clickCreate();
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertTrue(createAccount.verifyErrorEmailRegistered("Email already registered"));
	}
	@Test
	public void testPasswordMismatch() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.populate("John", "doe", "john.doe@example.com", "Password123!", "Password124!", true);
		createAccount.clickCreate();
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertTrue(createAccount.verifyErrorMessageSingUpPage("Passwords do not match."));
	}
	@Test
	public void testTermsNotAgreed() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.populate("John", "doe", "john.doe@example.com", "Password123!", "Password123!", false);
		createAccount.clickCreate();
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertTrue(createAccount.verifyErrorMessageSingUpPage("You must agree to the Terms and Conditions."));
	}
	@Test
	public void testSQLInjectionAttemptEmail() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.populate("John", "doe", "'1'='1'", "Password123!", "Password123!", true);
		createAccount.clickCreate();
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertTrue(createAccount.verifyErrorMessageSingUpPage("Enter a valid email address."));
	}
	@Test
	public void testXSSAttemptFirstName() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.populate("<script>alert('XSS')</script>", "doe", "john.doe@example.com", "Password123!", "Password123!", true);
		createAccount.clickCreate();
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertTrue(createAccount.verifyErrorMessageSingUpPage("Only letters, spaces, hyphens (-), and apostrophes (') are allowed."));
	}
	@Test
	public void testXSSAttemptLastName() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.populate("John", "<script>alert('XSS')</script>", "john.doe@example.com", "Password123!", "Password123!", true);
		createAccount.clickCreate();
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertTrue(createAccount.verifyErrorMessageSingUpPage("Only letters, spaces, hyphens (-), and apostrophes (') are allowed."));
	}*/
	@Test
	public void testVerifyToggle() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.populate("John", "doe", "john.doe@example.com", "Password123!", "Password123!", true);
		createAccount.verifyToggleMessage("password");
		Thread.sleep(2000);
		createAccount.togglePassword();
		createAccount.verifyToggleMessage("text");
		createAccount.toggleConfirmPassword();
		Log.info("Validating title");
		test.info("Validating title");
		Assert.assertTrue(createAccount.verifyToggleMessage("text"));
	}/*
	@Test
	public void testSpecialCharacterFirstName() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.populate("Jöhn", "doe", "john.doe@example.com", "Password123!", "Password123!", true);
		createAccount.clickCreate();
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertTrue(createAccount.verifyErrorMessageSingUpPage("Only letters, spaces, hyphens (-), and apostrophes (') are allowed."));
	}
	@Test
	public void testSpecialCharacterLastName() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.populate("John", "döe", "john.doe@example.com", "Password123!", "Password123!", true);
		createAccount.clickCreate();
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertTrue(createAccount.verifyErrorMessageSingUpPage("Only letters, spaces, hyphens (-), and apostrophes (') are allowed."));
	}
	
	@Test
	public void testPasswordBelowMinimum() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.populate("John", "doe", "john.doe@example.com", "Pas123!", "Pas123!", true);
		createAccount.clickCreate();
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertTrue(createAccount.verifyErrorMessageSingUpPage("Password must be at least 8 characters long."));
	}
	@Test
	public void testSQLInjectionAttemptPassword() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.populate("John", "doe", "john.doe@example.com", "'1'='1'", "'1'='1'", true);
		createAccount.clickCreate();
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertTrue(createAccount.verifyErrorMessageSingUpPage("Password must be at least 8 characters long."));
	}
	@Test
	public void testFirstNameContainingOnlySpaces() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.populate("  ", "doeA", "john.doeA@example.com", "Password123!", "Password123!", true);
		createAccount.clickCreate();
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertTrue(createAccount.verifyErrorMessageSingUpPage("Must be at least 2 characters long."));
	}
	
	@Test
	public void testMinimumLengthFirstName() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.populate("O", "doeA", "john.doeA@example.com", "Password123!", "Password123!", true);
		createAccount.clickCreate();
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertTrue(createAccount.verifyErrorMessageSingUpPage("Must be at least 2 characters long."));
	}
	@Test
	public void testMinimumLengthLastName() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.populate("John", "O", "john.doeA@example.com", "Password123!", "Password123!", true);
		createAccount.clickCreate();
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertTrue(createAccount.verifyErrorMessageSingUpPage("Must be at least 2 characters long."));
	}
	@Test
	public void testEmptyEmailId() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.populate("John", "doeA", "", "Password123!", "Password123!", true);
		createAccount.clickCreate();
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertTrue(createAccount.verifyErrorMessageSingUpPage("Email is required."));
	}*/
}