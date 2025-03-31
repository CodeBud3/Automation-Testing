package pages;

import org.openqa.selenium.WebDriver;
import utils.Log;
import actiondriver.ActionDriver;
import enums.ActionTypes.ActionType;
import enums.LoginPageLocatorEnum;



public class LoginPage extends LoginPageLocatorEnum {
	private WebDriver driver;
	private ActionDriver actionDriver;
	

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.actionDriver = new ActionDriver(driver); // Initialize with the provided driver
	}
	
	public void populateLoginFields(String username, String password) {
		actionDriver.performAction(ActionType.ENTER_TEXT, ElementLocators.EMAIL_TEXT, username);
		actionDriver.performAction(ActionType.ENTER_TEXT, ElementLocators.PASSWORD_TEXT, password);
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