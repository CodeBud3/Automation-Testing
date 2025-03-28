package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
	private static ExtentReports extent;
	private static ExtentTest test;
	public static String reportPath;

	public static ExtentReports getReportInstance() {

		if (extent == null) {

			String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
			reportPath = "reports/ExtentReport_" + timestamp + ".html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

			reporter.config().setDocumentTitle("Automation Test Report");
			reporter.config().setReportName("Test Execution Report");

			extent = new ExtentReports();
			extent.attachReporter(reporter);
		}

		return extent;
	}

	public static ExtentTest createTest(String testName) {

		test = getReportInstance().createTest(testName);
		return test;
	}

	public static void captureAndAttachScreenshot(WebDriver driver, ExtentTest test, String screenshotName, String message, boolean isPass) {
	    try {
	        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	        String fileName = screenshotName + "_" + timestamp + ".png";
	        String relativePathForSave = "reports/screenshots/" + fileName; // Save in reports/screenshots/
	        String relativePathForReport = "screenshots/" + fileName; // Relative to reports/
	        String absolutePath = System.getProperty("user.dir") + "/" + relativePathForSave;

	        // Ensure the directory exists
	        File screenshotDir = new File(System.getProperty("user.dir") + "/reports/screenshots");
	        if (!screenshotDir.exists()) {
	            screenshotDir.mkdirs();
	        }

	        FileUtils.copyFile(src, new File(absolutePath));

	        Log.info("Screenshot captured at: " + absolutePath);
	        if (isPass) {
	            test.pass(message, MediaEntityBuilder.createScreenCaptureFromPath(relativePathForReport).build());
	        } else {
	            test.fail(message, MediaEntityBuilder.createScreenCaptureFromPath(relativePathForReport).build());
	        }
	    } catch (Exception e) {
	        Log.error("Failed to capture screenshot: " + e.getMessage());
	        test.fail("Failed to capture screenshot: " + e.getMessage());
	    }
	}
}