package pages;

import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import actiondriver.ActionDriver;
import enums.ActionTypes.ActionType;
import enums.CommonLocators;


public class LogoutPage extends CommonLocators {
	private WebDriver driver;
	private ActionDriver actionDriver;
	

	public LogoutPage(WebDriver driver, ActionDriver actionDriver) {
		this.driver = driver;
		this.actionDriver = actionDriver; // Initialize with the provided driver
	}
	
	public void navigateToLogoutPage() {
		driver.get(ConfigReader.getProperty("url"));
	}
	
	
	public void populateLoginFields(String email, String password) {
		actionDriver.performAction(ActionType.ENTER_TEXT, CommonPageLocators.EMAIL_TEXT, email);
		actionDriver.performAction(ActionType.ENTER_TEXT, CommonPageLocators.PASSWORD_TEXT, password);
	}
}