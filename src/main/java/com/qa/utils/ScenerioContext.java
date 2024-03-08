package com.qa.utils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;

import io.restassured.response.Response;

public class ScenerioContext {
	private String scenarioName;
	// Scenario key/test case id/QAGP Id
	private String testCaseId;
	private String accessToken;
	private String baseUri;
	private List<String> overrideHeaders;

	private Map<String, Object> data;
	private Map<String, Object> queryParams;
	private Map<String, String> headers;

	protected final long uniqueId;
	private Map<String, Object> reqJsonPathDetails = null;
	private List<String> deleteReqJsonPathList = null;
	private String requestBasePath;

	private String scenarioLogFileName;
	private Response response;

	public <T> void set(String name, T object) {
		data.put(name, object);
	}

	public <T> T get(String name) {
		return (T) data.get(name);
	}

	public ScenerioContext() {
		data = new HashMap<>();
		queryParams = new HashMap<>();
		headers = new HashMap<>();

		reqJsonPathDetails = new HashMap<String, Object>();
		deleteReqJsonPathList = new ArrayList<String>();
		uniqueId = getUniqueID();
		set("uniqueId", uniqueId);
	}

	private synchronized long getUniqueID() {
		try {
			// Waiting randomly for 100 to 1000 milliseconds before generating unique id
			Thread.sleep(RandomUtils.nextInt(100, 1000));
		} catch (InterruptedException excp) {
			excp.printStackTrace();
		}
		Date date = new Date(uniqueId);
		return date.getTime();
	}

	public void addReqJsonPathDetail(final String jsonPath, final Object value) {
		reqJsonPathDetails.put(jsonPath, value);
	}

	public List<String> getDeleteReqJsonPathList() {
		return deleteReqJsonPathList;
	}

	public Map<String, Object> getReqJsonPathDetails() {
		return reqJsonPathDetails;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public String getScenarioLogFileName() {
		return scenarioLogFileName;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void addHeader(final String header, final String value) {
		headers.put(header, value);
	}

	public void setOverrideHeader(String header) {
		overrideHeaders.add(header);
	}

	public List<String> getOverrideHeaders() {
		return overrideHeaders;
	}

	public void clearHeaders() {
		headers.clear();
	}

	public Map<String, Object> getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(final String parameter, final String parameterValue) {
		queryParams.put(parameter, parameterValue);
	}

	public void clearQueryParams() {
		queryParams.clear();
	}

	public String getRequestBasePath() {
		return requestBasePath;
	}

	public void setRequestBasePath(String requestBasePath) {
		this.requestBasePath = requestBasePath;
	}

}
