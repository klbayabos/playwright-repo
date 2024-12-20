package com.sample.playwright.testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/features",
		glue="com.sample.playwright.steps",
		plugin = {"pretty", "html:target/cucumber-reports.html"}
		//,tags = "@smoke"
)
public class TestRunner {

}