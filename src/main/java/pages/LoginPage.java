package pages;

import java.lang.reflect.Field;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.AttributeHelper;
import actiondriver.ActionDriver;
import base.BaseTest;

public class LoginPage {
	private ActionDriver actionDriver;
	
	// Locators
	private By emailTextBox = AttributeHelper.getElementByAttribute("input", "field-email");
	private By passwordTextBox = AttributeHelper.getElementByAttribute("input", "field-password");
	private By loginButton = AttributeHelper.getElementByAttribute("button", "button-submit");
	private By emailErrorMessage =  AttributeHelper.getElementByAttribute("p","errormsg-email");
	private By passwordErrorMessage = AttributeHelper.getElementByAttribute("p", "errormsg-password");
	private By signInFormErrorContainer = AttributeHelper.getElementByAttribute("div", "sign-in-form-errors");
	private By signInFormErrorMessage = AttributeHelper.getElementByAttribute("div","sign-in-form-errors", "/ul/li");
	private By logoutButton = AttributeHelper.getElementByAttribute("button", "nav-logout");
	private By signUpButton = AttributeHelper.getElementByAttribute("a", "signup-link");
	private By navSignUpButton = AttributeHelper.getElementByAttribute("button","nav-signup-link");
	private By registrationPage = AttributeHelper.getElementByAttribute("div","signup-card-title");
	private By passVisToggle = AttributeHelper.getElementByAttribute("button", "password-visibility");
	private By passVisToggleText = AttributeHelper.getElementByAttribute("input", "field-password");
	private By rememberMeButton = AttributeHelper.getElementByAttribute("button", "field-rememberme");
	private By googleAuthButton = AttributeHelper.getElementByAttribute("button", "signin-google-auth");
	private By googleEmailTextBox = By.xpath("//input[@id='identifierId']");
	private By googleNextButton = By.xpath("//button[@class='VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc LQeN7 BqKGqe Jskylb TrZEUc lw1w4b']");
	private By googlePasswordTextBox = By.xpath("//input[@name='Passwd']");
	
	
	
	
	public LoginPage(WebDriver driver) {
		this.actionDriver = BaseTest.getActionDriver();
	}
	
	public By getErrorMessage(String fieldName) {
        try {
            // Convert field name to match the class member naming convention
            String memberName = fieldName;

            // Use reflection to access the private field
            Field field = this.getClass().getDeclaredField(memberName);
            field.setAccessible(true); // Allow access to private fields
            return (By) field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
        	throw new RuntimeException("Locator not found for: " + fieldName, e);
        }
    }
	
	public By getMessageLocator(String fieldName) {
        try {
            // Use reflection to access the private field
            Field field = this.getClass().getDeclaredField(fieldName);
            field.setAccessible(true); // Allow access to private fields
            return (By) field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Locator not found for: " + fieldName, e);
        }
    }
	
	public void populateLoginFields(String username, String password) {
		actionDriver.enterText(emailTextBox, username);
		actionDriver.enterText(passwordTextBox, password);
	}
	
	public void googleLoginEmailFields(String googleemail) {
	actionDriver.enterText(googleEmailTextBox, googleemail);
	}
	public void googleLoginPassFields(String googlepassword) {
		actionDriver.enterText(googlePasswordTextBox, googlepassword);
	}
	
	public void clickLoginButton() {
		actionDriver.click(loginButton);
	}
	public void clickLogoutButton() {
		actionDriver.click(logoutButton);
	}
	public void clickSignUpButton() {
		actionDriver.click(signUpButton);
	}
	public void clickNavSignUpButton() {
		actionDriver.click(navSignUpButton);
	}
	public void clickpassVisToggle() {
		actionDriver.click(passVisToggle);
	}
	public void clickrememberMeButton() {
		actionDriver.click(rememberMeButton);
	}
	public void clickgoogleAuthButton() {
		actionDriver.click(googleAuthButton);
	}
	public void clickgoogleSignInbutton() {
		actionDriver.click(googleNextButton);
	}
	

	public boolean isErrorMessagedDisplayed(String fieldName) {
		return actionDriver.isDisplayed(getErrorMessage(fieldName));
	}

	public String getErrorMessageText(String fieldName) {
		return actionDriver.getText(getErrorMessage(fieldName));
	}
	
	public Boolean verifyErrorMessage(String fieldName, String expectedErrorMessage) {
		return actionDriver.compareText(getErrorMessage(fieldName), expectedErrorMessage);
	}
	
	public boolean isMessageDisplayed(String fieldName) {
	        return actionDriver.isDisplayed(getMessageLocator(fieldName));
	}

	    // Method to get the text of a message
	public String getMessageText(String fieldName) {
	        return actionDriver.getText(getMessageLocator(fieldName));
	}

	    // Method to verify a message against an expected message
	public Boolean verifyMessage(String fieldName, String expectedMessage) {
	        return actionDriver.compareText(getMessageLocator(fieldName), expectedMessage);
	}
	
	public Boolean verifyToggleMessage(String expectedPassword) {
			return actionDriver.compareInputAttribute(passVisToggleText, "type", expectedPassword);
	}

}
