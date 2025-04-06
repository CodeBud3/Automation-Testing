package enums;

import org.openqa.selenium.By;

import interfaces.ElementLocator;
import utils.AttributeHelper;

public class SideNavLocators {
	public enum SideNavLocator  implements ElementLocator {
		// Buttons and Links (from ButtonType)
		USER_PROFILE("User profile", AttributeHelper.getElementByAttribute("button", "side-nav-user-profile")),
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

