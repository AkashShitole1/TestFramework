package com.qa.lenskart.stepdefinitions;

import com.qa.lenskart.pages.RestAssuredPage;
import com.qa.utils.ScenerioContext;

import io.cucumber.java.en.Given;

public class RestAssured extends BaseStepDefinations {
	
	public RestAssured(ScenerioContext scenarioContext) {
		super(scenarioContext);
		// TODO Auto-generated constructor stub
	}
	RestAssuredPage objRest = new RestAssuredPage();
	@Given("I get the auth token")
	public void getAuth() {
		objRest.getAuthToken();
	}
	
}