package pages;

import org.openqa.selenium.WebDriver;
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
}