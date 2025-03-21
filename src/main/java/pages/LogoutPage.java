/*package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import actiondriver.ActionDriver;
import base.BaseTest;

public class LogoutPage {
    private ActionDriver actionDriver;



	// Locators
	private By emailTextBox = By.xpath("//input[@id=':r0:-form-item']");
	private By passwordTextBox = By.xpath("//input[@id=':r1:-form-item']");
	private By rememberMeButton = By.xpath("//button[@id=':r2:-form-item']");
	private By signInButton = By.xpath("//*[@id=\"root\"]/div/div[2]/main/div[2]/div/div[2]/form/button");
	private By logoutButton = By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/button");
	private By errorMessage = By.id("error-message");
	public LogoutPage(WebDriver driver) {
        this.actionDriver = BaseTest.getActionDriver();
	}

	public void login(String email, String password)throws InterruptedException {
		actionDriver.enterText(emailTextBox, email);
		actionDriver.enterText(passwordTextBox, password);
		actionDriver.click(rememberMeButton);
		actionDriver.click(signInButton);
		Thread.sleep(6000);
		actionDriver.click(logoutButton);
		Thread.sleep(6000);
		
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
}*/