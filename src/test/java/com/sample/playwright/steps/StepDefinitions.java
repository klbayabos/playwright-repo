
package com.sample.playwright.steps;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.*;

public class StepDefinitions extends BaseSteps {
	
	@Before
    public void setUpTest(){
		setUpPages();
    }

    @After
    public void tearDownTest(){
    	tearDown();
    }
    
	/***** HUMANFORCE ****/
    
	@Given("viewer is on the homepage of the Humanforce page")
	public void userNavigatesToHumanForcePage() {
		page.navigate(humanforceURL);
	}
	
	@When("viewer clicks the Time and Attendance link")
	public void userClicksTimeAndAttendance() {
		humanforcePage.clickTimeAndAttendance();
	}
	
	@Then("viewer should see a list of helpful resources")
	public void userCanSeeListOfResources() {
		humanforcePage.verifyListOfResources();
	}

	@And("viewer selects the {string} article")
	public void userClicksArticle(String article) {
		humanforcePage.openArticle(article);
	}

	@Then("viewer reads the content of the article {string}")
	public void userCanSeeListOfResources(String article) {
		humanforcePage.validateArticle(article);
	}

	/***** TEST TENANT LOGIN ****/

	@Given("the user navigates to test tenant")
	public void userNavigatesToPage() {
		page.navigate(testTenantURL);
	}
	
	@Given("the user is logged in to test tenant as {string}")
	public void userLoginToTestTenant(String user) {
		userNavigatesToPage();
		userLogin(user);
	}

	@When("the user logins as {string}")
	public void userLogin(String user) {
		userSetup(user);
		loginPage.loginUser(USERNAME, PASSWORD);
	}

	@When("the user logins with username {string} and password {string}")
	public void userLogin(String username, String password) {
		loginPage.loginUser(username, password);
	}
	
	@Then("the user should see unsuccessful error message")
	public void unsuccessfulLoginErrorMessage() {
		loginPage.verifyUnsuccessfulLoginMessage();
	}

	@Then("the user should be redirected to the homepage")
	public void userRedirectedToHomePage() {
		homePage.verifyHomePageRedirection(testTenantHomepageURL);
	}

	@And("the user should be see the homepage content")
	public void userCanSeeHomePageContent() {
		homePage.verifyHomePageContent();
	}
	
	@And("the user should be see the homepage greeting his name {string}")
	public void userCanSeeHomePageContent(String greetingName) {
		homePage.verifyHomePageGreeting(greetingName);
	}

	/***** TEST TENANT ADMIN ****/
	
	@When("the user navigates to Area page")
	public void userNavigateToAreaPage() {
		adminPage.navigateToAreaPage(testTenantAreapageURL);
	}
	
	@And("the user creates a new area with name {string} shortname {string} exportcode {string}")
	public void deleteArea(String name, String shortName, String code)  {
		adminPage.addAreaRecord(name, shortName, code);
	}

	@Then("the user should see the name {string} shortname {string} exportcode {string} in the list of Areas")
	@And("the user should also see the name {string} shortname {string} exportcode {string} in the list of Areas")
	public void verifyArea(String name, String shortName, String code)  {
		adminPage.validateAreaRecord(name, shortName, code);
	}

	@When("the user deletes area with name {string} exportcode {string}")
	public void deleteArea(String name, String code)  {
		adminPage.deleteAreaRecord(name, code);
	}

	@Then("the area with name {string} exportcode {string} should not display in the list of Areas")
	@And("the area with name {string} exportcode {string} should also not display in the list of Areas")
	public void validateDeletedAreaRecord(String name, String code)  {
		adminPage.validateDeletedAreaRecord(name, code);
	}
	
}
