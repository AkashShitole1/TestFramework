package com.qa.utils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
@Log4j2
public class ScenerioContext {
	
	
	private Map<String, Object> data;
	private Response response;
	private Map<String, Object> reqJsonPathDetails = null;
	private List<String> deleteReqJsonPathList = null;

    public ScenerioContext(){
    	data = new HashMap<>();    
    	reqJsonPathDetails = new HashMap<String, Object>();
		deleteReqJsonPathList = new ArrayList<String>();
    }
    
    public <T> T get(String name) {
		return (T) data.get(name);
	}

	public <T> void set(String name, T object) {
		data.put(name, object);
	}
	public Map<String, Object> getData() {
		return data;
	}
	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}
	
	public Map<String, Object> getReqJsonPathDetails() {
		return reqJsonPathDetails;
	}
	public List<String> getDeleteReqJsonPathList() {
		return deleteReqJsonPathList;
	}
	public void clearStepContextData() {
		
		reqJsonPathDetails.clear();
		deleteReqJsonPathList.clear();
		
	}

    

}
