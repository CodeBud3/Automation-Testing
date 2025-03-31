package enums;

import org.openqa.selenium.By;

import interfaces.ElementLocator;
import utils.AttributeHelper;

public class ForgotPasswordLocators {
	public enum ElementLocators  implements ElementLocator {
		// Buttons and Links (from ButtonType)
		SIGN_UP("Sign Up Button", AttributeHelper.getElementByAttribute("button", "nav-signup-link")),
		LOGIN_PAGE("Return To Login Page Link", AttributeHelper.getElementByAttribute("a", "login-link")),
		EMAIL_FIELD("Email Field Text Box", AttributeHelper.getElementByAttribute("input", "field-email")),
		SIGN_IN_PAGE("Sign In Page Message", AttributeHelper.getElementByAttribute("div", "signin-card-title")),
		CREATE_ACCOUNT_PAGE("Create Account Page", AttributeHelper.getElementByAttribute("div", "signup-card-title")),
		PASSWORD_RESET("Password Reset Confirm", AttributeHelper.getElementByAttribute("button", "alert-confirm"));

		private final String name;
		private final By locator;

		ElementLocators(String name, By locator) {
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

