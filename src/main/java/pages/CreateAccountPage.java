package pages;

import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
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
	public void clickButton() {
		actionDriver.performAction(ActionType.CLICK, ElementLocators.TNC_CHECK_BOX);
		actionDriver.performAction(ActionType.CLICK, ElementLocators.CREATE_ACCOUNT);
	}
}