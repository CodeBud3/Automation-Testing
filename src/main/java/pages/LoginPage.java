package pages;

import java.lang.reflect.Field;
import java.time.Duration;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import utils.AttributeHelper;
import utils.Log;
import actiondriver.ActionDriver;
import base.BaseTest;

public class LoginPage {
	private WebDriver driver;
	private ActionDriver actionDriver;

	// Locators
	private By logoutButton = AttributeHelper.getElementByAttribute("button", "nav-logout");
	private By emailTextBox = AttributeHelper.getElementByAttribute("input", "field-email");
	private By passwordTextBox = AttributeHelper.getElementByAttribute("input", "field-password");
	private By emailErrorMessage = AttributeHelper.getElementByAttribute("p", "errormsg-email");
	private By passwordErrorMessage = AttributeHelper.getElementByAttribute("p", "errormsg-password");
	private By signInFormErrorContainer = AttributeHelper.getElementByAttribute("div", "sign-in-form-errors");
	private By signInFormErrorMessage = AttributeHelper.getElementByAttribute("div", "sign-in-form-errors", "/ul/li");
	private By registrationPage = AttributeHelper.getElementByAttribute("div", "signup-card-title");
	private By passVisToggleText = AttributeHelper.getElementByAttribute("input", "field-password");
	private By forgotPasswordPage = AttributeHelper.getElementByAttribute("div", "signin-card-title");
	

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.actionDriver = new ActionDriver(driver); // Initialize with the provided driver
	}

	public enum EnterText {
		GOOGLE_EMAIL("Google Email TextBox", By.xpath("//input[@id='identifierId']")),
		GOOGLE_PASSWORD("Google Password TextBox", By.xpath("//input[@name='Passwd']")),
		MICROSOFT_EMAIL("Microsoft Email TextBox", By.xpath("//input[@id='i0116']")), // Fixed typo
		MICROSOFT_PASSWORD("Microsoft Password TextBox", By.xpath("//input[@id='i0118']"));

		private final String name;
		private final By locator;

		EnterText(String name, By locator) {
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

	public void enterText(EnterText textField, String value) {
		Log.info("Entering text into " + textField.getName() + ": " + value);
		actionDriver.enterText(textField.getLocator(), value);
	}

	public enum ButtonType {
		LOGIN("Login Button", AttributeHelper.getElementByAttribute("button", "button-submit")),
		LOGOUT("Logout Button", AttributeHelper.getElementByAttribute("button", "nav-logout")),
		SIGN_UP("Sign Up Button", AttributeHelper.getElementByAttribute("a", "signup-link")),
		NAV_SIGN_UP("Nav Sign Up Button", AttributeHelper.getElementByAttribute("button", "nav-signup-link")),
		PASS_VIS_TOGGLE("Password Visibility Toggle", AttributeHelper.getElementByAttribute("button", "password-visibility")),
		REMEMBER_ME("Remember Me Button", AttributeHelper.getElementByAttribute("button", "field-rememberme")),
		GOOGLE_AUTH("Google Auth Button", AttributeHelper.getElementByAttribute("button", "signin-google-auth")),
		GOOGLE_SIGN_IN("Google Sign In Button", By.xpath("//button[@class='VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc LQeN7 BqKGqe Jskylb TrZEUc lw1w4b']")),
		MICROSOFT_AUTH("Microsoft Auth Button", AttributeHelper.getElementByAttribute("button", "signin-ms-auth")),
		MICROSOFT_NEXT("Microsoft Next Button", By.xpath("//input[@id='idSIButton9']")),
		MICROSOFT_SIGN_IN("Microsoft Sign In Button", By.xpath("//button[@id='idSIButton9']")),
		MICROSOFT_STAY_SIGNED_IN("Microsoft Stay Signed In Button", By.xpath("//button[@id='declineButton']")),
		FORGOT_PASSWORD("Forgot Password Link", By.xpath("//*[@id=\"root\"]/div/div[2]/main/div[2]/div/div[2]/form/div[2]/label/a"));

		private final String name;
		private final By locator;

		ButtonType(String name, By locator) {
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

	public void clickButton(ButtonType buttonType) {
		Log.info("Clicking " + buttonType.getName() + "...");
		actionDriver.click(buttonType.getLocator());
	}

	public By getMessageLocator(String fieldName) {
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

	    
	public void logoutAndVerify() {
	    clickButton(ButtonType.LOGOUT);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.urlContains("/login"));
	    Assert.assertTrue(isMessageDisplayed("emailTextBox"), 
	        "Expected to be redirected to the login page after logout.");
	}
}
