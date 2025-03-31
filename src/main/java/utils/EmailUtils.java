package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;

import java.time.Duration;

public class EmailUtils {
    private WebDriver driver;
    private WebDriverWait wait;

    public EmailUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public String getPasswordResetLinkFromYopmail(String email) {
        try {
            // Navigate to Yopmail
            Log.info("Navigating to Yopmail to retrieve password reset email...");
            driver.get("https://yopmail.com/wm");

            // Enter the email address in Yopmail's inbox field
            By emailInput = By.id("login");
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
            driver.findElement(emailInput).clear();
            driver.findElement(emailInput).sendKeys(email.split("@")[0]); // Enter only the username part (avibignoop)

            // Click the arrow button to access the inbox
            By checkInboxButton = By.cssSelector("button#refresh");
            driver.findElement(checkInboxButton).click();

            // Wait for the email to appear (you may need to refresh the inbox)
            By emailSubject = By.xpath("//div[contains(text(), 'Password Reset Request')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailSubject));
            driver.findElement(emailSubject).click();

            // Switch to the email content iframe
            By emailIframe = By.id("ifmail");
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailIframe));
            driver.switchTo().frame(driver.findElement(emailIframe));

            // Extract the reset link (the "here" link)
            By resetLink = By.xpath("//a[contains(text(), 'here')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(resetLink));
            String resetUrl = driver.findElement(resetLink).getAttribute("href");

            // Switch back to the default content
            driver.switchTo().defaultContent();

            Log.info("Password reset link retrieved: " + resetUrl);
            return resetUrl;

        } catch (Exception e) {
            Log.error("Failed to retrieve password reset link from Yopmail: " + e.getMessage());
            throw e;
        }
    }
}