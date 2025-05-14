package enums;

import org.openqa.selenium.By;

import interfaces.ElementLocator;
import utils.AttributeHelper;

public class ForgotPasswordLocators {
	public enum ForgotPasswordPageLocators  implements ElementLocator {
		// Buttons and Links (from ButtonType)
		LOGIN_PAGE("Return To Login Page Link", AttributeHelper.getElementByAttribute("a", "login-link")),
	    PASSWORD_RESET("Password Reset Button", AttributeHelper.getElementByAttribute("button", "button-submit")),
	    CONFIRM_RESET("Password Reset Confirm", AttributeHelper.getElementByAttribute("button", "alert-confirm")),
	    PASSWORD_TOGGLE("Toggle Password Button", AttributeHelper.getElementByAttribute("button", "password-visibility")),
	    CONFIRM_PASSWORD_TOGGLE("Toggle Confirm Password Button", AttributeHelper.getElementByAttribute("button", "confirmpassword-visibility"));

		
		
	    private final String name;
		private final By locator;

		ForgotPasswordPageLocators(String name, By locator) {
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

