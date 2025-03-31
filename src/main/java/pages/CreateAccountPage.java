package pages;

import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.Log;
import actiondriver.ActionDriver;
import enums.ActionTypes.ActionType;
import enums.CreateAccountLocatorEnum;


public class CreateAccountPage extends CreateAccountLocatorEnum {
	private WebDriver driver;
	private ActionDriver actionDriver;
	

	public CreateAccountPage(WebDriver driver) {
		this.driver = driver;
		this.actionDriver = new ActionDriver(driver); // Initialize with the provided driver
	}
	
	public void navigateToCreateAccountPage() {
		driver.get(ConfigReader.getProperty("url") + "/signup");
	}
	
	
	public void populateCreateAccountFields(String firstname, String lastname, String email, String password, String confirmpassword) {
		actionDriver.performAction(ActionType.ENTER_TEXT, ElementLocators.FIRST_NAME, firstname);
		actionDriver.performAction(ActionType.ENTER_TEXT, ElementLocators.LAST_NAME, lastname);
		actionDriver.performAction(ActionType.ENTER_TEXT, ElementLocators.EMAIL_ID, email);
		actionDriver.performAction(ActionType.ENTER_TEXT, ElementLocators.PASSWORD, password);
		actionDriver.performAction(ActionType.ENTER_TEXT, ElementLocators.CONFIRM_PASSWORD, confirmpassword);
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