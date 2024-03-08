package com.qa.utils;

import com.jayway.jsonpath.Filter;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public class RestClient {
	Filter logFilter = null;

	public Response get(RequestSpecBuilder requestBuilder) {

		final Response response = RestAssured.given(requestBuilder.build()).when().get();

		return response;
	}

	public Response post(RequestSpecBuilder requestBuilder) {

		final Response response = RestAssured.given(requestBuilder.build()).when().post();

		return response;
	}

	public Response put(RequestSpecBuilder requestBuilder) {

		final Response response = RestAssured.given(requestBuilder.build()).when().put();

		return response;
	}

	public Response delete(RequestSpecBuilder requestBuilder) {

		final Response response = RestAssured.given(requestBuilder.build()).when().delete();

		return response;
	}

	public Response patch(RequestSpecBuilder requestBuilder) {

		final Response response = RestAssured.given(requestBuilder.build()).when().patch();

		return response;
	}

}
