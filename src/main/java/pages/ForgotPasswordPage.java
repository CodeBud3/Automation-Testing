package pages;

import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.Log;
import actiondriver.ActionDriver;
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
}