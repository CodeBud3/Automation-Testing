package enums;

import org.openqa.selenium.By;

import interfaces.ElementLocator;

public class DashboardPageLocators {
		// Locators
		public enum DashboardLocators implements ElementLocator  {
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
