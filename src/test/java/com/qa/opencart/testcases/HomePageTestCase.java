package com.qa.opencart.testcases;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.AppConstants;

public class HomePageTestCase extends BaseTest{

	
	
	@Test
	public void homePageTitle() {
		String actualTitle=hp.getHomePageTitle();
		Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);	
	} 
	
	@Test
	public void searchTest() {
		hp.doSearch("macbook");
		 
	}
	
	
}
 