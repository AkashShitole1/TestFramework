package com.qa.saucedemo.pages;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import lombok.extern.log4j.Log4j2;

import static io.restassured.RestAssured.*;
@Log4j2
public class RestAssuredPage {

	public void getAuthToken() {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		String response = given().log().all().body("{\r\n"
				+ "\"username\":\"admin\",\r\n"
				+ "\"password\":\"password123\"\r\n"
				+ "\r\n"
				+ "}").header("Content-Type","application/json")
		.when().post("/auth")
		.then().log().all().statusCode(200).extract().asString();
		
	   JsonPath js = new JsonPath(response);
	   String token = js.getString("token");
	   System.out.println("token is "+ token );
	   
	   
	   given().log().all().auth().oauth2(token)
	   .when().get("/booking")
	   .then().log().all().statusCode(200);
	   
	}

}
