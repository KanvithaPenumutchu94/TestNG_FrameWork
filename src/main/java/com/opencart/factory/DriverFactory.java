package com.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.internal.BaseTestMethod;

import com.opencart.exception.FrameworkException;


public class DriverFactory {
	
	private WebDriver driver;
	Properties prop;
	OptionsManager options;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		//String browserName = System.getProperty("browser");
		
		System.out.println("Browser name is: "+browserName);
		
		options = new OptionsManager(prop);
		
		switch(browserName.toLowerCase().trim()) {
		case "chrome":
			//driver = new ChromeDriver(options.getChromeOptions());
			tlDriver.set(new ChromeDriver(options.getChromeOptions()));
			break;
		case "edge":
			//driver = new EdgeDriver(options.getEdgeOptions());
			tlDriver.set( new EdgeDriver(options.getEdgeOptions()));
			break;
		case "firefox":
			//driver = new FirefoxDriver(options.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(options.getFirefoxOptions()));
		case "safari":
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;
		default:
			System.out.println("please pass the right browser name..."+browserName);
			//throw new FrameworkException("No Browser Found...");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public Properties initProp()  {
		
		FileInputStream ip = null;
		prop = new Properties();
		
		String envName = System.getProperty("env");
		System.out.println("env name is: "+envName);
		
		try {
			if(envName == null) {
				System.out.println("your env is null..hence running tests in QA env...");
				ip = new FileInputStream(".\\src\\test\\resources\\config\\config.properties");
			}
			else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\config.qa.properties");
					break;
				case "dev":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\config.dev.properties");
					break;
				case "stage":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\config.stage.properties");
					break;
				case "uat":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\config.uat.properties");
					break;
				case "prod":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\config.prod.properties");
					break;
				default:
					System.out.println("please pass the right env name..."+envName);
					throw new FrameworkException("Wrong Env Name: "+envName);
				}
			}
		}catch(FileNotFoundException e) {
			
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis() + ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
