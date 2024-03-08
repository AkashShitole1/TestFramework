package com.qa.lenskart.stepdefinitions;

import com.qa.lenskart.pages.lenskartEyeWearPage;
import com.qa.lenskart.pages.lenskartHomePage;
import com.qa.utils.ScenerioContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class lenskart extends BaseStepDefinations{
	
	
	public lenskart(ScenerioContext scenarioContext) {
		super(scenarioContext);
		// TODO Auto-generated constructor stub
	}
	lenskartEyeWearPage objEye=new lenskartEyeWearPage();
	lenskartHomePage objHome=new lenskartHomePage();

	@Given("I launch the application url")
	public void i_launch_the_application_url() {
	    objHome.launchURL();
         
	}
    @When("I move to login page")
	public void i_move_to_login_page() {
	    objHome.moveLoginPage();
	}
	@Then("{string} logged in to the application")
	public void logged_in_to_the_application(String string) {
	    objHome.login();
	}
	@Then("I go to EyeWear Section")
	public void i_go_to_eye_wear_section() {
	    objEye.moveToSection();
	}
	@Then("I select rectangular frame")
	public void i_select_rectangular_frame() {
	    objEye.selectFrameType();
	}

}
