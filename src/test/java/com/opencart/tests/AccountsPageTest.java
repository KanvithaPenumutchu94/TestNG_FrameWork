package com.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.opencart.base.BaseTest;
import com.opencart.pages.AccountsPage;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accSetup() {
		accountsPage = new AccountsPage(driver);
		loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test
	public void isSearchFieldExistTest() {
		Assert.assertTrue(accountsPage.isSearchFieldExist());
	}

}
