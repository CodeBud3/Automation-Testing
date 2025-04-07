package pages;

import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import actiondriver.ActionDriver;
import enums.ActionTypes.ActionType;
import enums.ForgotPasswordLocators.ElementLocators;

public class ForgotPasswordPage {
	private WebDriver driver;
	private ActionDriver actionDriver;

	public ForgotPasswordPage(WebDriver driver, ActionDriver actionDriver) {
		this.driver = driver;
		this.actionDriver = actionDriver; // Initialize with the provided driver
	}

	public void navigateToForgotPasswordPage() {
		driver.get(ConfigReader.getProperty("url") + "/forgot-password");
	}

	public void populateForgotPageEmailFields(String email) {
		actionDriver.performAction(ActionType.ENTER_TEXT, ElementLocators.EMAIL_FIELD, email);
	}

	public void populatePasswordRestFields(String password, String confirmpassword) {
		actionDriver.performAction(ActionType.ENTER_TEXT, ElementLocators.PASSWORD, password);
		actionDriver.performAction(ActionType.ENTER_TEXT, ElementLocators.CONFIRM_PASSWORD, confirmpassword);
	}
	public void populateLoginFields(String username, String password) {
		actionDriver.performAction(ActionType.ENTER_TEXT, ElementLocators.EMAIL_FIELD, username);
		actionDriver.performAction(ActionType.ENTER_TEXT, ElementLocators.PASSWORD, password);
	}

	// Locators
	public void resetPasswordLink() {
		// Fetch the base URL and token2 from ConfigReader
		String baseUrl = ConfigReader.getProperty("url"); // Ensure this is defined in config.properties
		String token = ConfigReader.getProperty("ResetPasswordUserToken"); // Ensure this is defined in secret.properties

		// Construct the full URL with the token
		String resetPasswordUrl = baseUrl + "/reset-password?token=" + token;

		// Navigate to the constructed URL (assuming driver is available in the test
		// context)
		driver.get(resetPasswordUrl); // Add this if you need to navigate to the URL first
	}
}