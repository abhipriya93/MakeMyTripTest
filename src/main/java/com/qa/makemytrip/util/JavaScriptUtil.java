package com.qa.makemytrip.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

private WebDriver driver;	
	
	public JavaScriptUtil(WebDriver driver)
	{
		this.driver=driver;
	}
	
    public void scrollToElement(WebElement e)
    {
    	JavascriptExecutor js=(JavascriptExecutor) driver;
    	js.executeScript("arguments[0].scrollIntoView(true);", e);
    }
	
}
