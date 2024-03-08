package com.qa.lenskart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qa.lenskart.driverfactory.driverFactory;




public class lenskartHomePage extends BasePage{
	
	
	public void launchURL() {
		getURL(driverFactory.INSTANCE.initProperty().getProperty("appUrl"));
		System.out.println("app is launched");
		quit();
	}

	public void moveLoginPage() {
		System.out.println("moved to login page");
		
	}

	public void login() {
		
		
	}

}
