package enums;

import org.openqa.selenium.By;

import interfaces.ElementLocator;
import utils.AttributeHelper;

public class LoginPageLocatorEnum {
	public enum LoginLocatorEnum  implements ElementLocator {
	    REMEMBER_ME("Remember Me Button", AttributeHelper.getElementByAttribute("button", "field-rememberme")),
	    FORGOT_PASSWORD("Forgot Password Link", AttributeHelper.getElementByAttribute("a", "forgot-password-link")),
	    EMAIL_MESSAGE("Email Error Message", AttributeHelper.getElementByAttribute("p", "errormsg-email")),
	    PASSWORD_MESSAGE("Password Error Message", AttributeHelper.getElementByAttribute("p", "errormsg-password")),
	    SIGN_IN_FORM_CONTAINER("Sign In Form Error Container", AttributeHelper.getElementByAttribute("div", "sign-in-form-errors")),
	    SIGN_IN_FORM_MESSAGE("Sign In Form Error Message", AttributeHelper.getElementByAttribute("div", "sign-in-form-errors", "/ul/li")),
	    REGISTRATION_PAGE("Registration Page", AttributeHelper.getElementByAttribute("div", "signup-card-title")),
	    FORGOT_PASSWORD_PAGE("Forgot Password Page", AttributeHelper.getElementByAttribute("div", "forgot-password-card-title"));

		private final String name;
		private final By locator;

		LoginLocatorEnum(String name, By locator) {
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
