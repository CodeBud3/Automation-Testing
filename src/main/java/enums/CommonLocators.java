package enums;

import org.openqa.selenium.By;

import interfaces.ElementLocator;
import utils.AttributeHelper;

public class CommonLocators {
	public enum CommonPageLocators implements ElementLocator {
		// Buttons and Links (from ButtonType)
		LOGIN("Login Button", AttributeHelper.getElementByAttribute("button", "button-submit")),
		SIGN_UP("Sign Up Button", AttributeHelper.getElementByAttribute("a", "signup-link")),
		NAV_SIGN_UP("Nav Sign Up Button", AttributeHelper.getElementByAttribute("button", "nav-signup-link")),
		LOGOUT("Logout Button", AttributeHelper.getElementByAttribute("button", "nav-logout")),
		EMAIL_TEXT("Email Text Box", AttributeHelper.getElementByAttribute("input", "field-email")),
		PASSWORD_TEXT("Password Text Box", AttributeHelper.getElementByAttribute("input", "field-password")),
		CONFIRM_PASSWORD("Confirm Password Text Box", AttributeHelper.getElementByAttribute("input", "field-confirmpassword")),
		PASSWORD_VIS_TOGGLE("Password Visibility Toggle", AttributeHelper.getElementByAttribute("button", "password-visibility")),
		CONFIRM_PASSWORD_VIS_TOGGLE("Toggle Confirm Password Button", AttributeHelper.getElementByAttribute("button", "confirmpassword-visibility")),
		SIGN_IN_PAGE("Sign In Page Message", AttributeHelper.getElementByAttribute("div", "signin-card-title")),
		SIGN_UP_PAGE("Create Account Page", AttributeHelper.getElementByAttribute("div", "signup-card-title"));
		

		private final String name;
		private final By locator;

		CommonPageLocators(String name, By locator) {
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
