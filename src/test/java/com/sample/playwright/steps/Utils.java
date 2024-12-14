
package com.sample.playwright.steps;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Utils {
	private static final String SCREENSHOTS_PATH = "target/screenshots/";

	public static void takeScreenshot(Page page, String name) {
    	try {
    		java.nio.file.Files.createDirectories(Paths.get(SCREENSHOTS_PATH));
    		String screenshotName = timeStamp() + "_" +name.replace(":", "") + ".png";
    		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(SCREENSHOTS_PATH, screenshotName)));
    		System.out.println("Screenshot saved: " + SCREENSHOTS_PATH + screenshotName);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	public static String timeStamp() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
	}
	
	public static boolean waitUntilElementIsVisible(Locator element, int timeoutSec) {
		boolean isElementVisible = element.isVisible();
		int retry = 0;
		while (!isElementVisible && retry < timeoutSec) {
			try {
				Thread.sleep(1000);
				isElementVisible = element.isVisible();
				retry++;
            } catch (Exception e) {
            	System.out.println("After " + timeoutSec + " seconds, " + element + " was not visible.");
            }
		}
		return isElementVisible;
	}
	
	public static boolean waitUntilElementIsNotVisible(Locator element, int timeoutSec) {
		boolean isElementVisible = element.isVisible();
		int retry = 0;
		while (isElementVisible && retry < timeoutSec) {
			try {
				Thread.sleep(1000);
				isElementVisible = element.isVisible();
				retry++;
            } catch (Exception e) {
            	System.out.println("After " + timeoutSec + " seconds, " + element + " was still visible.");
            }
		}
		return !isElementVisible;
	}
}