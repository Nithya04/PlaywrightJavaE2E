 package com.qa.opencart.base;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;

public class BaseTest {
 
	PlaywrightFactory pf;
	Page page;
	protected HomePage hp;
	protected LoginPage lp; 
	protected Properties prop;

	@BeforeTest
	public void setUp() {
		try {
	        System.out.println("Starting test setup...");

	        // Initialize Playwright Factory
	        pf = new PlaywrightFactory();
	        System.out.println("PlaywrightFactory initialized: " + (pf != null));

	        // Load Properties
	        prop = pf.init_prop();
	        System.out.println("Properties loaded: " + (prop != null));

	        // Initialize Browser and Page
	        page = pf.initBrowser(prop);
	        System.out.println("Browser and Page initialized: " + (page != null));

	        // Initialize HomePage
	        hp = new HomePage(page);
	        System.out.println("HomePage initialized: " + (hp != null));
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Setup failed: " + e.getMessage());
	    }
	}
		
	}
	
	@AfterTest	
	public void tearDown() {
		page.context().browser().close();
	}
}
