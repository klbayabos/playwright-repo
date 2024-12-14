
package com.sample.playwright.pages;

import org.junit.Assert;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.Locator;
import com.sample.playwright.steps.Utils;

public class AdminPage {
	private Page page;

	// Menu
	private final Locator adminMenu;
	private final Locator adminOrgStructureMenu;
	private final Locator adminOrgStructureAreasMenu;
	
	//Button
	private final Locator addNewRecord;
	private final Locator saveButton;
	private final Locator confirmYesButton;
	
	//Fields
	private final Locator nameField;
	private final Locator shortNameField;
	private final Locator exportCodeField;
	private Locator areaRecord;
	private Locator deleteRecord;

	public AdminPage(Page page) {
		this.page = page;
		this.adminMenu = page.locator("button#MenuItem_Button_Admin");
		this.adminOrgStructureMenu = page.locator("xpath=//span[text()='Org Structure']");
		this.adminOrgStructureAreasMenu = page.locator("a#MenuSubItem_Button_Areas");
		this.addNewRecord = page.locator("xpath=//a[text()='Add new record']");
		this.saveButton = page.locator("a >> text='Save'");
		this.confirmYesButton = page.locator("a#yesButton");
		this.nameField = page.locator("input#Name");
		this.shortNameField = page.locator("input#ShortName");
		this.exportCodeField = page.locator("input#ExportCode");
	}

	public void navigateToAreaPage(String areapageURL) {
		Utils.waitUntilElementIsVisible(adminMenu, 15);
		adminMenu.click();
		adminOrgStructureMenu.click();
		adminOrgStructureAreasMenu.click();
		page.waitForURL(areapageURL);
		Assert.assertEquals(page.url(), areapageURL);
		page.waitForLoadState(LoadState.LOAD);
		Utils.takeScreenshot(page, "Area Page");
	}
	
	public void addAreaRecord(String name, String shortName, String code) {
		Utils.waitUntilElementIsVisible(addNewRecord, 15);
		addNewRecord.click();
		Utils.waitUntilElementIsVisible(nameField, 15);
		nameField.fill(name);
		shortNameField.fill(shortName);
		exportCodeField.fill(code);
		Utils.takeScreenshot(page, "Area Record input");
		saveButton.click();
	}

	public void deleteAreaRecord(String name, String code) {
		String xpath = "//tr[td[text()='"+name+"']/following-sibling::td[text()='"+code+"']]//a[text()='Delete']";
		Utils.waitUntilElementIsVisible(getRecordFromList(name, code), 15);
		deleteRecord = page.locator(xpath);
		deleteRecord.first().click();
		confirmYesButton.click();
	}

	public void validateAreaRecord(String name, String shortName, String code) {
		page.waitForLoadState(LoadState.LOAD);
		Assert.assertTrue("Area Record containing "+name+" "+shortName+" "+code+" was not displayed", Utils.waitUntilElementIsVisible(getRecordFromList(name, code), 15));
		Utils.takeScreenshot(page, "Area Record containing "+name+" "+shortName+" "+code+" is displayed");
	}

	public void validateDeletedAreaRecord(String name,String code) {
		page.waitForLoadState(LoadState.LOAD);
		Assert.assertTrue("Area Record containing "+name+" "+code+" was still displayed",Utils.waitUntilElementIsNotVisible(getRecordFromList(name, code), 15));
		Utils.takeScreenshot(page, "Area Record containing "+name+" "+code+" is deleted");
	}

	public Locator getRecordFromList(String name, String code) {
		String xpath = "//tr[td[text()='"+name+"']/following-sibling::td[text()='"+code+"']]";
		areaRecord = page.locator(xpath);
		return areaRecord.first();
	}
}