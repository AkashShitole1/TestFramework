package com.qa.saucedemo.stepdefinitions;

import com.qa.saucedemo.pages.RestAssuredPage;
import com.qa.utils.ScenerioContext;

import io.cucumber.java.en.Given;
import lombok.extern.log4j.Log4j2;
@Log4j2
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
