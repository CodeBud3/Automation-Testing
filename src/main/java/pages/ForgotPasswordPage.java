package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utils.ConfigReader;
import utils.Log;
import actiondriver.ActionDriver;
import enums.ActionTypes.ActionType;
import enums.CreateAccountLocatorEnum.ElementLocators;
import interfaces.ElementLocator;

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

	public void populateForgotPageEmailFields(String email) {
		actionDriver.performAction(ActionType.ENTER_TEXT, ElementLocators.EMAIL_ID, email);
	}

	public void populatePasswordRestFields(String password, String confirmpassword) {
		actionDriver.performAction(ActionType.ENTER_TEXT, ElementLocators.PASSWORD, password);
		actionDriver.performAction(ActionType.ENTER_TEXT, ElementLocators.CONFIRM_PASSWORD, confirmpassword);
	}

	// Locators

	public boolean isMessageDisplayed(ElementLocator element) {
		Log.info("Checking if " + element.getName() + " is displayed...");
		return actionDriver.isDisplayed(element.getLocator());
	}

	public String getMessageText(ElementLocator element) {
		Log.info("Retrieving text from " + element.getName() + "...");
		return actionDriver.getText(element.getLocator());
	}

	public Boolean verifyMessage(ElementLocator element, String expectedMessage) {
		Log.info("Verifying message for " + element.getName() + ": expected '" + expectedMessage + "'");
		return actionDriver.compareText(element.getLocator(), expectedMessage);
	}

	public Boolean verifyAttribute(ElementLocator element, String attribute, String expectedValue) {
		try {
			Log.info("Verifying " + attribute + " attribute for " + element.getName() + ": expected '" + expectedValue
					+ "'");
			return actionDriver.compareInputAttribute(element.getLocator(), attribute, expectedValue);
		} catch (Exception e) {
			Log.error("Failed to verify " + attribute + " attribute for " + element.getName() + ": " + e.getMessage());
			throw e;
		}
	}
	public void resetPasswordLink() {
		// Fetch the base URL and token2 from ConfigReader
		String baseUrl = "https://to-do-ggau.onrender.com"; // Ensure this is defined in config.properties
		String token = ConfigReader.getProperty("ResetPasswordUserToken"); // Ensure this is defined in secret.properties

		// Construct the full URL with the token
		String resetPasswordUrl = baseUrl + "/reset-password?token=" + token;

		// Navigate to the constructed URL (assuming driver is available in the test
		// context)
		driver.get(resetPasswordUrl); // Add this if you need to navigate to the URL first
	}
}