package com.qa.opencart.testcases;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.AppConstants;
import com.qa.opencart.utils.ScreenShotUtils;
public class HomePageTestCase extends BaseTest{

	
	
	@Test
	public void homePageTitle() {
		Assert.assertNotNull(hp, "HomePage object is null");
		String actualTitle=hp.getHomePageTitle();
		ScreenShotUtils.captureScreenshot(page);
		Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);	
	} 
	
	@Test
	public void searchTest() {
		ScreenShotUtils.captureLocatorScreenshot(hp.getSearchIcon());
        Assert.assertNotNull(hp, "HomePage object is null");

		hp.doSearch("macbook");
		 
	}
	
	
}
 