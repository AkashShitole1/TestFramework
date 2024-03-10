package com.qa.utils;

import java.util.Map;

import org.json.simple.JSONObject;

import com.jayway.jsonpath.Configuration;
import com.qa.constants.HttpMethod;
import com.qa.saucedemo.driverfactory.driverFactory;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.config.HeaderConfig;
import io.restassured.http.ContentType;
import lombok.extern.log4j.Log4j2;
@Log4j2
public class RequestBuilder {
    private String httpMethod;
	public RequestSpecBuilder buildRequest(String requestDetails) {
		JSONObject jsonobj=null;
		jsonobj=JsonUtils.convertStringtoJsonObject(requestDetails);
		final RequestSpecBuilder builder = new RequestSpecBuilder();
		if (null != jsonobj.get("baseUri")) {
			builder.setBaseUri(jsonobj.get("baseUri").toString());
		}
		else {
		builder.setBaseUri(driverFactory.INSTANCE.initProperty().getProperty("baseURI"));
		}
		if (null != jsonobj.get("path")) {
		builder.setBasePath( jsonobj.get("path").toString());
		}
		setHttpMethod(jsonobj.get("method").toString());
		
		if (null != jsonobj.get("headers")) {
			Map<String, String> headersData = (Map<String, String>) jsonobj.get("headers");
			builder.addHeaders(headersData);
		}
		
		
		            if (null != jsonobj.get("queryParams")) {
		 			if (HttpMethod.POST.getMethod().equalsIgnoreCase(getHttpMethod())) {
						builder.addQueryParams((Map<String, String>) jsonobj.get("queryParams"));
					} else {
						builder.addParams((Map<String, String>) jsonobj.get("queryParams"));
					}
				}
		            
		            if (null != jsonobj.get("body")) {
		    			builder.setBody(jsonobj.get("body").toString());
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
