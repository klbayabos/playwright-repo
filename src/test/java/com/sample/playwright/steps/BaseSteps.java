package com.sample.playwright.steps;

import java.util.Arrays;

import org.junit.Assert;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.sample.playwright.pages.*;
import io.github.cdimascio.dotenv.Dotenv;

public class BaseSteps {
	protected Page page;
	protected Browser browser;
	protected HumanforcePage humanforcePage;
	protected LoginPage loginPage;
	protected HomePage homePage;
	protected AdminPage adminPage;
	protected String humanforceURL;
	protected String testTenantURL;
	protected String testTenantHomepageURL;
	protected String testTenantAreapageURL;
	protected String USERNAME;
	protected String PASSWORD;
	protected Dotenv dotenv;
    
	public BaseSteps() {
		setUpBrowserAndConfig();
	}

	private void setUpBrowserAndConfig() {
		// Setup Browser
		Playwright playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true).setArgs(Arrays.asList("--start-maximized")));
		page = browser.newPage();
		page.setDefaultTimeout(30000);
		page.setViewportSize(2560, 1440);
		
		// Setup Config
		dotenv = Dotenv.load();
		humanforceURL = dotenv.get("HUMANFORCE_URL");
		testTenantURL = dotenv.get("TEST_TENANT_URL");
		testTenantHomepageURL = dotenv.get("HOMEPAGE_URL");
		testTenantAreapageURL = dotenv.get("AREA_PAGE_URL");		
	}
	
	protected void setUpPages() {
		humanforcePage = new HumanforcePage(page);
		loginPage = new LoginPage(page);
		homePage = new HomePage(page);
		adminPage = new AdminPage(page);
	}
	
	protected void userSetup(String user) {
		String userRole="";
		switch(user) {
	        case "Employee": userRole = "EMPLOYEE_USER"; break;
	        case "Manager": userRole = "MANAGER_USER"; break;
	        case "Admin": userRole = "ADMIN_USER"; break;
	        default: Assert.fail("User '" + user + "' does not exist"); break;
	    }
		USERNAME = dotenv.get(userRole);
		PASSWORD = dotenv.get("PASSWORD");
	}
	
	protected void tearDown() {
		if (browser != null) browser.close();
	}
}
