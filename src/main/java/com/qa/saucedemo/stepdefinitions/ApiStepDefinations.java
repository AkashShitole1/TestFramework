package com.qa.saucedemo.stepdefinitions;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.qa.utils.ScenerioContext;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import junit.framework.Assert;
import lombok.extern.log4j.Log4j2;
@Log4j2
public class ApiStepDefinations extends BaseStepDefinations {
	public ApiStepDefinations(ScenerioContext scenarioContext) {
		super(scenarioContext);
		// TODO Auto-generated constructor stub
	}

	private String fileName;

	

	@And("I call {string} api using file {string}")
	public void i_call_api_using_file(String apiDetails, String requestDetailsFilePath) {
		this.fileName = requestDetailsFilePath;
		callApiUsingFile(requestDetailsFilePath, "request");

	}

	@Then("The response status should be {int}")
	public void the_response_status_should_be(int int1) {
       Assert.assertEquals(scenarioContext.getResponse().getStatusCode(), int1); 
	}
	
	@Then("I store response fields value to below variables")
	public void assignValues(DataTable data) {
		Map<String, String> variables = data.asMaps().get(0);
		storeJsonPathValueFromResponseIntoVariable(variables);
		
	}

	private void storeJsonPathValueFromResponseIntoVariable(final Map<String, String> data) {
		data.entrySet().forEach(e -> {
			System.out.println("value is" + e.getValue()+" key is"+e.getKey());
			storeJsonPathValueFromResponseIntoVariable(e.getValue(), e.getKey());
		});
		
	}

	private void storeJsonPathValueFromResponseIntoVariable(String jsonPath, String variableName) {
		io.restassured.path.json.JsonPath json = scenarioContext.getResponse().jsonPath();
		
		String value = json.get(jsonPath).toString();
		scenarioContext.set(variableName, value);
		
	}
	
	@Then("I print the value of {string}")
	public void getValueFromContext(String variable) {
		
		if(variable.contains("${")) {
			stepUtil.updateVariableByValue(variable, scenarioContext.getData());
		}
		
	}

}
