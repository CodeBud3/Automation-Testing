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
		
	}
	/*
	@Test
	public void testSuccessfulAccountCreation() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.create("JohnA", "doeAB", "john.doeAB@example.com", "Password123!", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}*/
	@Test
	public void testEmptyFirstName() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.create("", "doe", "john.doe@example.com", "Password123!", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testEmptyLastName() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.create("John", "", "john.doe@example.com", "Password123!", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testInvalidEmailFormat() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.create("John", "doe", "john.doe@exampl", "Password123!", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testEmailAlreadyRegistered() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.create("John", "doe", "john.doe@example.com", "Password123!", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testPasswordMismatch() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.create("John", "doe", "john.doe@example.com", "Password123!", "Password124!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testWeakPassword() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.create("Joaahnaaaa", "doeaaaa", "john.doaaaaa@example.com", "Abcdef1!", "Abcdef1!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	//@Test
	//public void testTermsNotAgreed() throws InterruptedException {
	//	Log.info("Logging in...");
	//	test.info("Logging in...");
	//	createAccount.create("John", "doe", "john.doe@example.com", "Password123!", "Password123!");
	//	Log.info("Validating title");
	//	test.info("Validating title");
	//	Thread.sleep(5000);
	//	Assert.assertEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	//}
	//@Test
	//public void testSignInPage() throws InterruptedException {
	//	Log.info("Logging in...");
	//	test.info("Logging in...");
	//	createAccount.create("John", "doe", "john.doe@example.com", "Password123!", "Password123!");
	//	Log.info("Validating title");
	//	test.info("Validating title");
	//	Thread.sleep(5000);
	//	Assert.assertEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	//}
	@Test
	public void testSQLInjectionAttemptEmail() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.create("John", "doe", "'1'='1'", "Password123!", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testXSSAttemptFirstName() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.create("<script>alert('XSS')</script>", "doe", "john.doe@example.com", "Password123!", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testXSSAttemptLastName() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.create("John", "<script>alert('XSS')</script>", "john.doe@example.com", "Password123!", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testMaximumFieldLengths() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.create("FirstNameWithMaxLengthForTestingABCDEFGHIJABCDEF", "LastNameWithMaxLengthForTestingABCDEFGHIJABCDEFG", "longemailaddresswithmultiplecharactersandnumbers1234567890abcdefghijklmnopqrstuvwx@domainexample.com", "ApplebananaCherrydogLemonfishGrapeHorseiglooJuice7!xyz1ABCD1234", "ApplebananaCherrydogLemonfishGrapeHorseiglooJuice7!xyz1ABCD1234");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	//@Test
	//public void testVisibilityToggle() throws InterruptedException {
	//	Log.info("Logging in...");
	//	test.info("Logging in...");
	//	createAccount.create("John", "doe", "john.doe@example.com", "Password123!", "Password123!");
	//	Log.info("Validating title");
	//	test.info("Validating title");
	//	Thread.sleep(5000);
	//	Assert.assertEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	//}
	@Test
	public void testSpecialCharacterFirstName() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.create("Jöhn", "doe", "john.doe@example.com", "Password123!", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testSpecialCharacterLastName() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.create("John", "döe", "john.doe@example.com", "Password123!", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testMinimumPasswordLength() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.create("John", "doe", "john.doe@example.com", "Pass123!", "Pass123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testPasswordBelowMinimum() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.create("John", "doe", "john.doe@example.com", "Pas123!", "Pas123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testSQLInjectionAttemptPassword() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.create("John", "doe", "john.doe@example.com", "'1'='1'", "'1'='1'");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testFirstNameContainingOnlySpaces() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.create("  ", "doeA", "john.doeA@example.com", "Password123!", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testEmailContainingSpecialCharacters() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.create("John", "doeA", "john.doe+test@example.com", "Password123!", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testMinimumLengthFirstName() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.create("O", "doeA", "john.doeA@example.com", "Password123!", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testMinimumLengthLastName() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.create("John", "O", "john.doeA@example.com", "Password123!", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testEmptyEmailId() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.create("John", "doeA", "", "Password123!", "Password123!");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
	@Test
	public void testMaximumPasswordLength() throws InterruptedException {
		Log.info("Logging in...");
		test.info("Logging in...");
		createAccount.create("Johnaaa", "doeAaaa", "johasdan.doeA@example.com", "ApplebananaCherrydogLemonfishGrapeHorseiglooJuice7!xyz1ABCD1234", "ApplebananaCherrydogLemonfishGrapeHorseiglooJuice7!xyz1ABCD1234");
		Log.info("Validating title");
		test.info("Validating title");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getCurrentUrl(), "https://to-do-ggau.onrender.com/");
	}
}