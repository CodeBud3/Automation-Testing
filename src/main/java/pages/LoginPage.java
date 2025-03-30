package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.AttributeHelper;
import utils.Log;
import actiondriver.ActionDriver;



public class LoginPage {
	private WebDriver driver;
	private ActionDriver actionDriver;
	

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.actionDriver = new ActionDriver(driver); // Initialize with the provided driver
	}
	// Locators

	public enum ElementLocators {
		// Buttons and Links (from ButtonType)
		LOGIN("Login Button", AttributeHelper.getElementByAttribute("button", "button-submit")),
		LOGOUT("Logout Button", AttributeHelper.getElementByAttribute("button", "nav-logout")),
		SIGN_UP("Sign Up Button", AttributeHelper.getElementByAttribute("a", "signup-link")),
		NAV_SIGN_UP("Nav Sign Up Button", AttributeHelper.getElementByAttribute("button", "nav-signup-link")),
		PASS_VIS_TOGGLE("Password Visibility Toggle",
				AttributeHelper.getElementByAttribute("button", "password-visibility")),
		REMEMBER_ME("Remember Me Button", AttributeHelper.getElementByAttribute("button", "field-rememberme")),
		GOOGLE_AUTH("Google Auth Button", AttributeHelper.getElementByAttribute("button", "signin-google-auth")),
		MICROSOFT_AUTH("Microsoft Auth Button", AttributeHelper.getElementByAttribute("button", "signin-ms-auth")),
		FORGOT_PASSWORD("Forgot Password Link",
				AttributeHelper.getElementByAttribute("a", "forgot-password-link")),

		// Text Fields (from EnterText)
		EMAIL_TEXT("Email Text Box", AttributeHelper.getElementByAttribute("input", "field-email")),
		PASSWORD_TEXT("Password Text Box", AttributeHelper.getElementByAttribute("input", "field-password")),

		// Other Elements (for completeness)
		EMAIL_MESSAGE("Email Error Message", AttributeHelper.getElementByAttribute("p", "errormsg-email")),
		PASSWORD_MESSAGE("Password Error Message", AttributeHelper.getElementByAttribute("p", "errormsg-password")),
		SIGN_IN_FORM_CONTAINER("Sign In Form Error Container",
				AttributeHelper.getElementByAttribute("div", "sign-in-form-errors")),
		SIGN_IN_FORM_MESSAGE("Sign In Form Error Message",
				AttributeHelper.getElementByAttribute("div", "sign-in-form-errors", "/ul/li")),
		REGISTRATION_PAGE("Registration Page", AttributeHelper.getElementByAttribute("div", "signup-card-title")),
		PASSWORD_VISIBILITY_TOGGLE("Password Visibility Toggle Text",
				AttributeHelper.getElementByAttribute("input", "field-password")),
		FORGOT_PASSWORD_PAGE("Forgot Password Page", AttributeHelper.getElementByAttribute("div", "forgot-password-card-title"));

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
	
	public void populateLoginFields(String username, String password) {
		performAction(ActionType.ENTER_TEXT, ElementLocators.EMAIL_TEXT, username);
		performAction(ActionType.ENTER_TEXT, ElementLocators.PASSWORD_TEXT, password);
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