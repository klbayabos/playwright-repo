
package com.sample.playwright.pages;

import org.junit.Assert;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.sample.playwright.steps.Utils;

public class LoginPage {
	private Page page;

	private final Locator usernameField;
	private final Locator passwordField;
	private final Locator loginButton;
	private final Locator unsuccessfulLoginErrorMsg;

	public LoginPage(Page page) {
		this.page = page;
		this.usernameField = page.locator("#UserName");
		this.passwordField = page.locator("#Password");
		this.loginButton = page.locator("button#btnSubmit");
		this.unsuccessfulLoginErrorMsg = page.locator("xpath=//li[text()='The Employee code/email and/or password is invalid.']");
	}

	public void loginUser(String username, String password) {
		usernameField.fill(username);
		passwordField.fill(password);
		Utils.takeScreenshot(page, "Login Credentials");
		loginButton.click();
	}
	
	public void verifyUnsuccessfulLoginMessage() {
		Assert.assertTrue(Utils.waitUntilElementIsVisible(unsuccessfulLoginErrorMsg, 15));
		Utils.takeScreenshot(page, "Unsuccessful Login Error Msg");
	}

}