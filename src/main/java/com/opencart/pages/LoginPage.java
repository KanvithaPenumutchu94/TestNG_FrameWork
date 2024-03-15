package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.internal.BaseTestMethod;

import com.opencart.constants.AppConstants;
import com.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage  {
	
	private WebDriver driver;
	private ElementUtil eleUtil;

	// By locators: OR
	private By userName = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img[title='naveenopencart']");
	
	private By registerLink = By.linkText("Register");
	
	
	// page const...
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.MEDIUM_DEFAUTT_WAIT);
		return title;
	}
	
	public boolean isForgorPwdLinkExist() {
		return eleUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.MEDIUM_DEFAUTT_WAIT).isDisplayed();
	}
	
	@Step("Login into the application method")
	public boolean doLogin(String un,String pw) {
		System.out.println("Credentials are: "+un+" and "+pw);
		eleUtil.doSendKeys(userName, un);
		eleUtil.doSendKeys(password, pw);
		eleUtil.doClick(loginBtn);
		System.out.println("user is logged in successfully");
		return true;
	}
	
	public boolean isRegisterLinkExist() {
		return eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.MEDIUM_DEFAUTT_WAIT).isDisplayed();
	}
	
	public boolean doClickOnRegisterLink() {
		eleUtil.doClick(registerLink);
		return true;
	}
	
	



}
