package enums;

import org.openqa.selenium.By;

import interfaces.ElementLocator;
import utils.AttributeHelper;

public class CreateAccountLocatorEnum {
		// Locators
		public enum ElementLocators implements ElementLocator  {
			
			FIRST_NAME("First Name Text Box", AttributeHelper.getElementByAttribute("input", "field-firstname")),
			LAST_NAME("Last Name Text Box", AttributeHelper.getElementByAttribute("input", "field-lastname")),
			EMAIL_ID("Email Id Text Box", AttributeHelper.getElementByAttribute("input", "field-email")),
			PASSWORD("Password Text Box", AttributeHelper.getElementByAttribute("input", "field-password")),
			CONFIRM_PASSWORD("Confirm Password Text Box", AttributeHelper.getElementByAttribute("Input", "field-confirmpassword")),
			TNC_CHECK_BOX("Terms And Condition Check Box", AttributeHelper.getElementByAttribute("button", "field-tnc")),		
			CREATE_ACCOUNT("Create Account Button", AttributeHelper.getElementByAttribute("button", "button-submit")),
			SIGN_IN("Sign In Button", AttributeHelper.getElementByAttribute("a", "signin-link")),
			NAV_LOGIN("Navigate Login Button", AttributeHelper.getElementByAttribute("button", "nav-signin-link")),
			PASSWORD_TOGGLE("Toggle Password Button", AttributeHelper.getElementByAttribute("button", "password-visibility")),
			CONFIRM_PASSWORD_TOGGLE("Toggle Confirm Password Button", AttributeHelper.getElementByAttribute("button", "confirmpassword-visibility")),
			SIGN_IN_PAGE("Navigate to Sign In Page", AttributeHelper.getElementByAttribute("div", "signin-card-title")),
			
			//GOOGLE_AUTH("Google Auth Button", AttributeHelper.getElementByAttribute("button", "signin-google-auth")),
			//MICROSOFT_AUTH("Microsoft Auth Button", AttributeHelper.getElementByAttribute("button", "signin-ms-auth")),
			//VER_ERROR_MESSAGE("Verify Error Message Sing Up", AttributeHelper.getElementByAttribute("p", "")),
			ERROR_MESSAGE("Error Message", AttributeHelper.getElementByAttribute("div", "sign-up-form-errors", "/ul/li")),
			VER_ERROR_FIRST_NAME("Verify Error First Name", AttributeHelper.getElementByAttribute("p", "errormsg-firstname")),
			VER_ERROR_LAST_NAME("Verify Error Last Name", AttributeHelper.getElementByAttribute("p", "errormsg-lastname")),
			VER_ERROR_EMAIL_ID("Verify Error Email", AttributeHelper.getElementByAttribute("p", "errormsg-email")),
			VER_ERROR_PASSWORD("Verify Error Password", AttributeHelper.getElementByAttribute("p", "errormsg-password")),
			VER_ERROR_CONFIRM_PASSWORD("Verify Error Confirm Password", AttributeHelper.getElementByAttribute("p", "errormsg-confirmpassword")),
			VER_ERROR_TNC_CHECK_BOX("Verify Error AttributeHelper", AttributeHelper.getElementByAttribute("p", "errormsg-tnc"));			
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
