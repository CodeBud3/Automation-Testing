package base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import actiondriver.ActionDriver;
import utils.ConfigReader;
import utils.ExtentReportManager;
import utils.Log;
import utils.ApiRequestHandler;

public class BaseTest {
	protected static ActionDriver actionDriver;
	protected static ExtentReports extent;
	protected static Properties prop;
	protected static WebDriver driver;
	protected ExtentTest test;
	private String userDataDir; // Store the user data directory for the test

	@BeforeSuite
	public void setupSuite() throws IOException {
	    // Clean up old screenshots
	    File screenshotDir = new File(System.getProperty("user.dir") + "/reports/screenshots");
	    if (screenshotDir.exists()) {
	        for (File file : screenshotDir.listFiles()) {
	            file.delete();
	        }
	    }
	    extent = ExtentReportManager.getReportInstance();
	}

	@AfterSuite
	public void teardownReport() throws Exception {
		extent.flush();
		// ApiRequestHandler.deleteUser("puni.kumar143@outlook.com");
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
		if (result.getStatus() == ITestResult.SUCCESS) {
			Log.info("Test passed.");
			test.pass("Test passed.");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			ExtentReportManager.captureAndAttachScreenshot(driver, test,
					result.getMethod().getMethodName() + "_Failure", "Screenshot on test failure", false);
			test.fail("Test failed: " + result.getThrowable().getMessage());
		}

		if (driver != null) {
			Log.info("Terminating driver!");
			driver.quit();
		}
		driver = null;
		actionDriver = null;
		userDataDir = null;
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

	public void staticWait(int seconds) {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	}

	public void initDriver() {
		String browser = ConfigReader.getProperty("browser");
		Log.info("Initializing the driver...");

		switch (browser.toLowerCase()) {
		case "chrome": {
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			options.addArguments("--disable-blink-features=AutomationControlled");
			options.addArguments("--disable-extensions");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--start-maximized");
			options.addArguments(
					"user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36");
			// Use a unique user data directory for each test, but preserve it within the
			// test
			if (userDataDir == null) {
				userDataDir = System.getProperty("java.io.tmpdir") + "/chrome-user-data-" + System.currentTimeMillis();
			}
			options.addArguments("--user-data-dir=" + userDataDir);
			driver = new ChromeDriver(options);
			((JavascriptExecutor) driver)
					.executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
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

	public void configureBrowser() {
		// Implicit Wait
		int implicitWait = ConfigReader.getIntProperty("implicitWait");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

		driver.manage().window().maximize();
		Log.info("Navigating to URL");
		driver.get(ConfigReader.getProperty("url"));
	}

}
