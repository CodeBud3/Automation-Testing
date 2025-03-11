package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Log;

public class LoginPage {
	private WebDriver driver;
	
	@FindBy(id="Email")
	WebElement usernameTextbox;
	@FindBy(id="Password")
	WebElement passwordTextbox;
	@FindBy(xpath="//*[@id=\"main\"]/div/div/div/div[2]/div[1]/div/form/div[3]/button")
	WebElement loginButton;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public void enterUsername(String username) {
		Log.info("Entering Username : " + username);
		usernameTextbox.clear();
		usernameTextbox.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		Log.info("Entering password");
		passwordTextbox.clear();
		passwordTextbox.sendKeys(password);
	}
	
	public void clickLogin() {
		Log.info("Clicking Login button");
		loginButton.click();
	}
}
