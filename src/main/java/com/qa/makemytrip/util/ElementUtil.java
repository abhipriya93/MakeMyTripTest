package com.qa.makemytrip.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Attachment;

public class ElementUtil {
	
private WebDriver driver;	
	
	public ElementUtil(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public WebElement getElement(By locator)
	{
		return driver.findElement(locator);
	}
	
	public List <WebElement> getElements(By locator)
	{
		return driver.findElements(locator);
	}
	
	public String returnStringBeforeCharacter(String name,char limiter)
	{
		int index=name.indexOf(limiter);
		return name.substring(0, index).trim();
	}
	
	public String returnStringBetweenCharacters(String name,char limiter1,char limiter2)
	{
		int index=name.indexOf(limiter1);
		int index2=name.indexOf(limiter2);
		return name.substring(index+1, index2).trim();
	}
	
	public String waitForTitle(String title,int timeout)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
		if (wait.until(ExpectedConditions.urlContains(title)))
		{
			return driver.getCurrentUrl();
		}
		return null;
	}

	public WebElement waitForElementVisible(By locator,int timeout)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
		
	public String getURL()
	{
		return driver.getCurrentUrl();
	}
	
	public void NavigateBack()
	{
		driver.navigate().back();
	}
	
	public List<String> getListofText(List<WebElement> e)
	{
	  List<String> fText=new ArrayList<String>();	
	  if (e.size()==0)
	  {
		  System.out.println("Null List");
		  return null;
	  }
		for(WebElement eText:e)
		{
			fText.add(eText.getText());
		}
		return fText;
	}
	
	public void performClick(By locator)
	{
		getElement(locator).click();
	}

	public void performClick(WebElement e)
	{
		e.click();
	}
	
	public void waitForElementPerformClick(By locator,int timeout)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
	}
	
	public void waitForElementClickablePerformClick(By locator,int timeout)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	public String getElementText(By locator)
	{
		return getElement(locator).getText();
	}
	
	public String getElementText(WebElement e)
	{
		return e.getText();
	}
	
	public By convertTextToXpath(String path)
	{
		return By.xpath(path);
	}
	
	public boolean elementDisplayed(By locator)
	{
		return getElement(locator).isDisplayed();
	}
	
	public WebElement waitForElementDisplayed(By locator,int timeout)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	@Attachment(value = "Screenshot", type = "image/png")
	public byte[] screenshot() {
	    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
}
