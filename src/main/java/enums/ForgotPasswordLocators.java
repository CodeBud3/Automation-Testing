package enums;

import org.openqa.selenium.By;

import interfaces.ElementLocator;
import utils.AttributeHelper;

public class ForgotPasswordLocators {
	public enum ElementLocators  implements ElementLocator {
		// Buttons and Links (from ButtonType)
		LOGIN("Login Button", AttributeHelper.getElementByAttribute("button", "button-submit")),
		LOGOUT("Logout Button", AttributeHelper.getElementByAttribute("button", "nav-logout")),
		SIGN_UP("Sign Up Button", AttributeHelper.getElementByAttribute("button", "nav-signup-link")),
		LOGIN_PAGE("Return To Login Page Link", AttributeHelper.getElementByAttribute("a", "login-link")),
		EMAIL_FIELD("Email Field Text Box", AttributeHelper.getElementByAttribute("input", "field-email")),
		SIGN_IN_PAGE("Sign In Page Message", AttributeHelper.getElementByAttribute("div", "signin-card-title")),
		CREATE_ACCOUNT_PAGE("Create Account Page", AttributeHelper.getElementByAttribute("div", "signup-card-title")),
		PASSWORD_RESET("Password Reset Button", AttributeHelper.getElementByAttribute("button", "button-submit")),
		CONFIRM_RESET("Password Reset Confirm", AttributeHelper.getElementByAttribute("button", "alert-confirm")),
		PASSWORD("Password Text Box", AttributeHelper.getElementByAttribute("input", "field-password")),
		CONFIRM_PASSWORD("Confirm Password Text Box", AttributeHelper.getElementByAttribute("Input", "field-confirmpassword")),
		PASSWORD_TOGGLE("Toggle Password Button", AttributeHelper.getElementByAttribute("button", "password-visibility")),
		CONFIRM_PASSWORD_TOGGLE("Toggle Confirm Password Button", AttributeHelper.getElementByAttribute("button", "confirmpassword-visibility")),
		SAVE_PASSWORD("Save Password Button", AttributeHelper.getElementByAttribute("button", "button-submit")),
		VER_ERROR_EMAIL_ID("Verify Error Email ID", AttributeHelper.getElementByAttribute("p", "errormsg-email")),
		VER_ERROR_PASSWORD("Verify Error Password", AttributeHelper.getElementByAttribute("p", "errormsg-password")),
		VER_ERROR_CONFIRM_PASSWORD("Verify Error Confirm Password", AttributeHelper.getElementByAttribute("p", "errormsg-confirmpassword"));

		
		
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

