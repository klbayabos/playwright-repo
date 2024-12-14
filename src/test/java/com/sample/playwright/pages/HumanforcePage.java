
package com.sample.playwright.pages;

import org.junit.Assert;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.sample.playwright.steps.Utils;


public class HumanforcePage {
	private Page page;

	private final Locator wfmTimeAndAttendance;
	private final Locator helpfulResources;
	private final Locator articleContent;
	private Locator article;

	public HumanforcePage(Page page) {
		this.page = page;
		this.wfmTimeAndAttendance = page.locator("role=link[name='Time & Attendance']");
		this.helpfulResources = page.locator("role=heading[name='Helpful resources']");
		this.articleContent = page.locator("div.o-container").filter(new Locator.FilterOptions().setHasText("What is workforce analytics"));		
	}

	public void clickTimeAndAttendance() {
		wfmTimeAndAttendance.scrollIntoViewIfNeeded();
		wfmTimeAndAttendance.click();
	}

	public void verifyListOfResources() {
		Assert.assertTrue("List of Helpful Resources are not displayed", Utils.waitUntilElementIsVisible(helpfulResources, 15));
		helpfulResources.scrollIntoViewIfNeeded();
		Utils.takeScreenshot(page, "Helpful Resources");
	}
	
	// Open an article with article's name as the parameter
	public void openArticle(String articleName) {
		getArticleElement(articleName).click();
	}
	
	// Validate article content is not empty
	public void validateArticle(String articleName) {
		Assert.assertTrue("Article page did not load", Utils.waitUntilElementIsVisible(getArticleElement(articleName), 15));
		Assert.assertNotNull(articleName + " article does not have any content", articleContent.textContent());
		Utils.takeScreenshot(page, articleName + " article is displayed");
	}

	public Locator getArticleElement(String articleName) {
		String articleXpath = "xpath=//span[text()='" + articleName + "']";
		article = page.locator(articleXpath);
		article.scrollIntoViewIfNeeded();
		return article;
	}

}