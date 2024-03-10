package com.qa.saucedemo.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src\\test\\java\\com\\qa\\saucedemo\\features"}, glue = {
		"com/qa/saucedemo/stepdefinitions","com/qa/hooks" }, plugin = { "pretty",
				"html:reports/cucumber-default-report/cucumber.html", "json:reports/cucumber.json", "junit:reports/cucumber.xml" }, monochrome = true, dryRun = false, 
				tags = "(@Test-CreateBooking)"
				)

public class LocalRunner {

}
