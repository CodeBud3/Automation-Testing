/*package pages;

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
	private By signInFormErrorContainer = AttributeHelper.getElementByAttribute("div","sign-in-form-errors");
	private By signInFormErrorMessage = AttributeHelper.getElementByAttribute("div","sign-in-form-errors", "/ul/li");

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
	
	public void populateLoginFields(String username, String password) {
		actionDriver.enterText(emailTextBox, username);
		actionDriver.enterText(passwordTextBox, password);
	}
	
	public void clickLoginButton() {
		actionDriver.click(loginButton);
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
}*/