package com.qa.saucedemo.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.saucedemo.driverfactory.driverFactory;

import io.cucumber.java.Before;

public class BasePage {
	
	public WebDriver driver() {
		return driverFactory.INSTANCE.getDriver();
	}
    
	public WebDriverWait getExplicitWait() {
		return new WebDriverWait(driver(), Duration.ofSeconds(90));
	}

	private FluentWait<WebDriver> getFluentWait(int... waitTimeInSeconds) {
		int timeout = waitTimeInSeconds.length > 0 ? waitTimeInSeconds[0] : 90;
		return new FluentWait<WebDriver>(driver()).pollingEvery(Duration.ofSeconds(2))
				.withTimeout(Duration.ofSeconds(timeout)).ignoring(StaleElementReferenceException.class)
				.ignoring(NoSuchElementException.class).ignoring(InterruptedException.class);
	}

	/**
	 * Wait for nested/child elements to be displayed from given parent element.
	 *
	 * @param parentBy By to find parent element
	 * @param childBy  By to check visibility for child element
	 * @return true if nested/child element is displayed otherwise false
	 */
	
	  public Boolean waitForChildElementToBeDisplayed(By parentBy , By childBy) {
		  try {
			  getFluentWait().until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(parentBy, childBy));
			  return true;
		  }
		  catch(Exception e){
			  System.out.println("Nested Element is not displayed");
			  return false;
		  }
	  }
	  
	  /**
		 * Wait for nested/child elements to be displayed from given parent element.
		 *
		 * @param Parent element to find child elements
		 * @param childBy  By to check visibility for child element
		 * @return true if nested/child element is displayed otherwise false
		 */
		
		  public Boolean waitForChildElementsToBeDisplayed( WebElement parentElement,  By childElementBy) {
			  try {
				  getFluentWait().until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(parentElement, childElementBy));
				  return true;
			  }
			  catch(Exception e){
				  System.out.println("Nested Element is not displayed");
				  return false;
			  }
		  }
	  
	 public Boolean waitForElementToBeVisible(By by) {
		 try {
			 getFluentWait().until(ExpectedConditions.visibilityOf(driver().findElement(by)));
			 return true;
		 }
		 catch(Exception e) {
			 System.out.println("Element is not displayed"); 
			 return false;
		 }
	 }
		  
	
	 public WebElement findElement(By by) {
		WebElement element = getFluentWait().until(ExpectedConditions.visibilityOf(driver().findElement(by)));
		return element;
	}

	public List<WebElement> findElements(By by) {
		List<WebElement> elements = getFluentWait()
				.until(ExpectedConditions.visibilityOfAllElements(driver().findElements(by)));
		return elements;
	}

	public WebElement findElementWithText(By by, String text) {
		List<WebElement> elements = findElements(by);
		for (WebElement element : elements) {
			if (element.getText().equalsIgnoreCase(text))
				return element;
		}
		throw new NoSuchElementException(
				String.format("Could not found element with text equals to %s by locator %s", text, by.toString()));

	}
	public void getURL(String url) {
		driver().get(url);
	}

	public void waitAndClick(By by) {
		WebElement element = getFluentWait().until(ExpectedConditions.visibilityOf(driver().findElement(by)));
		element.click();
	}
	
	public void enterText(By by,String text) {
		waitForElementToBeVisible(by);
	  driver().findElement(by).sendKeys("text");
	}
	
	public String getPageTitle() {
		return driver().getTitle();
	}
	public void quit() {
		driver().quit();
	}
	
	
	public By getBy(String locatorKey, Object... value) {
		String[] parts = validateLocatorStrategy(locatorKey);
		String strategy = parts[0].trim().toLowerCase();
		String locator = parts[1].trim();
		locator = String.format(locator, value);
		By by = null;
		switch (strategy) {
		case "classname":
			by = By.className(locator);
			break;
		case "id":
			by = By.id(locator);
			break;
		case "linktext":
			by = By.linkText(locator);
			break;
		case "name":
			by = By.name(locator);
			break;
		case "partiallinktext":
			by = By.partialLinkText(locator);
			break;
		case "tagname":
			by = By.tagName(locator);
			break;
		case "xpath":
			by = By.xpath(locator);
			break;
		case "css":
			by = By.cssSelector(locator);
			break;
		default:
			throw new RuntimeException("Invalid (or not supported) locator strategy : " + strategy);
		}
		return by;
	}
	private String[] validateLocatorStrategy(final String locatorKey) {
		String locatorKeyValue = getPropertyValue(locatorKey);
		System.out.println(locatorKeyValue);
		if (null == locatorKeyValue) {
                       throw new RuntimeException("Key is not defined in locator properties file: " + locatorKey);
        }
        if (locatorKeyValue.isEmpty()) {
            throw new RuntimeException(locatorKey + " locator key value is not defined in locator properties file");
        }
        String[] parts = locatorKeyValue.split("=", 2);
        if (parts.length < 2) {
            throw new RuntimeException("Invalid locator:" + locatorKey
                    + " Required \'<locstrategy>=<value>\', for example: \'id=userName\'");
        }
        return parts;
		
		
		
	}
	
	private String getPropertyValue(String locatorKey) {
		String resourcePath = "./src/test/resources/com/qa/locators/saucedemo.properties";
		Properties pro = new Properties();
		FileInputStream file = null;
		try {
			file = new FileInputStream(resourcePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pro.load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pro.getProperty(locatorKey);
		
	}

}
