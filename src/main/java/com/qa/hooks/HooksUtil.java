package com.qa.hooks;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.qa.saucedemo.driverfactory.driverFactory;
import com.qa.utils.ScenerioContext;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class HooksUtil {
	
	@Before
	public void setupDriver() {
		System.out.println("Run Before Scenario");
		driverFactory.INSTANCE.initDriver(driverFactory.INSTANCE.initProperty().getProperty("driver"));
	}
	
	@After
	public void closeDriver(){
		driverFactory.INSTANCE.getDriver().quit();;
		
		
	}

}
