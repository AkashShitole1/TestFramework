package com.qa.lenskart.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src\\test\\java\\com\\qa\\lenskart\\features\\lenskart.feature"}, glue = {
		"com/qa/lenskart/stepdefinitions" }, plugin = { "pretty",
				"html:reports/htmlreports","json:reports/cucumber.json" }, monochrome = true, dryRun = false, 
				tags = "(@test1)"
				)

public class LocalRunner {

}
