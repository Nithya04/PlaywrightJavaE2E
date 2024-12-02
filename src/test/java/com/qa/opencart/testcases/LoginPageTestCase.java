package com.qa.opencart.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.AppConstants;
import com.qa.opencart.pages.HomePage;

public class LoginPageTestCase extends BaseTest {

	@Test(priority = 1)
	public void loginPageNavigationTest() {
		lp=hp.navigateToLoginPage();
		String actualLoginPoageTitle=lp.getLoginPageTitle();
		System.out.println("LOgin Page Title is "+actualLoginPoageTitle);
		Assert.assertEquals(actualLoginPoageTitle, AppConstants.LOGIN_PAGE_TITLE);	

	}
	
	@Test(priority = 2)
	public void ValidateForgotPwdLink() {
		Assert.assertTrue(lp.isForgotPasswordLinkVisible());
		
	}
	
	@Test(priority = 3)
	
	public void validateLogin() {
		lp.login(prop.getProperty("username"), prop.getProperty("password"));
		//Assert.assertTrue(lp.lopgin(prop.getProperty("username").trim(), prop.getProperty("password").trim())); 
	}
	
}
