package pages;

import org.openqa.selenium.WebDriver;
import actiondriver.ActionDriver;
import enums.ActionTypes.ActionType;
import enums.CommonLocators.CommonPageLocators;
import enums.LoginPageLocatorEnum;
import enums.SideNavLocators.SideNavLocator;

	public class LoginPage extends LoginPageLocatorEnum {
		private WebDriver driver;
		private ActionDriver actionDriver;
		

		public LoginPage(WebDriver driver, ActionDriver actionDriver) {
			this.driver = driver;
			this.actionDriver = actionDriver; // Initialize with the provided driver
		}
	
	public void populateLoginFields(String username, String password) {
		actionDriver.performAction(ActionType.ENTER_TEXT, CommonPageLocators.EMAIL_TEXT, username);
		actionDriver.performAction(ActionType.ENTER_TEXT, CommonPageLocators.PASSWORD_TEXT, password);
	}
	public void performLogoutAction() {
		actionDriver.performAction(ActionType.CLICK, SideNavLocator.USER_PROFILE);
		actionDriver.performAction(ActionType.CLICK, SideNavLocator.LOGOUT);
	}
}