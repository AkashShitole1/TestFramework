package com.qa.lenskart.stepdefinitions;

import java.util.List;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;
import org.apache.logging.log4j.ThreadContext;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.qa.constants.HttpMethod;
import com.qa.utils.JsonUtils;
import com.qa.utils.RequestBuilder;
import com.qa.utils.RestClient;
import com.qa.utils.ScenerioContext;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public class BaseStepDefinations  {
	protected ScenerioContext scenarioContext = null;
    JsonUtils stepUtil;

	public BaseStepDefinations(ScenerioContext scenarioContext) {
		this.scenarioContext = scenarioContext;
	}

	public void callApiUsingFile(String requestFilePath, final String requestJsonPath) {
		System.out.println(requestFilePath);
		System.out.println("check");
		final String requestDetails = stepUtil.getUpdatedJsonValueFromFile(requestFilePath, requestJsonPath);
		System.out.println("");
		callSerivceUsingRequest(requestDetails);

	}

	public void callSerivceUsingRequest(final String requestDetails) {
		RequestBuilder request = new RequestBuilder();
		RequestSpecBuilder requestSpecBuilder = request.buildRequest(requestDetails, scenarioContext, false);
		final Response response = executeApiCall(request.getHttpMethod(), requestSpecBuilder);
		scenarioContext.setResponse(response);
	}

	public Response executeApiCall(String httpMethod, RequestSpecBuilder requestSpecBuilder) {
		ThreadContext.put("logFileName", scenarioContext.getScenarioLogFileName());
		Response response = null;
		RestClient restClient = new RestClient();
		if (HttpMethod.GET.getMethod().equalsIgnoreCase(httpMethod)) {
			response = restClient.get(requestSpecBuilder);
		} else if (HttpMethod.POST.getMethod().equalsIgnoreCase(httpMethod)) {
			response = restClient.post(requestSpecBuilder);
		} else if (HttpMethod.PUT.getMethod().equalsIgnoreCase(httpMethod)) {
			response = restClient.put(requestSpecBuilder);
		} else if (HttpMethod.DELETE.getMethod().equalsIgnoreCase(httpMethod)) {
			response = restClient.delete(requestSpecBuilder);
		} else if (HttpMethod.PATCH.getMethod().equalsIgnoreCase(httpMethod)) {
			response = restClient.patch(requestSpecBuilder);
		}
		return response;
	}
	
	

}
