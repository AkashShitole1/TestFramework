package com.qa.saucedemo.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/java/com/qa/saucedemo/features"}, glue = {
		"com/qa/saucedemo/stepdefinitions","com/qa/hooks" }, plugin = { "pretty",
				"html:target/cucumber-default-report/cucumber.html", "json:target/cucumber.json", "json:target/cucumber.json" }, monochrome = true, dryRun = false, 
				tags = "(@test1)"
				)

public class LocalRunner {

}
