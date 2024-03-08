package com.qa.lenskart.stepdefinitions;

import com.qa.utils.ScenerioContext;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class ApiStepDefinations extends BaseStepDefinations {
	public ApiStepDefinations(ScenerioContext scenarioContext) {
		super(scenarioContext);
		// TODO Auto-generated constructor stub
	}

	private String fileName;

	

	@And("I call {string} api using file {string}")
	public void i_call_api_using_file(String apiDetails, String requestDetailsFilePath) {
		this.fileName = requestDetailsFilePath;
		System.out.println("akash");
		callApiUsingFile(requestDetailsFilePath, "request");

	}

	@Then("The response status should be {int}")
	public void the_response_status_should_be(Integer int1) {

	}

}
