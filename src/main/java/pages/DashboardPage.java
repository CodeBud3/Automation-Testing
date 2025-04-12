package pages;

import org.openqa.selenium.WebDriver;

import actiondriver.ActionDriver;
import enums.DashboardPageLocators;
import enums.ActionTypes.ActionType;
import enums.DashboardPageLocators.DashboardLocators;
import enums.SideNavLocators.SideNavLocator;

public class DashboardPage extends DashboardPageLocators {
		private WebDriver driver;
		private ActionDriver actionDriver;
		

		public DashboardPage(WebDriver driver, ActionDriver actionDriver) {
			this.driver = driver;
			this.actionDriver = actionDriver;
		}
		
		public void populateLoginFields(String username, String password) {
			actionDriver.performAction(ActionType.ENTER_TEXT, DashboardLocators.EMAIL_TEXT, username);
			actionDriver.performAction(ActionType.ENTER_TEXT, DashboardLocators.PASSWORD_TEXT, password);
			actionDriver.performAction(ActionType.CLICK, DashboardLocators.LOGIN);
		}
		public void performLogoutAction() {
			actionDriver.performAction(ActionType.CLICK, SideNavLocator.USER_PROFILE);
			actionDriver.performAction(ActionType.CLICK, SideNavLocator.LOGOUT);
		}
}