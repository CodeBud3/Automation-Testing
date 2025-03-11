package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import actiondriver.ActionDriver;
import base.BaseTest;

public class LoginPage {
	private ActionDriver actionDriver;
	
	// Locators
	private By usernameTextBox = By.id("Email");
	private By passwordTextBox = By.id("Password");
	private By loginButton = By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/div[1]/div/form/div[3]/button");
	private By errorMessage = By.id("error-message");
	public LoginPage(WebDriver driver) {
		this.actionDriver = BaseTest.getActionDriver();
	}

	public void login(String username, String password) {
		actionDriver.enterText(usernameTextBox, username);
		actionDriver.enterText(passwordTextBox, password);
		actionDriver.click(loginButton);
	}

	public boolean isErrorMessagedDisplayed() {
		return actionDriver.isDisplayed(errorMessage);
	}

	public String getErrorMessageText() {
		return actionDriver.getText(errorMessage);
	}
	
	public void verifyErrorMessage(String expectedErrorMessage) {
		actionDriver.compareText(errorMessage, expectedErrorMessage);
	}
}
