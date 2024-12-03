package com.qa.opencart.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage {
//Object REpository
	
	private Page page;
	
//String locators
	private String searchField="//input[@placeholder='Search']";
	private String searchIcon="//button[@class='btn btn-default btn-lg']";
	private String searchHeader="//label[@for='input-search']";
	private String myAccount="//span[normalize-space()='My Account']";
	private String loginLink="//a[normalize-space()='Login']";
	
	
	//page constructor
	public HomePage(Page page) {
		this.page=page;
	}
	
	// Get Locator methods for elements
    public Locator getSearchField() {
        return page.locator(searchField);
    }

    public Locator getSearchIcon() {
        return page.locator(searchIcon);
    }

    public Locator getSearchHeader() {
        return page.locator(searchHeader);
    }

    public Locator getMyAccount() {
        return page.locator(myAccount);
    }

    public Locator getLoginLink() {
        return page.locator(loginLink);
    }

	
	//page actions
	public String getHomePageTitle() {
		
		String title= page.title();
		System.out.println("Home Page Title IS"+title);
		return title;
	}
	
	public String getHomePageURL() {
		return page.url();
	}
	 public String doSearch(String productName) {
		 page.fill(searchField, productName);
		 page.click(searchIcon);
		 String header= page.textContent(searchHeader);
		 System.out.println("header is"+header);
		 return header;
	 }
	 
	 public LoginPage navigateToLoginPage() {
		 clickMyAccount();
		 page.click(loginLink);
		 return new LoginPage(page);
		 
	 }
	 
	 public void clickMyAccount() {
		 page.click(myAccount);
		 
	 }
}
