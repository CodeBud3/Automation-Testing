package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import utils.ExtentReportManager;
import utils.Log;


public class BaseTest {
	protected Properties prop;
	protected WebDriver driver;
	protected static ExtentReports extent;
	protected ExtentTest test;

	@BeforeSuite
	public void setupReport() {
		extent = ExtentReportManager.getReportInstance();
	}

	@AfterSuite
	public void teardownReport() {
		extent.flush();
	}

	@BeforeMethod
	public void setUp() throws IOException {
		// Load configuration file
		setupProperties();

		// Initialize the web driver based on config.properties
		initDriver();

		// Implicit Wait
		int implicitWait = Integer.parseInt(prop.getProperty("implicitWait"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

		driver.manage().window().maximize();
		Log.info("Navigating to URL");
		driver.get(prop.getProperty("url"));
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		// capture screenshots for failed cases
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotPath = ExtentReportManager.captureScreenshot(driver, result.getMethod().getMethodName());
			test.fail("Test failed ... Check screenshot",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
		if (driver != null) {
			Log.info("Terminating driver!");
			driver.quit();
		}
	}

	public void setupProperties() throws IOException {
		Log.info("Loading configuration");
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
		prop.load(fis);
	}

	public void initDriver() {
		String browser = prop.getProperty("browser");
		Log.info("Initializing the driver...");

		switch (browser.toLowerCase()) {
			case "chrome": {
				driver = new ChromeDriver();
				break;
			}
			case "firefox": {
				driver = new FirefoxDriver();
				break;
			}
			case "edge": {
				driver = new EdgeDriver();
				break;
			}
			default: {
				throw new IllegalArgumentException("Browser not supported: " + browser);
			}
		}
	}
}
