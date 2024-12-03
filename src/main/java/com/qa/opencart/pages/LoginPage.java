package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
	private Page page;
	

	private String emailAddress="//input[@id='input-email']";
	private String passwordText="#input-password";
	private String loginBtn="//input[@value='Login']";
	private String forgotPasswordLink="//div[@class='form-group']//a[normalize-space()='Forgotten Password']";
	private String myaccLink="//a[@class='list-group-item'][normalize-space()='My Account']";
	
	public LoginPage(Page page) {
		this.page=page;
	}
	
	public String getLoginPageTitle() {
		String title= page.title();
		System.out.println("Login Page Title IS"+title);
		return title;
	}
	public boolean login(String email, String password) {
	
		page.fill(emailAddress, email);
	
		page.fill(passwordText, password);
		page.click(loginBtn);
		
		if(page.isVisible(myaccLink)) {
			System.out.println("User has been logged in");
			return true;			
		}
		return false;
		
	}
	
	public boolean isForgotPasswordLinkVisible() {
		return page.isVisible(forgotPasswordLink);
	}
}

