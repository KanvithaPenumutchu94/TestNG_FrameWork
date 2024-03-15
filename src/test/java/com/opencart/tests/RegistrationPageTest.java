package com.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.base.BaseTest;
import com.opencart.constants.AppConstants;
import com.opencart.pages.AccountsPage;
import com.opencart.pages.RegistrationPage;
import com.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {
	
	@BeforeClass
	public void accSetup() {
		registerPage = new RegistrationPage(driver);
		//loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		loginPage.doClickOnRegisterLink();
	}
	
	//way -1
//	@Test
//	public void userRegTest() {
//		boolean isRegDone = registerPage.userRegisteration("kann", "kota", "gfjs@gmail.com", "1234567890", "test123", "yes");
//		Assert.assertTrue(isRegDone);
//	}
	
	
//	//way -2
//	@DataProvider
//	public Object[][] getUserRegData(){
//		return new Object[][] {
//			
//			{ "Rahul", "yadav", "dfjsh@gmail.com", "9876543233", "test@123", "yes" },
//			{ "Karishma", "automation", "sds@gmail.com", "9876544434", "test@123", "no" },
//			{ "Jyothi", "auto", "hdfsk@gmail.com", "9876522234", "test@123", "yes" },
//		};
//	}
//	
//	@Test(dataProvider = "getUserRegData")
//	public void userRegTest(String firstName, String lastName, String eid, String telephone, String password, String subscribe) {
//
//		boolean isRegDone = registerPage.userRegisteration(firstName, lastName, eid, telephone, password,
//				subscribe);
//		Assert.assertTrue(isRegDone);
//	}
	
	
	
	//way -3
//	public String getRandomEmailID() {
//		return "testautomation" + System.currentTimeMillis() + "@opencart.com";
//		// testautomation1212121@opencart.com
//		// return "testautomation" + UUID.randomUUID()+"@gmail.com";
//	}
//	@DataProvider
//	public Object[][] getUserRegData(){
//		return new Object[][] {
//			
//			{ "Rahul", "yadav", "9876543233", "test@123", "yes" },
//			{ "Karishma", "automation", "9876544434", "test@123", "no" },
//			{ "Jyothi", "auto", "9876522234", "test@123", "yes" }
//		};
//	}
//
//	@Test(dataProvider = "getUserRegData")
//	public void userRegTest(String firstName, String lastName, String telephone, String password, String subscribe) {
//
//		boolean isRegDone = registerPage.userRegisteration(firstName, lastName, getRandomEmailID(), telephone, password,
//				subscribe);
//		Assert.assertTrue(isRegDone);
//	}
	
	
	//way -4
	public String getRandomEmailID() {
		return "testautomation" + System.currentTimeMillis() + "@opencart.com";
		// testautomation1212121@opencart.com
		// return "testautomation" + UUID.randomUUID()+"@gmail.com";
	}
	
	@DataProvider
	public Object[][] getUserRegTestExcelData() {
		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_DATA_SHEET_NAME);
		return regData;
	}
	
	//run = total rows
	// params = total cols
	@Test(dataProvider = "getUserRegTestExcelData")
	public void userRegTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		boolean isRegDone = registerPage.userRegisteration(firstName, lastName, getRandomEmailID(), telephone, password,
				subscribe);
		Assert.assertTrue(isRegDone);
	}
	
	
	


}
