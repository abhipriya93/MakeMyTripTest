package com.qa.makemytrip.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.makemytrip.factory.DriverFactory;
import com.qa.makemytrip.pages.HomePage;
import com.qa.makemytrip.pages.PrivacyPolicy;

public class BaseTest {
	
	public DriverFactory df;
	public Properties prop;
	
	public WebDriver driver;
	
	public HomePage homePage;
	public PrivacyPolicy policy;
		
	@BeforeTest
	public void setup()
	{
		df=new DriverFactory();
		prop=df.init_prop();
		driver=df.init_driver(prop);
		homePage= new HomePage(driver);
	}
	
	@AfterTest
	public void tearDown()
	{
		//driver.quit();
	}
	

}
