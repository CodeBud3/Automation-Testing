package enums;

import org.openqa.selenium.By;

import interfaces.ElementLocator;
import utils.AttributeHelper;

public class SideNavLocators {
	public enum SideNavLocator  implements ElementLocator {
		// Buttons and Links (from ButtonType)
	    USER_PROFILE("User profile", AttributeHelper.getElementByAttribute("button", "side-nav-user-profile")),
	    USER_HOME_BUTTON("User Home Button", AttributeHelper.getElementByAttribute("a", "sidenav-home")),
	    USER_TASK_BUTTON("User Task Button", AttributeHelper.getElementByAttribute("a", "sidenav-tasks")),
	    SIDE_NAV_BUTTON("Side Bar Button", AttributeHelper.getElementByAttribute("button", "sidenav-trigger")),
	    LOGOUT("Logout Button", AttributeHelper.getElementByAttribute("div", "menu-logout"));

		
		
		private final String name;
		private final By locator;

		SideNavLocator(String name, By locator) {
			this.name = name;
			this.locator = locator;
		}

		public String getName() {
			return name;
		}

		public By getLocator() {
			return locator;
		}
	}
}