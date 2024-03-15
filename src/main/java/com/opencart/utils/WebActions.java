package com.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.internal.BaseTestMethod;

import com.opencart.factory.DriverFactory;

public class WebActions {

	public static int time = 60;
	public static Alert alert;
	public static Select select;
	public static Actions action;
	public static WebDriver driver;
	
	public WebActions(WebDriver driver) {
		this.driver = driver;
	}

	public static void enterURL(String url) {
		driver.get(url);
		// ExtentReportManager.logInfo("Opened the URL");
	}

	public static void clear(WebElement e,String elementName) {
		WebElement ele = waitForVisibility(e);
		ele.clear();
		//ExtentReportManager.logInfo("Cleared the text from "+ elementName);
	}

	public static void click(WebElement e,String elementName) {
		WebElement ele = waitForVisibility(e);
		ele.click();
		//ExtentReportManager.logInfo("Clicked on : "+ elementName);
	}

	public static void sendKeys(WebElement e, String text,String elementName) {
		WebElement ele = waitForVisibility(e);
		ele.sendKeys(text);
		//ExtentReportManager.logInfo("Entered : "+text+" into "+ elementName);
	}

	public static void sendKeysWithKeys(WebElement e, Keys keys) {
		WebElement ele = waitForVisibility(e);
		ele.sendKeys(keys);

	}

	public static void sendKeysWithKeys_1(WebElement e, Keys keys, String text) {
		WebElement ele = waitForVisibility(e);
		ele.sendKeys(keys, text);

	}

	public static String getText(WebElement e,String elementName) {
		WebElement ele = waitForVisibility(e);
		String value_of_gettext = ele.getText();
		//ExtentReportManager.logInfo("Got the text from : "+elementName);
		return value_of_gettext;
	}

	public static String getAttribute(WebElement e, String attributeName,String elementName) {
		WebElement ele = waitForVisibility(e);
		String value_of_getattribute = ele.getAttribute(attributeName);
		//ExtentReportManager.logInfo("Got the attribute value from : "+elementName);
		return value_of_getattribute;
	}

	public static String get_Title(String elementName) {
		String title = driver.getTitle();
		//ExtentReportManager.logInfo("Got the title value from : "+elementName);
		return title;
	}

	public static boolean isDisplayed(WebElement e,String elementName) {
		WebElement ele = waitForVisibility(e);
		boolean displayed_status = ele.isDisplayed();
		if (displayed_status == true) {
			//ExtentReportManager.logInfo("Displayed the : " +elementName);
		} else {
			//ExtentReportManager.logInfo("Not Displayed the : " +elementName);
		}

		return displayed_status;
	}

	public static boolean isEnabled(WebElement e, String info) {
		WebElement ele = waitForVisibility(e);
		boolean enabled_status = ele.isEnabled();
		if (enabled_status == true) {
			System.out.println("successfully enabled the : " + info + " in the web page");
		} else {
			System.out.println("Unsuccessfully enabled the : " + info + " in the web page");
		}
		return enabled_status;
	}

	public static boolean isSelected(WebElement e) {
		WebElement ele = waitForClickable(e);
		boolean selected_status = ele.isSelected();
		return selected_status;
	}

	public static String getWindowHandle(String info) {
		String windowID = driver.getWindowHandle();
		System.out.println("successfully return the current window Id of " + info);
		return windowID;
	}

	public static List<String> getWindowHandles() {
		Set<String> windowIDs = driver.getWindowHandles();
		List<String> windowIDslist = new ArrayList(windowIDs); // covert Set ---> List
		/*
		 * String parentWindowID=windowIDslist.get(0); String
		 * childWindowID=windowIDslist.get(1); driver.switchTo().window(childWindowID);
		 * System.out.println("child window title:"+driver.getTitle());
		 */
		return windowIDslist;
	}

	public static void maximize_Browser() {
		driver.manage().window().maximize();
	}

	public static void minimize_Browser() {
		driver.manage().window().minimize();
	}

