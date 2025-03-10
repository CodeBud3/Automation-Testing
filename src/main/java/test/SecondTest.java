package test;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class SecondTest {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        String title = driver.getTitle();
		System.out.println("Title is " + title);
		
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement text = driver.findElement(By.id("my-text-id"));
        WebElement button = driver.findElement(By.cssSelector("button"));
        
        text.sendKeys("Selenium");
        button.click();
        
        WebElement message = driver.findElement(By.id("message"));
        System.out.println(message.getText());
		driver.quit();
	}
}
