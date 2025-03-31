package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.AttributeHelper;
import utils.ConfigReader;
import utils.Log;
import actiondriver.ActionDriver;


public class CreateAccountPage {
	private WebDriver driver;
	private ActionDriver actionDriver;
	

	public CreateAccountPage(WebDriver driver) {
		this.driver = driver;
		this.actionDriver = new ActionDriver(driver); // Initialize with the provided driver
	}
	
	public void navigateToCreateAccountPage() {
		driver.get(ConfigReader.getProperty("url") + "/signup");
	}
	// Locators
	public enum ElementLocators {
		
		FIRST_NAME("First Name Text Box", AttributeHelper.getElementByAttribute("input", "field-firstname")),
		LAST_NAME("Last Name Text Box", AttributeHelper.getElementByAttribute("input", "field-lastname")),
		EMAIL_ID("Email Id Text Box", AttributeHelper.getElementByAttribute("input", "field-email")),
		PASSWORD("Password Text Box", AttributeHelper.getElementByAttribute("input", "field-password")),
		CONFIRM_PASSWORD("Confirm Password Text Box", AttributeHelper.getElementByAttribute("Input", "field-confirmpassword")),
		TNC_CHECK_BOX("Terms And Condition Check Box", AttributeHelper.getElementByAttribute("button", "field-tnc")),		
		CREATE_ACCOUNT("Create Account Button", AttributeHelper.getElementByAttribute("button", "button-submit")),
		SIGN_IN("Sign In Button", AttributeHelper.getElementByAttribute("a", "signin-link")),
		NAV_LOGIN("Navigate Login Button", AttributeHelper.getElementByAttribute("button", "nav-signin-link")),
		PASSWORD_TOGGLE("Toggle Password Button", AttributeHelper.getElementByAttribute("button", "password-visibility")),
		CONFIRM_PASSWORD_TOGGLE("Toggle Confirm Password Button", AttributeHelper.getElementByAttribute("button", "confirmpassword-visibility")),
		LOGOUT("Logout Button", AttributeHelper.getElementByAttribute("button", "nav-logout")),
		SIGN_IN_PAGE("Navigate to Sign In Page", AttributeHelper.getElementByAttribute("div", "signin-card-title")),
		
		//GOOGLE_AUTH("Google Auth Button", AttributeHelper.getElementByAttribute("button", "signin-google-auth")),
		//MICROSOFT_AUTH("Microsoft Auth Button", AttributeHelper.getElementByAttribute("button", "signin-ms-auth")),
		//VER_ERROR_MESSAGE("Verify Error Message Sing Up", AttributeHelper.getElementByAttribute("p", "")),
		ERROR_MESSAGE("Error Message", AttributeHelper.getElementByAttribute("div", "sign-up-form-errors", "/ul/li")),
		VER_ERROR_FIRST_NAME("Verify Error First Name", AttributeHelper.getElementByAttribute("p", "errormsg-firstname")),
		VER_ERROR_LAST_NAME("Verify Error Last Name", AttributeHelper.getElementByAttribute("p", "errormsg-lastname")),
		VER_ERROR_EMAIL_ID("Verify Error Email", AttributeHelper.getElementByAttribute("p", "errormsg-email")),
		VER_ERROR_PASSWORD("Verify Error Password", AttributeHelper.getElementByAttribute("p", "errormsg-password")),
		VER_ERROR_CONFIRM_PASSWORD("Verify Error Confirm Password", AttributeHelper.getElementByAttribute("p", "errormsg-confirmpassword")),
		VER_ERROR_TNC_CHECK_BOX("Verify Error AttributeHelper", AttributeHelper.getElementByAttribute("p", "errormsg-tnc"));
		
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
	
	public void populateCreateAccountFields(String firstname, String lastname, String email, String password, String confirmpassword) {
		performAction(ActionType.ENTER_TEXT, ElementLocators.FIRST_NAME, firstname);
		performAction(ActionType.ENTER_TEXT, ElementLocators.LAST_NAME, lastname);
		performAction(ActionType.ENTER_TEXT, ElementLocators.EMAIL_ID, email);
		performAction(ActionType.ENTER_TEXT, ElementLocators.PASSWORD, password);
		performAction(ActionType.ENTER_TEXT, ElementLocators.CONFIRM_PASSWORD, confirmpassword);
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