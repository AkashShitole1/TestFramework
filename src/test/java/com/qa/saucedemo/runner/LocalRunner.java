package com.qa.saucedemo.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src\\test\\java\\com\\qa\\lenskart\\features"}, glue = {
		"com/qa/saucedemo/stepdefinitions" }, plugin = { "pretty",
				"html:reports/htmlreports","json:reports/cucumber.json" }, monochrome = true, dryRun = false, 
				tags = "(@test1)"
				)

public class LocalRunner {

}
