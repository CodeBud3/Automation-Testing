package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.AttributeHelper;
import utils.Log;
import actiondriver.ActionDriver;

public class ForgotPasswordPage {
	private WebDriver driver;
	private ActionDriver actionDriver;

	public ForgotPasswordPage(WebDriver driver) {
		this.driver = driver;
		this.actionDriver = new ActionDriver(driver); // Initialize with the provided driver
	}
	// Locators

	public enum ElementLocators {

		SIGN_UP("Sign Up Button", AttributeHelper.getElementByAttribute("button", "nav-signup-link")),
		LOGIN_PAGE("Return To Login Page Link", AttributeHelper.getElementByAttribute("a", "login-link")),
		EMAIL_FIELD("Email Field Text Box", AttributeHelper.getElementByAttribute("input", "field-email")),
		SIGN_IN_PAGE("Sign In Page Message", AttributeHelper.getElementByAttribute("div", "signin-card-title")),
		CREATE_ACCOUNT_PAGE("Create Account Page", AttributeHelper.getElementByAttribute("div", "signup-card-title")),
		PASSWORD_RESET("Password Reset Button", AttributeHelper.getElementByAttribute("button", "button-submit")),
		CONFIRM_RESET("Password Reset Confirm", AttributeHelper.getElementByAttribute("button", "alert-confirm")),
		PASSWORD("Password Text Box", AttributeHelper.getElementByAttribute("input", "field-password")),
		CONFIRM_PASSWORD("Confirm Password Text Box", AttributeHelper.getElementByAttribute("Input", "field-confirmpassword")),
		PASSWORD_TOGGLE("Toggle Password Button", AttributeHelper.getElementByAttribute("button", "password-visibility")),
		CONFIRM_PASSWORD_TOGGLE("Toggle Confirm Password Button", AttributeHelper.getElementByAttribute("button", "confirmpassword-visibility")),
		SAVE_PASSWORD("Save Password Button", AttributeHelper.getElementByAttribute("button", "button-submit")),
		VER_ERROR_EMAIL_ID("Verify Error Email ID", AttributeHelper.getElementByAttribute("p", "errormsg-email")),
		VER_ERROR_PASSWORD("Verify Error Password", AttributeHelper.getElementByAttribute("p", "errormsg-password")),
		VER_ERROR_CONFIRM_PASSWORD("Verify Error Confirm Password", AttributeHelper.getElementByAttribute("p", "errormsg-confirmpassword")),
		YOPMAIL_EMAIL("Yopmail Email Text Box", By.xpath("//input[@id='login']")),
		YOPMAIL_LOGIN("Yopmail Login Button", By.xpath("//input[@id='login']")),
		RESET_PASSWORD_LINK("Reset Password Link", By.xpath("//a[normalize-space()='here']"));
		

		
		
		private final String name;
		private final By locator;

		ElementLocators(String name, By locator) {
			this.name = name;
			this.locator = locator;
		}

		public String getName() {
			return name;
		}

		public By getLocator() {
			return locator;
		}
	}

	public enum ActionType {
		ENTER_TEXT("Entering text into"), CLICK("Clicking");

		private final String logPrefix;

		ActionType(String logPrefix) {
			this.logPrefix = logPrefix;
		}

		public String getLogPrefix() {
			return logPrefix;
		}
	}

	public void performAction(ActionType actionType, ElementLocators element, String value) {
		try {
			String logMessage = actionType.getLogPrefix() + " " + element.getName()
					+ (actionType == ActionType.ENTER_TEXT ? ": " + value : "...");
			Log.info(logMessage);

			switch (actionType) {
			case ENTER_TEXT:
				if (value == null) {
					throw new IllegalArgumentException("Value cannot be null for ENTER_TEXT action");
				}
				actionDriver.enterText(element.getLocator(), value);
				break;
			case CLICK:
				actionDriver.click(element.getLocator());
				break;
			default:
				throw new IllegalArgumentException("Unsupported action type: " + actionType);
			}
		} catch (Exception e) {
			Log.error("Failed to perform " + actionType + " on " + element.getName() + ": " + e.getMessage());
			throw e;
		}
	}

	public void performAction(ActionType actionType, ElementLocators element) {
		if (actionType == ActionType.ENTER_TEXT) {
			throw new IllegalArgumentException(
					"ENTER_TEXT action requires a value; use the overloaded method with a value parameter");
		}
		performAction(actionType, element, null);
	}

	public boolean isMessageDisplayed(ElementLocators element) {
		Log.info("Checking if " + element.getName() + " is displayed...");
		return actionDriver.isDisplayed(element.getLocator());
	}

	public String getMessageText(ElementLocators element) {
		Log.info("Retrieving text from " + element.getName() + "...");
		return actionDriver.getText(element.getLocator());
	}

	public Boolean verifyMessage(ElementLocators element, String expectedMessage) {
		Log.info("Verifying message for " + element.getName() + ": expected '" + expectedMessage + "'");
		return actionDriver.compareText(element.getLocator(), expectedMessage);
	}

	public Boolean verifyAttribute(ElementLocators element, String attribute, String expectedValue) {
		try {
			Log.info("Verifying " + attribute + " attribute for " + element.getName() + ": expected '" + expectedValue
					+ "'");
			return actionDriver.compareInputAttribute(element.getLocator(), attribute, expectedValue);
		} catch (Exception e) {
			Log.error("Failed to verify " + attribute + " attribute for " + element.getName() + ": " + e.getMessage());
			throw e;
		}
	}

	public void navigateToResetLink(String resetUrl) {
	    Log.info("Navigating to password reset link: " + resetUrl);
	    driver.get(resetUrl);
	}

	public void resetPassword(String newPassword) {
	    Log.info("Resetting password...");
	    performAction(ActionType.ENTER_TEXT, ElementLocators.PASSWORD, newPassword);
	    performAction(ActionType.ENTER_TEXT, ElementLocators.CONFIRM_PASSWORD, newPassword);
	    performAction(ActionType.CLICK, ElementLocators.SAVE_PASSWORD);
	}
}