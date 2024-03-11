package com.qa.saucedemo.driverfactory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	private Properties prop;
	private ChromeOptions chrome;
	private FirefoxOptions firefox;
	
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		chrome = new ChromeOptions(); 
		chrome.addArguments("--remote-allow-origins=*");
		if(Boolean.parseBoolean(prop.getProperty("headless"))) chrome.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) chrome.addArguments("--incognito");
		
		return chrome;
	}
	public FirefoxOptions getFirefoxOptions() {
		firefox = new FirefoxOptions(); 
		if(Boolean.parseBoolean(prop.getProperty("headless"))) firefox.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) firefox.addArguments("--incognito");
		
		return firefox;
	}
	

}
