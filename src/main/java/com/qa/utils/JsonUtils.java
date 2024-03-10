package com.qa.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.text.StringSubstitutor;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.parser.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.common.io.Files;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class JsonUtils {
	private ScenerioContext scenarioContext;

	public JsonUtils(ScenerioContext scenarioContext) {
		this.scenarioContext = scenarioContext;
	}

	public static String readJsonFile(final String fileName) {
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		File file = null;
		String jsonString = null;
		try {
			file = new File(loader.getResource(fileName).getFile());
			jsonString = FileUtils.readFileToString(file);
		} catch (FileNotFoundException excp) {

		} catch (IOException excp) {

		} catch (Exception excp) {

		}
		
		return jsonString;

	}

	public String getUpdatedJsonValueFromFile(final String requestFilePath,final String requestJsonPath) {
		String payloadJsonString = JsonUtils.getJsonStringFromFileByJsonPath(requestFilePath, requestJsonPath);
		return JsonUtils.updateVariableByValue(payloadJsonString,scenarioContext.getData());
	}
	
	public static String updateJsonObject(String jsonString, Map<String, Object> jsonPathValueMap,
            List<String> deleteJsonPathList) {
DocumentContext doc = JsonPath.parse(jsonString);
// Update jsonpath value
if (null != jsonPathValueMap) {
for (String key : jsonPathValueMap.keySet()) {
try {
doc = doc.set(key, jsonPathValueMap.get(key));
}catch(Exception excp) {
}
}
}

// Delete json path
if (null != deleteJsonPathList) {
for (String jsonPath : deleteJsonPathList) {
try {
doc = doc.delete(jsonPath);
} catch (Exception excp) {
}
}
}
return doc.jsonString();
}


	public static JSONObject convertStringtoJsonObject(String requestString) {
		JSONParser parse = new JSONParser();
		JSONObject jsonObj = null;
		
		try {
			jsonObj = (JSONObject)parse.parse(requestString);
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
	
		return jsonObj;
	}

	public static String updateVariableByValue(String variable, Map<String, Object> data) {
		System.out.println("in the update variable by value");
		StringSubstitutor stringSubstitutor = new StringSubstitutor(data);
		return stringSubstitutor.replace(variable);
		
	}
	public static String getJsonStringFromFileByJsonPath(final String requestFilePath,final String requestJsonPath) {
        String requestString = readJsonFile(requestFilePath);
		
		JSONParser parse = new JSONParser();
		JSONObject jsonObj = null;
		
		try {
			jsonObj = (JSONObject)parse.parse(requestString);
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
	    
		return jsonObj.get(requestJsonPath).toString();
		
	}
	public static String updateTemplate(final String templateContent, Map<String, Object> requestData) {
        StringSubstitutor stringSubstitutor = new StringSubstitutor(requestData);
        String updatedTemplate = stringSubstitutor.replace(templateContent);
        
        stringSubstitutor = new StringSubstitutor(requestData, "\"${", ".numeric}\"");
        return stringSubstitutor.replace(updatedTemplate);
    }


	
}
