package actiondriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseTest;
import utils.Log;

public class ActionDriver {
	private WebDriver driver;
	private WebDriverWait wait;

	public ActionDriver(WebDriver driver) {
		this.driver = driver;
		int explicitWait = Integer.parseInt(BaseTest.getProp().getProperty("explicitWait"));
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
	}

	public void click(By by) {
		try {
			waitForElementToBeClickable(by);
			driver.findElement(by).click();
		} catch (Exception e) {
			Log.error("Unable to click element: " + e.getMessage());
		}
	}

	public void enterText(By by, String value) {
		try {
			waitForElementToBeVisible(by);
			WebElement element = driver.findElement(by);
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			Log.error("Unable to enter value:  " + e.getMessage());
		}
	}

	public String getText(By by) {
		try {
			waitForElementToBeVisible(by);
			return driver.findElement(by).getText();
		} catch (Exception e) {
			Log.error("Unable to get text:  " + e.getMessage());
			return "";
		}
	}

	public boolean compareText(By by, String expectedText) {
		try {
			waitForElementToBeVisible(by);
			String actualText = driver.findElement(by).getText();
			if (expectedText.equals(actualText)) {
				Log.info("Text are matching: " + actualText + "=" + expectedText);
				return true;
			}
			Log.info("Text are not matching: " + actualText + "!=" + expectedText);
			return false;

		} catch (Exception e) {
			Log.error("Error while comparing text: " + e.getMessage());
			return false;
		}
	}

	public boolean isDisplayed(By by) {
		try {
			waitForElementToBeVisible(by);
			return driver.findElement(by).isDisplayed();

		} catch (Exception e) {
			Log.error("Error while comparing text: " + e.getMessage());
			return false;
		}
	}

	private void waitForElementToBeClickable(By by) {
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	private void waitForElementToBeVisible(By by) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitForPageLoad(int timeoutInsec) {
		try {
			wait.withTimeout(Duration.ofSeconds(timeoutInsec)).until(WebDriver -> ((JavascriptExecutor) WebDriver)
					.executeScript("return document.readyState").equals("complete"));
			Log.info("Page loaded successfully");
		} catch (Exception e) {
			Log.error("Unable to load page within " + timeoutInsec + "s : "+ e.getMessage());
		}
	}
	
	public void scrollToElement(By by) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(by);
			js.executeScript("arguments[0], scrollIntoView(true);", element);
		} catch (Exception e) {
			Log.error("Failed to scroll to element: "+e.getMessage());
		}
	}
}
