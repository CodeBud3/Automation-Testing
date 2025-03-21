package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import actiondriver.ActionDriver;
import base.BaseTest;

public class createAccount {
	private ActionDriver actionDriver;
	
	// Locators
	private By firstNameTextBox = By.xpath("//*[@id=\":r0:-form-item\"]");
	private By lastNameTextBox = By.xpath("//*[@id=\":r1:-form-item\"]");
	private By emailIdTextBox = By.xpath("//*[@id=\":r2:-form-item\"]");
	private By passwordTextBox = By.xpath("//*[@id=\":r3:-form-item\"]");
	private By confirmPasswordTextBox = By.xpath("//*[@id=\":r4:-form-item\"]");
	private By checkBoxButton = By.xpath("//*[@id=\":r5:-form-item\"]");
	private By createAccountButton = By.xpath("//*[@id=\"root\"]/div/div[2]/main/div[2]/div/div[2]/form/button");
	private By errorMessage = By.id("error-message");
	public createAccount(WebDriver driver) {
		this.actionDriver = BaseTest.getActionDriver();
	}

	public void create(String firstName, String lastName, String emailId, String password, String confirmPassword) {
		actionDriver.enterText(firstNameTextBox, firstName);
		actionDriver.enterText(lastNameTextBox, lastName);
		actionDriver.enterText(emailIdTextBox, emailId);
		actionDriver.enterText(passwordTextBox, password);
		actionDriver.enterText(confirmPasswordTextBox, confirmPassword);
		actionDriver.click(checkBoxButton);
		actionDriver.click(createAccountButton);
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