	public static void refresh() {
		driver.navigate().refresh();
		//ExtentReportManager.logInfo("Refreshed the page");
	}

	public static void forward() {
		driver.navigate().forward();
	}

	public static void back() {
		driver.navigate().back();
	}

	public static WebElement waitForVisibility(WebElement e) {
		WebElement element = new WebDriverWait(driver, Duration.ofSeconds(time))
				.until(ExpectedConditions.visibilityOf(e));
		return element;
	}

	public static WebElement waitForClickable(WebElement e) {
		WebElement element = new WebDriverWait(driver, Duration.ofSeconds(time))
				.until(ExpectedConditions.elementToBeClickable(e));
		return element;
	}

	public static Boolean waitForSelectable_status(WebElement e) {
		Boolean status = new WebDriverWait(driver, Duration.ofSeconds(time))
				.until(ExpectedConditions.elementToBeSelected(e));
		return status;
	}

	public static void checkBox_click(WebElement e) {
		WebElement ele = waitForClickable(e);
		ele.click();
	}

	public static List<WebElement> waitForClickable_multiple(By locator) {
		List<WebElement> elements = new WebDriverWait(driver, Duration.ofSeconds(time))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		for (WebElement element : elements) {
			element.click();
		}
		return elements;
	}

	public static void checkBoxes(By locator) {
		List<WebElement> checkboxes = waitForClickable_multiple(locator);
		// List<WebElement> checkboxes=driver.findElements(locator);
		for (WebElement checkbox : checkboxes) {
			checkbox.click();
		}
	}

	public static void switchToAlert() {
		alert = driver.switchTo().alert();
	}

	public static void accept_alert() {
		alert.accept();
		//ExtentReportManager.logInfo("Accepted the alert");
	}

	public static void dismiss_alert() {
		alert.dismiss();
		//ExtentReportManager.logInfo("Dismissed the alert");
	}

	public static void sendKeys_alert(String text) {
		alert.sendKeys(text);
	}

	public static String getText_alert() {
		String textFromAlert = alert.getText();
		return textFromAlert;
	}

	public static void selectByIndex(WebElement e, int index) {
		WebElement ele = waitForClickable(e);
		select = new Select(ele);
		select.selectByIndex(index);
	}

	public static void selectByValue(WebElement e, String value) {
		WebElement ele = waitForVisibility(e);
		select = new Select(ele);
		select.selectByValue(value);
	}

	public static void selectByVisibleText(WebElement e, String text) {
		WebElement ele = waitForVisibility(e);
		select = new Select(e);
		select.selectByVisibleText(text);
		//ExtentReportManager.logInfo("Selected the value based on visibleText from select class dropdown");
	}

	public static List<WebElement> getOptions(WebElement e) {
		WebElement ele = waitForClickable(e);
		select = new Select(ele);
		List<WebElement> options = select.getOptions();
		for (WebElement option : options) {
			String text_option = option.getText();
			System.out.println(text_option);
		}
		System.out.println("Test Completed");
		return options;
	}

	public static void selectEachOption(WebElement e) throws InterruptedException {
		select = new Select(e);
		List<WebElement> options = select.getOptions();
		System.out.println("size of the dropdown values " + options.size());

		for (WebElement option : options) {
			System.out.println(option.getText());
			option.click();
			System.out.println("successfully clicked on :" + option.getText());
			Thread.sleep(2000);
		}

	}

	public static void checkTitle(String actual, String expected) {
		Assert.assertEquals(actual, expected);
		//ExtentReportManager.logInfo("Actual and Expected tile are equal");
	}

	public static void actionClick(WebElement element,String elementName) {
		action = new Actions(driver);
		action.moveToElement(element).click();
		//ExtentReportManager.logInfo("Clicked on: "+elementName);
	}

	// Control+a using actions class
	public static void keyBoardActionCtrlA() {
		action = new Actions(driver);
		action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
	}

	// delete opration
	public static void keyBoardActionCtrlA_Delete(WebElement element) {
		action = new Actions(driver);
		action.click(element).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}

}
