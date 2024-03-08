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
import org.apache.logging.log4j.core.parser.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.common.io.Files;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;



public class JsonUtils {
	private ScenerioContext scenarioContext;

	public JsonUtils(ScenerioContext scenarioContext) {
		this.scenarioContext = scenarioContext;
	}

	public static String readJsonFile(final String fileName) {
		System.out.println("inside read json");
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

	public String getUpdatedJsonValueFromFile(final String filePath,final String jsonPath) {
		System.out.println("check2");
		String payloadJsonString = getJsonStringFromFileByJsonPath(filePath, jsonPath);
		
		payloadJsonString = updateJsonObject(payloadJsonString, scenarioContext.getReqJsonPathDetails(),
				scenarioContext.getDeleteReqJsonPathList());
		System.out.println(payloadJsonString);
		return updateTemplate(payloadJsonString, scenarioContext.getData());
	}

	public static JSONObject getJsonObjectFromString(final String jsonString) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) parser.parse(jsonString);
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

	public String getJsonStringFromFileByJsonPath(String requestFilePath, final String requestJsonPath) {
		System.out.println("inside getJsonStringFromFileByJsonPath ");
		String jsonString = JsonUtils.readJsonFile(requestFilePath);
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;

		try {
			Object obj = new JSONParser().parse(jsonString);

			// typecasting obj to JSONObject
			jsonObject = (JSONObject) obj;

		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonObject.get(requestJsonPath).toString());
		return jsonObject.get(requestJsonPath).toString();
	}

	public static String updateJsonObject(String jsonString, Map<String, Object> jsonPathValueMap,
			List<String> deleteJsonPathList) {
		DocumentContext doc = JsonPath.parse(jsonString);
// Update jsonpath value
		if (null != jsonPathValueMap) {
			for (String key : jsonPathValueMap.keySet()) {
				try {
					doc = doc.set(key, jsonPathValueMap.get(key));
				} catch (Exception excp) {
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

	public static String updateTemplate(final String templateContent, Map<String, Object> requestData) {
		// Replace string variables data. Double quote will remains as it is.
		StringSubstitutor stringSubstitutor = new StringSubstitutor(requestData);
		String updatedTemplate = stringSubstitutor.replace(templateContent);
		// Replace numeric variables data. Variable along with double quote get
		// replaces.
		stringSubstitutor = new StringSubstitutor(requestData, "\"${", ".numeric}\"");
		return stringSubstitutor.replace(updatedTemplate);
	}
	
}
