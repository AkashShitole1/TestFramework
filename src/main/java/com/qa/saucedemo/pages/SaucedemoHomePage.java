package com.qa.saucedemo.pages;

import org.openqa.selenium.By;

import com.qa.saucedemo.driverfactory.driverFactory;

public class SaucedemoHomePage extends BasePage{
	
	
	private By getInputTagById(String idName){return getBy("saucedemo.loginpage.username",idName);}
	private By loginpassword = getBy("saucedemo.loginpage.password");

	public void launchapp() {
		getURL(driverFactory.INSTANCE.initProperty().getProperty("appUrl"));
		
	}

	public void login(String username, String password) {
		enterText(getInputTagById("user-name"),username);
		enterText(loginpassword,password);
		waitAndClick(getInputTagById("login-button"));
		
	}

	public String verifyPageTitle() {
		return getPageTitle();
		
	}

}
