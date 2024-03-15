package com.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Design opencart login page")
@Story("US 101: Login page features")
@Feature("F 50: Login feature")
public class LoginPageTest extends BaseTest {
	
	@Description("login page test...")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void loginPageTitleTest() {
		loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}

}
