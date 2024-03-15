package com.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.opencart.factory.DriverFactory;
import com.opencart.pages.AccountsPage;
import com.opencart.pages.LoginPage;
import com.opencart.pages.RegistrationPage;

public class BaseTest {

	protected WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountsPage accountsPage;
	protected RegistrationPage registerPage;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String browserName) {
		df = new DriverFactory();
		prop = df.initProp();
		
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		
		driver = df.initDriver(prop);
		
		loginPage = new LoginPage(driver);
	}
	
	@AfterTest
	public void tearDown() {
		//driver.quit();
	}
}
