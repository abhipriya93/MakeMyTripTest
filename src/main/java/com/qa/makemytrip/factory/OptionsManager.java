package com.qa.makemytrip.factory;

import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	
	public OptionsManager(Properties prop)
	{
		this.prop=prop;
	}
	
	public ChromeOptions getChromeOptions()
	{
		co=new ChromeOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("remote")))
		{
			co.setCapability("browserName", "chrome");
			co.setBrowserVersion("126.0");
			
		}
		
		if(Boolean.parseBoolean(prop.getProperty("headless")))
		{
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito")))
		{
			co.addArguments("--incognito");
		}
		co.setCapability("selenoid:options", new HashMap<String, Object>() {{
		   

		    /* How to enable VNC */
		    put("enableVNC", true);
		}});
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions()
	{
		fo=new FirefoxOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("remote")))
		{
			co.setCapability("enableVNC", true);
		}
		
		if(Boolean.parseBoolean(prop.getProperty("headless")))
		{
			fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito")))
		{
			fo.addArguments("--incognito");
		}
		
		return fo;
	}
}
