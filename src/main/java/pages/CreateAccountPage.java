package pages;

import java.lang.reflect.Field;
import java.time.Duration;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AttributeHelper;
import utils.Log;
import actiondriver.ActionDriver;
import base.BaseTest;


public class CreateAccountPage {
	private WebDriver driver;
	private ActionDriver actionDriver;
	

	public CreateAccountPage(WebDriver driver) {
		this.driver = driver;
		this.actionDriver = new ActionDriver(driver); // Initialize with the provided driver
	}
	// Locators

	public enum ElementLocators {
		
		FIRST_NAME("First Name Text Box", AttributeHelper.getElementByAttribute("Input", "field-firstname")),
		LAST_NAME("last Name Text Box", AttributeHelper.getElementByAttribute("Input", "field-lastname")),
		EMAIL_ID("Email Id Text Box", AttributeHelper.getElementByAttribute("Input", "field-email")),
		PASSWORD("Password Text Box", AttributeHelper.getElementByAttribute("Input", "field-password")),
		CONFIRM_PASSWORD("Confirm Password Text Box", AttributeHelper.getElementByAttribute("Input", "field-confirmpassword")),
		CREATE_ACCOUNT("Create Account Button", AttributeHelper.getElementByAttribute("Input", "button-submit")),
		SIGN_IN("Sign In Button", AttributeHelper.getElementByAttribute("Input", "signin-link")),
		NAV_LOGIN("Navigate Login Button", AttributeHelper.getElementByAttribute("Input", "nav-signin-link")),
		PASSWORD_TOGGLE("Toggle Password Button", AttributeHelper.getElementByAttribute("Input", "password-visibility")),
		CONFIRM_PASSWORD_TOGGLE("Toggle Confirm Password Button", AttributeHelper.getElementByAttribute("Input", "field-confirmpassword")),
		//VER_ERROR_MESSAGE("Verify Error Message Sing Up", AttributeHelper.getElementByAttribute("Input", "")),
		VER_ERROR_EMAIL("Verify Error Email", AttributeHelper.getElementByAttribute("Input", "errormsg-firstname"));
		//ERROR_MESSAGE("Error Message", AttributeHelper.getElementByAttribute("Input", "")),
		
		
	

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
	
	public void populateLoginFields(String firstname, String lastname, String email, String password, String confirmpassword, String createaccount) {
		performAction(ActionType.ENTER_TEXT, ElementLocators.FIRST_NAME, firstname);
		performAction(ActionType.ENTER_TEXT, ElementLocators.LAST_NAME, lastname);
		performAction(ActionType.ENTER_TEXT, ElementLocators.EMAIL_ID, email);
		performAction(ActionType.ENTER_TEXT, ElementLocators.PASSWORD, password);
		performAction(ActionType.ENTER_TEXT, ElementLocators.CONFIRM_PASSWORD, confirmpassword);
		performAction(ActionType.ENTER_TEXT, ElementLocators.CREATE_ACCOUNT, createaccount);
		
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