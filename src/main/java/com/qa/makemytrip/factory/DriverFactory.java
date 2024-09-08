package com.qa.makemytrip.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qa.makemytrip.exceptions.FrameworkException;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * This class will initialize the webdriver and launch the browser
 * 
 */

public class DriverFactory {
	
	 WebDriver driver;
	 Properties prop;
	 OptionsManager om;
	 
	 public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	 
	 /*
	  * This method will initialize webdriver and return webDriver object
	  * @param browserName
	  * @return WebDriver
	  */
	 public WebDriver init_driver(Properties prop)
	 {
		 System.getProperty("browser");
         om=new OptionsManager(prop);
		 if( prop.getProperty("browser").trim().equalsIgnoreCase("chrome"))
		 {
			 WebDriverManager.chromedriver().setup();
			 //driver=new ChromeDriver(om.getChromeOptions());
			 tlDriver.set(new ChromeDriver(om.getChromeOptions()));
		 }
		 else
		 {
			 System.out.println("Please provide correct browser name");
		 }
		 
		 getDriver().manage().deleteAllCookies();
		 getDriver().manage().window().maximize();
		 getDriver().get(prop.getProperty("url").trim());
		 return getDriver();
	 }
	 
	 public static WebDriver getDriver()
	 {
		return tlDriver.get();
	 }
	 
	 /*
	  * This method will load the properties
	  */
	 public Properties init_prop()
	 {
		 prop=new Properties();
		 FileInputStream ip=null;
		 
		 String envName=System.getProperty("env");
		 if (envName==null)
		 {
			 System.out.println("No env is given..running on default");
			 envName="default";
		 }
		 try {
			 
			 if(envName.equalsIgnoreCase("default"))
			 {
			  ip=new FileInputStream("./src/test/resource/config/config.properties");
			 }
			 else if(envName.equalsIgnoreCase("qa"))
			 {
			  ip=new FileInputStream("./src/test/resource/config/qa.config.properties");
			 }
			 else
			 {
				 System.out.println("select correct environment");
				 throw new FrameworkException("no environment found exception");
			 }
			 prop.load(ip);
		 }
		 catch(FileNotFoundException e)
		 {
			 e.printStackTrace();
		 }
		 catch(IOException e)
		 {
			e.printStackTrace(); 
		 } 
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 return prop;
	 }
	 
	 /*
	  * Take screenshot
	 */
	 public String getScreenshot()
	 {
		 File srcFile=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		 String path="./"+"screenshot/"+System.currentTimeMillis()+".png";
		 File destination=new File(path);
		 try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return path;
	 }
	 

}
