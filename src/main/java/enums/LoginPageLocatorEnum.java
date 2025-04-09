package enums;

import org.openqa.selenium.By;

import interfaces.ElementLocator;
import utils.AttributeHelper;

public class LoginPageLocatorEnum {
	public enum ElementLocators  implements ElementLocator {
		// Buttons and Links (from ButtonType)
		LOGIN("Login Button", AttributeHelper.getElementByAttribute("button", "button-submit")),
		LOGOUT("Logout Button", AttributeHelper.getElementByAttribute("button", "nav-logout")),
		SIGN_UP("Sign Up Button", AttributeHelper.getElementByAttribute("a", "signup-link")),
		NAV_SIGN_UP("Nav Sign Up Button", AttributeHelper.getElementByAttribute("button", "nav-signup-link")),
		PASS_VIS_TOGGLE("Password Visibility Toggle",
				AttributeHelper.getElementByAttribute("button", "password-visibility")),
		REMEMBER_ME("Remember Me Button", AttributeHelper.getElementByAttribute("button", "field-rememberme")),
		GOOGLE_AUTH("Google Auth Button", AttributeHelper.getElementByAttribute("button", "signin-google-auth")),
		MICROSOFT_AUTH("Microsoft Auth Button", AttributeHelper.getElementByAttribute("button", "signin-ms-auth")),
		FORGOT_PASSWORD("Forgot Password Link",
				AttributeHelper.getElementByAttribute("a", "forgot-password-link")),
		SIGN_IN_PAGE("Sign In Page Message", AttributeHelper.getElementByAttribute("div", "signin-card-title")),
		SAVE_PASSWORD("Save Password Button", AttributeHelper.getElementByAttribute("button", "button-submit")),
		CREATE_ACCOUNT("Create Account Button", AttributeHelper.getElementByAttribute("button", "button-submit")),

		// Text Fields (from EnterText)
		EMAIL_TEXT("Email Text Box", AttributeHelper.getElementByAttribute("input", "field-email")),
		PASSWORD_TEXT("Password Text Box", AttributeHelper.getElementByAttribute("input", "field-password")),

		// Other Elements (for completeness)
		EMAIL_MESSAGE("Email Error Message", AttributeHelper.getElementByAttribute("p", "errormsg-email")),
		PASSWORD_MESSAGE("Password Error Message", AttributeHelper.getElementByAttribute("p", "errormsg-password")),
		SIGN_IN_FORM_CONTAINER("Sign In Form Error Container",
				AttributeHelper.getElementByAttribute("div", "sign-in-form-errors")),
		SIGN_IN_FORM_MESSAGE("Sign In Form Error Message",
				AttributeHelper.getElementByAttribute("div", "sign-in-form-errors", "/ul/li")),
		REGISTRATION_PAGE("Registration Page", AttributeHelper.getElementByAttribute("div", "signup-card-title")),
		PASSWORD_VISIBILITY_TOGGLE("Password Visibility Toggle Text",
				AttributeHelper.getElementByAttribute("input", "field-password")),
		FORGOT_PASSWORD_PAGE("Forgot Password Page", AttributeHelper.getElementByAttribute("div", "forgot-password-card-title")),
		GOOGLE_EMAIL("Google Email Text Box", By.xpath("//*[@id=\"identifierId\"]")),
		GOOGLE_SIGN_IN("Google Sign In page", By.xpath("//*[@id=\"headingText\"]/span")),
		MICROSOFT_EMAIL("Microsoft Email Text Box", By.xpath("//input[@id='i0116']")),
		MICROSOFT_SIGN_IN("Microsoft Sign In page", By.xpath("//*[@id=\"loginHeader\"]/div"));

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
