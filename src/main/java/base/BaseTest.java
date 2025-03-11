package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

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

import actiondriver.ActionDriver;
import utils.ExtentReportManager;
import utils.Log;

public class BaseTest {
	protected static ActionDriver actionDriver;
	protected static ExtentReports extent;
	protected static Properties prop;
	protected static WebDriver driver;
	protected ExtentTest test;

	@BeforeSuite
	public void setupSuite() throws IOException {
		// Load configuration file
		loadConfig();
		extent = ExtentReportManager.getReportInstance();
	}

	@AfterSuite
	public void teardownReport() {
		extent.flush();
	}

	@BeforeMethod
	public void setUp() {
		// Initialize the web driver based on config.properties
		initDriver();
		// setup browser configurations
		configureBrowser();
		if (actionDriver == null) {
			actionDriver = new ActionDriver(driver);
		}
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
		driver = null;
		actionDriver = null;
	}

	public static WebDriver getWebDriver() {
		return driver;
	}
	
	public static ActionDriver getActionDriver() {
		if (actionDriver == null) {
			Log.error("Action driver is not initialized");
			throw new IllegalStateException("Action driver is not initialized");
		}
		return actionDriver;
	}
	public void loadConfig() throws IOException {
		Log.info("Loading configuration");
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
		prop.load(fis);
	}

	public void staticWait(int seconds) {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	}
	
	public static Properties getProp() {
		return prop;
	}
	
	private void initDriver() {
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

	private void configureBrowser() {
		// Implicit Wait
		int implicitWait = Integer.parseInt(prop.getProperty("implicitWait"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

		driver.manage().window().maximize();
		Log.info("Navigating to URL");
		driver.get(prop.getProperty("url"));
	}
	

}
