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
	private By navigateToSignInButton = By.xpath("//*[@id=\"root\"]/div/div[2]/main/div[2]/div/div[3]/p/b/a");
	private By navigateToLoginButton = By.xpath("//*[@id=\"root\"]/div/div[2]/main/div[1]/button/a");
	private By togglePasswordButton = By.xpath("//div[4]//div[1]//button[1]");
	private By toggleConfirmPasswordButton = By.xpath("//div[5]//div[1]//button[1]");
	private By verifyErrorMessageSingUp = By.xpath("//p[contains(@id, '-form-item-message')]");
	private By verifyErrorEmail = By.xpath("//li[normalize-space()='Email already registered']");
	//private By verifyToggle = By.xpath("//*[@id=\":r3:-form-item\"]");

	
	private By errorMessage = By.xpath("//p[@id=':r1v:-form-item-message']");
	public createAccount(WebDriver driver) {
		this.actionDriver = BaseTest.getActionDriver();
	}

	public void populate(String firstName, String lastName, String emailId, String password, String confirmPassword, Boolean tnc) {
		actionDriver.enterText(firstNameTextBox, firstName);
		actionDriver.enterText(lastNameTextBox, lastName);
		actionDriver.enterText(emailIdTextBox, emailId);
		actionDriver.enterText(passwordTextBox, password);
		actionDriver.enterText(confirmPasswordTextBox, confirmPassword);
		if (tnc) {
		actionDriver.click(checkBoxButton);
		}
	}

	public void clickCreate()
	{
		actionDriver.click(createAccountButton);
	}
	public boolean isErrorMessagedDisplayed() {
		return actionDriver.isDisplayed(errorMessage);
	}

	public String getErrorMessageText() {
		return actionDriver.getText(errorMessage);
	}

	public Boolean verifyErrorMessage(String expectedErrorMessage) {
		return actionDriver.compareText(errorMessage, expectedErrorMessage);
	}
	public void navigateToSignInByLink() {
		actionDriver.click(navigateToSignInButton);
	}
	public void navigateToLoginByButton() {
	actionDriver.click(navigateToLoginButton);
	}
	public void togglePassword() {
	actionDriver.click(togglePasswordButton);
	}
	public void toggleConfirmPassword() {
	actionDriver.click(toggleConfirmPasswordButton);
	}
	//
	public Boolean verifyErrorMessageSingUpPage(String expectedErrorMessage) {
		return actionDriver.compareText(verifyErrorMessageSingUp, expectedErrorMessage);
	}
	public Boolean verifyErrorEmailRegistered(String expectedErrorMessage) {
		return actionDriver.compareText(verifyErrorEmail, expectedErrorMessage);
	}
	public Boolean verifyToggleMessage(String expectedPassword) {
		return actionDriver.compareInputAttribute(passwordTextBox, "type", expectedPassword);
	}
}