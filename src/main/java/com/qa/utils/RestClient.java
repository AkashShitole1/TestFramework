package com.qa.utils;

import com.jayway.jsonpath.Filter;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
@Log4j2
public class RestClient {

	public Response get(RequestSpecBuilder reqSpecBuilder) {
		final Response response = RestAssured.given(reqSpecBuilder.build()).when().get();
		return response;
	}

	public Response post(RequestSpecBuilder reqSpecBuilder) {
		final Response response = RestAssured.given(reqSpecBuilder.build()).when().post();
		return response;
	}

	public Response put(RequestSpecBuilder reqSpecBuilder) {
		Response response = RestAssured.given(reqSpecBuilder.build()).when().post();
		return response;
	}

	public Response delete(RequestSpecBuilder reqSpecBuilder) {
		Response response = RestAssured.given(reqSpecBuilder.build()).when().delete();
        return response;
	}

	public Response patch(RequestSpecBuilder reqSpecBuilder) {
		Response response = RestAssured.given(reqSpecBuilder.build()).when().patch();
		return response;
		
	}
	

}
