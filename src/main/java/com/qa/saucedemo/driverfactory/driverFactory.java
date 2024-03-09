package com.qa.saucedemo.driverfactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.Before;
import io.cucumber.messages.internal.com.google.protobuf.util.Durations;
import io.github.bonigarcia.wdm.WebDriverManager;

public enum driverFactory {
	INSTANCE;
    public Properties prop;
    
    public OptionsManager options;
    
    private final ThreadLocal<WebDriver> testContexts = new ThreadLocal<WebDriver>();

	public synchronized WebDriver getDriver() {
		return testContexts.get();
	}

	public synchronized void setDriver(WebDriver driver) {
		testContexts.set(driver);
	}
    
//     public WebDriver getDriver() {
//    	 return driver;
//    	 String driverName = initProperty().getProperty("driver"); 
//    	 System.out.println("driver is "+ driverName);
//    	 driver = initDriver(driverName);
//    	 return driver;
    	 
     

	/**
	 * this method is used to initialize the driver on the basis of given
	 * browserName.
	 * 
	 * @param prop
	 * @return this returns driver
	 */
	public synchronized void  initDriver(String identificationKey) {
		String driverName = initProperty().getProperty("driver"); 
		WebDriver driver = null;
		options = new OptionsManager(prop);
		
		if(driverName.equals("chrome")) {
	     WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options.getChromeOptions());
            
		}
		else if(driverName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(options.getFirefoxOptions());
		}
		else if(driverName.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
		}
		driver.manage().timeouts().pageLoadTimeout(90,TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        System.out.println(driver);
        setDriver(driver);
	}
	
	public Properties initProperty() {
		prop = new Properties();
		FileInputStream file = null;
		String envName = System.getProperty("env");
		//String envName = System.getenv("env");
		System.out.println("Running test cases on environment: " + envName);

		if (envName == null) {
			System.out.println("No env is given...hence running it on QA env by default....");
			try {
				file = new FileInputStream("./src/test/resources/com/qa/config/qa-config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

		else {
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					file = new FileInputStream("./src/test/resources/com/qa/config/qa-config.properties");
					break;
				case "prod":
					file = new FileInputStream("./src/test/resources/com/qa/config/prod-config.properties");
					break;

				default:
					System.out.println("Please pass the right environment.... " + envName);
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

		try {
			prop.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
		
	}

	

		

}
	
	

