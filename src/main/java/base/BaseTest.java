package base;

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
import factory.DriverFactory;
import utils.ConfigReader;
import utils.ExtentReportManager;
import utils.Log;

public class BaseTest {
	protected static ActionDriver actionDriver;
	protected static ExtentReports extent;
	protected static Properties prop;
	protected static WebDriver driverInstance;
	protected ExtentTest test;

	@BeforeSuite
	public void setupSuite() throws IOException {
		extent = ExtentReportManager.getReportInstance();
	}

	@AfterSuite
	public void teardownReport() throws Exception {
		extent.flush();
		// ApiRequestHandler.deleteUser("Avinash.Noop@example.com");
		// ApiRequestHandler.deleteUser("longemailaddresswithmultiplecharactersandnumbers1234567890abcdefghijklmnopqrstuvwx@domainexample.com");
		// ApiRequestHandler.deleteUser("Avinash.Noop+Doddu@example.com");
		// ApiRequestHandler.deleteUser("Avinash.NoopDoddu@example.com");
	}

	@BeforeMethod
	public void setUp() {
		// Initialize the web driver based on config.properties
		initDriver();
		// setup browser configurations
		configureBrowser();
		if (actionDriver == null) {
			actionDriver = new ActionDriver(DriverFactory.getDriver());
		}
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		// Capture screenshots for both success and failure cases
		if (result.getStatus() == ITestResult.SUCCESS) {
			String screenshotPath = ExtentReportManager.captureScreenshot(DriverFactory.getDriver(), result.getMethod().getMethodName());
			Log.info("Test passed. Screenshot captured at: " + screenshotPath);
			test.pass("Test passed. Check screenshot",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotPath = ExtentReportManager.captureScreenshot(DriverFactory.getDriver(), result.getMethod().getMethodName());
			Log.info("Test failed. Screenshot captured at: " + screenshotPath);
			test.fail("Test failed. Check screenshot",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}

		// Cleanup
		if (DriverFactory.getDriver() != null) {
			Log.info("Terminating driver!");
			DriverFactory.quitDriver();
		}
		driverInstance = null;
		actionDriver = null;
	}

	public static WebDriver getWebDriver() {
		return DriverFactory.getDriver();
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

	private void initDriver() {
		String browser = ConfigReader.getProperty("browser");
		Log.info("Initializing the driver...");

		switch (browser.toLowerCase()) {
		case "chrome": {
			driverInstance = new ChromeDriver();
			break;
		}
		case "firefox": {
			driverInstance = new FirefoxDriver();
			break;
		}
		case "edge": {
			driverInstance = new EdgeDriver();
			break;
		}
		default: {
			throw new IllegalArgumentException("Browser not supported: " + browser);
		}
		}
		DriverFactory.setDriver(driverInstance);
	}

	private void configureBrowser() {
		// Implicit Wait
		int implicitWait = ConfigReader.getIntProperty("implicitWait");
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

		DriverFactory.getDriver().manage().window().maximize();
		Log.info("Navigating to URL");
		DriverFactory.getDriver().get(ConfigReader.getProperty("url"));
	}
}
