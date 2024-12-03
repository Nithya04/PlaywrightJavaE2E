package com.qa.opencart.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.nio.file.Paths;

public class ScreenShotUtils {

    /**
     * Captures a screenshot of the current page.
     *
     * @param page     The Playwright Page object
     * @param filePath The path where the screenshot will be saved
     */
    public static void captureScreenshot(Page page) {
        if (page == null) {
            throw new IllegalArgumentException("Page object cannot be null");
        }
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("./snaps/scr.png")));
     
    }

    /**
     * Captures a full-page screenshot of the current page.
     *
     * @param page     The Playwright Page object
     * @param filePath The path where the full-page screenshot will be saved
     */
    public static void captureFullPageScreenshot(Page page) {
        if (page == null) {
            throw new IllegalArgumentException("Page object cannot be null");
        }
        page.screenshot(new Page.ScreenshotOptions().setFullPage(true).setPath(Paths.get("./snaps/fullpage.jpg")));
     
    }
    
    public static void captureLocatorScreenshot(Locator locator) {
        if (locator == null) {
            throw new IllegalArgumentException("Locator object cannot be null");
        }
        locator.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("./snaps/locator.jpg")));
    }
}
 