package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.AttributeHelper;
import utils.ConfigReader;
import utils.Log;
import actiondriver.ActionDriver;



public class ForgotPasswordPage {
	private WebDriver driver;
	private ActionDriver actionDriver;
	

	public ForgotPasswordPage(WebDriver driver) {
		this.driver = driver;
		this.actionDriver = new ActionDriver(driver); // Initialize with the provided driver
	}
	
	public void navigateToForgotPasswordPage() {
		driver.get(ConfigReader.getProperty("url") + "/forgot-password");
	}
	// Locators

	public enum ElementLocators {
		
		
		
		
		SIGN_UP("Sign Up Button", AttributeHelper.getElementByAttribute("button", "nav-signup-link")),
		LOGIN_PAGE("Return To Login Page Link", AttributeHelper.getElementByAttribute("a", "login-link")),
		EMAIL_FIELD("Email Field Text Box", AttributeHelper.getElementByAttribute("input", "field-email")),
		SIGN_IN_PAGE("Sign In Page Message", AttributeHelper.getElementByAttribute("div", "signin-card-title")),
		CREATE_ACCOUNT_PAGE("Create Account Page", AttributeHelper.getElementByAttribute("div", "signup-card-title")),		
		PASSWORD_RESET("Password Reset Confirm", AttributeHelper.getElementByAttribute("button", "alert-confirm"));
		
		

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
	        Log.info("Verifying " + attribute + " attribute for " + element.getName() + ": expected '" + expectedValue + "'");
	        return actionDriver.compareInputAttribute(element.getLocator(), attribute, expectedValue);
	    } catch (Exception e) {
	        Log.error("Failed to verify " + attribute + " attribute for " + element.getName() + ": " + e.getMessage());
	        throw e;
	    }
	}	
}