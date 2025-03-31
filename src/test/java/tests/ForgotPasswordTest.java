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
		forgotPasswordPage.navigateToForgotPasswordPage();
		Log.info("Navigating to forgot password");
		test.info("Navigating to forgot password");
	}
	
	@Test
	public void testSignUpButton() {
		forgotPasswordPage.performAction(ActionType.CLICK, ElementLocators.SIGN_UP);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(forgotPasswordPage.verifyMessage(ElementLocators.CREATE_ACCOUNT_PAGE, "Create an account"));
	}
	@Test
	public void testLoginPageLink() {
		forgotPasswordPage.performAction(ActionType.CLICK, ElementLocators.LOGIN_PAGE);
		Log.info("Validating Successful Sign in");
		test.info("Validating Successful Sign in");
		Assert.assertTrue(forgotPasswordPage.verifyMessage(ElementLocators.SIGN_IN_PAGE, "Sign in to your account"));
	}
	
}