package com.qa.utils;

import java.util.Map;

import org.json.simple.JSONObject;

import com.jayway.jsonpath.Configuration;
import com.qa.constants.HttpMethod;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.config.HeaderConfig;
import io.restassured.http.ContentType;

public class RequestBuilder {
	private String httpMethod;

	public RequestSpecBuilder buildRequest(final String fileNameOrJsonString, ScenerioContext scenarioContext,
			boolean isFile) {
		JSONObject jsonObject = null;
		if (isFile) {
			String jsonString = JsonUtils.readJsonFile(fileNameOrJsonString);
			jsonObject = JsonUtils
					.getJsonObjectFromString(JsonUtils.updateTemplate(jsonString, scenarioContext.getData()));
		} else {
			jsonObject = JsonUtils
					.getJsonObjectFromString(JsonUtils.updateTemplate(fileNameOrJsonString, scenarioContext.getData()));
		}
		final RequestSpecBuilder builder = new RequestSpecBuilder();
		String value = "";
		if (null != jsonObject.get("baseUri")) {
			builder.setBaseUri(jsonObject.get("baseUri").toString());
		} else {
			if (null != jsonObject.get("gatekey")) {
				builder.setBaseUri("https://restful-booker.herokuapp.com");
			} else {
				builder.setBaseUri("https://restful-booker.herokuapp.com");
			}
		}

		// Check base path is set from scenario if not check is set from json file or
		// not.
		if (scenarioContext.getRequestBasePath() != null && !scenarioContext.getRequestBasePath().isEmpty()) {
			value = scenarioContext.getRequestBasePath();
		} else if (null != jsonObject.get("path")) {
			value = jsonObject.get("path").toString();
		}
		// If path value is not empty then check service is set in json file, then get
		// service version.
		if (!value.isEmpty()) {
			if (null != jsonObject.get("service")) {
				String version ="";
				value = value.replace("${version}", version);
			}
			builder.setBasePath(value);
		}

		if (null != jsonObject.get("port")) {
			builder.setPort(Integer.parseInt(jsonObject.get("port").toString()));
		}

		if (null != jsonObject.get("method")) {
			setHttpMethod(jsonObject.get("method").toString());
		}

		if (null != jsonObject.get("headers")) {
			Map<String, String> headersData = (Map<String, String>) jsonObject.get("headers");
			builder.addHeaders(headersData);

			if (headersData.containsKey("Content-Type")
					&& headersData.get("Content-Type").contains("multipart/mixed")) {
				builder.setConfig(RestAssured.config().encoderConfig(
						EncoderConfig.encoderConfig().encodeContentTypeAs("multipart/mixed", ContentType.TEXT)));
			}
		}

		// Add headers data added from scenario file
		if (null != scenarioContext.getHeaders()) {
			if (scenarioContext.getOverrideHeaders().size() > 0) {
				String[] headers = scenarioContext.getOverrideHeaders()
						.toArray(new String[scenarioContext.getOverrideHeaders().size()]);
				RestAssured.config = RestAssured.config()
						.headerConfig(HeaderConfig.headerConfig().overwriteHeadersWithName(headers[0], headers));
			}
			builder.addHeaders(scenarioContext.getHeaders());
		}

		// Add query parameter data added from request JSON file
		if (null != jsonObject.get("queryParams")) {
			if (HttpMethod.POST.getMethod().equalsIgnoreCase(getHttpMethod())) {
				builder.addQueryParams((Map<String, String>) jsonObject.get("queryParams"));
			} else {
				builder.addParams((Map<String, String>) jsonObject.get("queryParams"));
			}
		}

		// Add query parameter data added from scenario file
		if (null != scenarioContext.getQueryParams()) {
			if (HttpMethod.POST.getMethod().equalsIgnoreCase(getHttpMethod())) {
				builder.addQueryParams(scenarioContext.getQueryParams());
			} else {
				builder.addParams(scenarioContext.getQueryParams());
			}
		}

		if (null != jsonObject.get("formParams")) {
			builder.addFormParams((Map<String, String>) jsonObject.get("formParams"));
		}

		if (null != jsonObject.get("body")) {
			builder.setBody(jsonObject.get("body").toString());
		}
		return builder;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
}
