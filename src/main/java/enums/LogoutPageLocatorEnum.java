package enums;

import org.openqa.selenium.By;

import interfaces.ElementLocator;
import utils.AttributeHelper;

public class LogoutPageLocatorEnum {
	public enum ElementLocators  implements ElementLocator {
		// Buttons and Links (from ButtonType)
		LOGIN("Login Button", AttributeHelper.getElementByAttribute("button", "button-submit")),
		LOGOUT("Logout Button", AttributeHelper.getElementByAttribute("button", "nav-logout")),
		SIGN_UP("Sign Up Button", AttributeHelper.getElementByAttribute("a", "signup-link")),
		NAV_SIGN_UP("Nav Sign Up Button", AttributeHelper.getElementByAttribute("button", "nav-signup-link")),
		SIGN_IN_PAGE("Sign In Page Message", AttributeHelper.getElementByAttribute("div", "signin-card-title")),

		// Text Fields (from EnterText)
		EMAIL_TEXT("Email Text Box", AttributeHelper.getElementByAttribute("input", "field-email")),
		PASSWORD_TEXT("Password Text Box", AttributeHelper.getElementByAttribute("input", "field-password")),
		DASHBOARD_WELCOME_MESSAGE("Welcome message", By.xpath("//*[@id=\"root\"]/div/div/div/main/main/h1[1]"));


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
