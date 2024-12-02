package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import java.util.Base64;

public class PlaywrightFactory {
	
	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;
	Page page;
	Properties prop;
	private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
	private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
	private static ThreadLocal<Page> tlPage = new ThreadLocal<>();
	private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();

	public static Playwright getPlaywright() {
		return tlPlaywright.get();
	}

	public static Browser getBrowser() {
		return tlBrowser.get();
	}

	public static BrowserContext getBrowserContext() {
		return tlBrowserContext.get();
	}

	public static Page getPage() {
		return tlPage.get();
	}
	//INIT
	
	public Page initBrowser(Properties prop) {
		String browsername=prop.getProperty("browser").trim();
		System.out.println("Browser name is : "+browsername);
		// Initialize Playwright instance
		playwright=Playwright.create();
		
		switch(browsername.toLowerCase()) {
		case "chromium":
			browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "firefox":
			browser=playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "safari":
			browser=playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "chrome":
			browser=playwright.chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false));
			break;
			default:
				throw new IllegalArgumentException("Invalid browser name provided: " + browsername);
				
		}
		
		browserContext=browser.newContext();
		page=browserContext.newPage();
		page.navigate(prop.getProperty("url").trim());
		
		return page;
	}
	
	// Close resources
    public void tearDown() {
        if (playwright != null) {
            playwright.close();
        }
    }
// INITIALISE PROPERTY
    
    public Properties init_prop() {
    	try {
    	FileInputStream ip= new FileInputStream("./src/test/resources/config.properties/config.prop");
    	prop = new Properties();
    	prop.load(ip);//loads in key and value
    }
    	catch(FileNotFoundException e) {
    		e.printStackTrace();
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    	return prop;
    }
    public static String takeScreenshot() {
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		
		byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		String base64Path = Base64.getEncoder().encodeToString(buffer);
		
		return base64Path;
	}
}
