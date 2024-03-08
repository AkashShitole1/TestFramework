package com.qa.saucedemo.stepdefinitions;

import org.junit.Assert;

import com.qa.saucedemo.pages.SaucedemoHomePage;
import com.qa.utils.ScenerioContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SauceDemo extends BaseStepDefinations{
	
	
	public SauceDemo(ScenerioContext scenarioContext) {
		super(scenarioContext);
	}

	SaucedemoHomePage objHome=new SaucedemoHomePage();

	@Given("user launch the saucedemo application[UI]")
	public void launchApplication(){
		objHome.launchapp();
		
	}
	
	@When("{string} logged into the application with {string}[UI]")
	public void login(String username,String password) {
		objHome.login(username,password);
	}
	
	@Then("I verify page title should be {string}[UI]")
	public void verifyPageTitle(String title) {
		Assert.assertEquals("Page title is not verified",title, objHome.verifyPageTitle());
	}

}
