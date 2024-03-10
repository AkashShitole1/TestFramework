package com.qa.saucedemo.stepdefinitions;

import java.util.List;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;
import org.apache.logging.log4j.ThreadContext;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.github.dockerjava.transport.DockerHttpClient.Request;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.qa.constants.HttpMethod;
import com.qa.utils.JsonUtils;
import com.qa.utils.RequestBuilder;
import com.qa.utils.RestClient;
import com.qa.utils.ScenerioContext;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
@Log4j2
public class BaseStepDefinations  {
	protected JsonUtils stepUtil;
	protected ScenerioContext scenarioContext = null;
   

	public BaseStepDefinations(ScenerioContext scenarioContext) {
		this.scenarioContext = scenarioContext;
		stepUtil = new JsonUtils(scenarioContext);
	}

	public void callApiUsingFile(String requestFilePath, final String requestJsonPath) {
		scenarioContext.set("accessToken", "akash");
final String requestDetails = stepUtil.getUpdatedJsonValueFromFile(requestFilePath, requestJsonPath);

         System.out.println("request details is" + requestDetails);
		callApiService(requestDetails);

	}
	public void callApiService(String requestDetails) {
		RequestBuilder builder = new RequestBuilder();
		RequestSpecBuilder reqSpecBuilder = builder.buildRequest(requestDetails);
		final Response response = executeApi(builder.getHttpMethod(),reqSpecBuilder);
		System.out.println("API is executed and the response is "+response.toString());
		scenarioContext.setResponse(response);
		
		
	}

	private Response executeApi(String httpMethod, RequestSpecBuilder reqSpecBuilder) {
		Response response = null;
        RestClient restClient = new RestClient();
        if (HttpMethod.GET.getMethod().equalsIgnoreCase(httpMethod)) {
            response = restClient.get(reqSpecBuilder);
        } else if (HttpMethod.POST.getMethod().equalsIgnoreCase(httpMethod)) {
            response = restClient.post(reqSpecBuilder);
        } else if (HttpMethod.PUT.getMethod().equalsIgnoreCase(httpMethod)) {
            response = restClient.put(reqSpecBuilder);
        } else if (HttpMethod.DELETE.getMethod().equalsIgnoreCase(httpMethod)) {
            response = restClient.delete(reqSpecBuilder);
        } else if (HttpMethod.PATCH.getMethod().equalsIgnoreCase(httpMethod)) {
            response = restClient.patch(reqSpecBuilder);
        }
        return response;
	}


}
