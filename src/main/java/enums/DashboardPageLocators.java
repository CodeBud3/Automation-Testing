package enums;

import org.openqa.selenium.By;

import interfaces.ElementLocator;
import utils.AttributeHelper;

public class DashboardPageLocators {
		// Locators
		public enum DashboardLocators implements ElementLocator  {
			LOGIN("Login Button", AttributeHelper.getElementByAttribute("button", "button-submit")),
			EMAIL_TEXT("Email Text Box", AttributeHelper.getElementByAttribute("input", "field-email")),
			PASSWORD_TEXT("Password Text Box", AttributeHelper.getElementByAttribute("input", "field-password")),
			TASK_TAB_PAGE("Tasks Tab PAGE", By.xpath("//h2[normalize-space()='My Tasks']")),
			ADD_TASK("Add Task Button", By.xpath("//button[normalize-space()='Add Task']")),
			TASK_TITLE("Task Enter Title", AttributeHelper.getElementByAttribute("input", "field-title")),
			SIGN_IN_PAGE("Navigate to Sign In Page", AttributeHelper.getElementByAttribute("div", "signin-card-title")),
			PROFILE_EMAIL("Profile Email indication", By.xpath("//span[@class='truncate text-xs']")),
			DASHBOARD_WELCOME_MESSAGE("Welcome message", By.xpath("//*[@id=\"root\"]/div/div/div/main/main/h1[1]"));
			
			
			
			private final String name;
			private final By locator;

			DashboardLocators(String name, By locator) {
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
