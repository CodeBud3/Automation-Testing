package actiondriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import enums.ActionTypes.ActionType;
import enums.LoginPageLocatorEnum.ElementLocators;
import interfaces.ElementLocator;
import utils.ConfigReader;
import utils.Log;

public class ActionDriver {
	private WebDriver driver;
	private WebDriverWait wait;

	public ActionDriver(WebDriver driver) {
		this.driver = driver;
		int explicitWait = ConfigReader.getIntProperty("explicitWait");
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
	public boolean compareInputAttribute(By by, String attribute, String expectedText) {
		try {
			waitForElementToBeVisible(by);
			String actualText = driver.findElement(by).getDomProperty(attribute);
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
	        boolean displayed = driver.findElement(by).isDisplayed();
	        Log.info("Element " + by + " is displayed: " + displayed);
	        return displayed;
	    } catch (Exception e) {
	        Log.info("Element " + by + " is not displayed: " + e.getMessage());
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
	
	

	public void performAction(ActionType actionType, ElementLocator element, String value) {
		try {
			String logMessage = actionType.getLogPrefix() + " " + element.getName()
					+ (actionType == ActionType.ENTER_TEXT ? ": " + value : "...");
			Log.info(logMessage);

			switch (actionType) {
			case ENTER_TEXT:
				if (value == null) {
					throw new IllegalArgumentException("Value cannot be null for ENTER_TEXT action");
				}
				enterText(element.getLocator(), value);
				break;
			case CLICK:
				click(element.getLocator());
				break;
			default:
				throw new IllegalArgumentException("Unsupported action type: " + actionType);
			}
		} catch (Exception e) {
			Log.error("Failed to perform " + actionType + " on " + element.getName() + ": " + e.getMessage());
			throw e;
		}
	}

	public void performAction(ActionType actionType, ElementLocator element) {
		if (actionType == ActionType.ENTER_TEXT) {
			throw new IllegalArgumentException(
					"ENTER_TEXT action requires a value; use the overloaded method with a value parameter");
		}
		performAction(actionType, element, null);
	}
}