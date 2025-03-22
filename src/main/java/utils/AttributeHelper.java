package utils;
import org.openqa.selenium.By;
public class AttributeHelper {
	
	public static By getElementByAttribute(String element, String attribute) {
		return By.xpath("//"+element+"[@data-test-"+attribute+"='true']");
	}
	
	public static By getElementByAttribute(String element, String attribute, String options) {
		return By.xpath("//"+element+"[@data-test-"+attribute+"='true'] "+options);
	}
}
