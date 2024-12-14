
package com.sample.playwright.pages;

import org.junit.Assert;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.Locator;
import com.sample.playwright.steps.Utils;

public class HomePage {
	private Page page;

	private final Locator homepageHomeMenu;
	private final Locator homepageHeader;
	private final Locator homepageGreeting;

	public HomePage(Page page) {
		this.page = page;
		this.homepageHomeMenu = page.locator("a#MenuItem_Button_Home");
		this.homepageHeader = page.locator("hf-home-header");
		this.homepageGreeting = page.locator("span.home-header__info__name");
	}

	public void verifyHomePageRedirection(String homepageURL) {
		page.waitForURL(homepageURL);
		Assert.assertEquals(page.url(), homepageURL);
		page.waitForLoadState(LoadState.LOAD);
		Utils.takeScreenshot(page, "Homepage");
	}

	public void verifyHomePageContent() {
		Assert.assertTrue(Utils.waitUntilElementIsVisible(homepageHomeMenu, 15));
		Assert.assertTrue(Utils.waitUntilElementIsVisible(homepageHeader, 15));
		Utils.takeScreenshot(page, "Test Tenant Homepage");
	}

	public void verifyHomePageGreeting(String name) {
		Utils.waitUntilElementIsVisible(homepageGreeting, 15);
		String expectedGreeting = "Hello " + name;
		Assert.assertEquals("Test Tenant Homepage does not have any content", expectedGreeting, homepageGreeting.textContent());
		Utils.takeScreenshot(page, "Homepage Greeting");
	}
}