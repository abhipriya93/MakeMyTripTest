package com.qa.makemytrip.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.qa.makemytrip.util.ElementUtil;
import com.qa.makemytrip.util.JavaScriptUtil;

public class PrivacyPolicy {
	
private WebDriver driver;
	
private ElementUtil eUtil;
private JavaScriptUtil jUtil;

private By indiaPolicy=By.xpath("//a[@href='#IndiaPolicy']");
private By europePolicy=By.xpath("//a[@href='#EuropePolicy']");
private By indiaPolicyTopHeading=By.xpath("//div[@id='IndiaPolicy']"
		                                     + "/div[@class='top_section append_bottom clearFix']"
		                                     + "/p[@class='top_heading append_bottomHalf']");
	
private By introductionText=By.xpath("//div[@id='IndiaPolicy']/div[@class='top_section append_bottom clearFix']/p[@class='heading_info margn_bttm']");


	public PrivacyPolicy(WebDriver driver)
	{
		this.driver=driver;
		eUtil=new ElementUtil(driver);
		jUtil=new JavaScriptUtil(driver);
	}
	

	public List<String> getIntroductionText()
	{
		
		return eUtil.getListofText(eUtil.getElements(introductionText));
	}
	

}
