package com.qa.makemytrip.tests;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.makemytrip.base.BaseTest;
import com.qa.makemytrip.constants.Constants;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic - 1: This Epic pas is for home page of make my trip")
@Story("Homepage user story 1: Design home page with various features")
public class HomePageTest extends BaseTest {

	@Description("Home page initial popup visibility")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void homePageClosePopupVisible()
	{
	    Assert.assertEquals(true,homePage.closeLoginPopupVisibility());
	}
	
	@Test
	public void listAllCountries()
	{
		Assert.assertEquals(Constants.COUNTRIES,homePage.getAllCountries());
	}
	
	@DataProvider
	public String[] countryData()
	{
		return new String[]{"Bahrain","India","Saint Barthélemy"};
	}
	
	@Test(dataProvider="countryData")
	public void selectCountry(String country)
	{
		Assert.assertEquals(true,homePage.selectCountryFromAllCountries(country));
		screenshot();
	}
	
	@Test
	public void validateFooter()
	{
		Assert.assertEquals(homePage.getFooterText(), Constants.HomePagePopupFooterText);
	}
	
	@Test
	public void validateInvalidMobileText()
	{
		Assert.assertEquals(homePage.getInvalidMobileText(), Constants.HomePagePopupInvalidMobileText);
	}
	
	@Test
	public void validatePrivacyPolicyURL()
	{
		Assert.assertEquals(homePage.getPrivacyPolicyURL(),Constants.PRIVACY_URL);
	}
	
	@Test
	public void validateUserAgreementURL()
	{
		Assert.assertEquals(homePage.getUserAgreementURL(),Constants.USER_AGREEMENT_URL);
	}
	
	@Test
	public void validateTermsURL()
	{
		Assert.assertEquals(homePage.getTermsURL(),Constants.TERMS_URL);
	}
	
	@DataProvider
	public String[] accountTypes()
	{
		return new String[] {"MyBiz Account","Personal Account"};
	}
	
	@Test(dataProvider="accountTypes")
	public void ValidateAccountTypes(String accountType)
	{
		Assert.assertEquals(homePage.NavigateAccountTypes(accountType), true);
	}
	
	@Test(enabled = false)
	public void validatePopupImage()
	{
		Assert.assertEquals(homePage.imageVisibility(),true);
	}
	
	@Attachment(value = "Screenshot", type = "image/png")
	public byte[] screenshot() {
	    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
	
}
