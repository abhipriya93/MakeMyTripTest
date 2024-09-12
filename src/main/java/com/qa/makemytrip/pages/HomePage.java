package com.qa.makemytrip.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.makemytrip.constants.Constants;
import com.qa.makemytrip.util.ElementUtil;
import com.qa.makemytrip.util.JavaScriptUtil;

import io.qameta.allure.Step;

public class HomePage {

	private WebDriver driver;

	private ElementUtil eUtil;
	private JavaScriptUtil jUtil;

	private By closeLogin = By.xpath("//section[@data-cy='CommonModal_2']/span");
	private By mobileNumber = By.xpath("//input[@data-cy='userName']");
	private By defaultCountry = By.xpath("//p[@data-cy='MobileCodeDropDown_59']");
	private By countryDropdownName = By.xpath("//div[@data-cy='List_57']/span[2]");
	private By countryInitials;
	private By footer = By.xpath("//div[@class='makeFlex column hrtlCenter loginFooter']/p");
	private By invalidMobileText = By.xpath("//p[@data-cy='error']");
	private By privacyPolicy = By
			.xpath("//div[@class='makeFlex column hrtlCenter loginFooter']//a[text()='Privacy Policy']");
	private By userAgreement = By.xpath("//p/a[text()='User Agreement']");
	private By Terms = By.linkText("T&Cs");
	private By accountTypeList = By.xpath("//ul[@data-cy='LoginFlowPopup_82']/li");
	private By myBizHeader = By.xpath("//form[@data-cy='MyBizLogin_116']/p");
	private By personalAccountHeader = By.xpath("//label[@for='username']");
	private By popupImage = By.xpath("//div[@data-cy='RegularSaleLoginWrapper']/img");
	private By sliderDotIcon = By.xpath("//p[@data-testid='slider-dot-item']/span");
	private By sliderImage = By.xpath("//div[@data-cy='login-slider-comp-wrapper']");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtil(driver);
		jUtil = new JavaScriptUtil(driver);
	}

	public boolean closeLoginPopupVisibility() {
		WebElement closeLoginButton = eUtil.waitForElementVisible(closeLogin, Constants.DEFAULT_ELEMENT_TIME_OUT);
		if (closeLoginButton != null) {
			return true;

		}
		return false;
	}

	@Step("All countries in dropdown of popup")
	public int getAllCountries() {
		WebElement defaultCon = eUtil.waitForElementVisible(defaultCountry, Constants.DEFAULT_ELEMENT_TIME_OUT);
		eUtil.performClick(defaultCon);
		List<WebElement> countryList = eUtil.getElements(countryDropdownName);
		for (WebElement e : countryList) {
			jUtil.scrollToElement(e);
			String cName = eUtil.returnStringBeforeCharacter(e.getText(), '(');
			System.out.print("Country Name: " + cName);
			System.out.print(" Country Code:" + eUtil.returnStringBetweenCharacters(e.getText(), '(', ')'));
			System.out.println(" Country Initials: " + getCountryInitials(cName));
		}
		eUtil.performClick(mobileNumber);
		return countryList.size();
	}

	public String getCountryInitials(String country) {

		try {
			countryInitials = eUtil.convertTextToXpath(
					"//div[@data-cy='List_57']/span[text()=\"" + country + "\"]/following-sibling::span");
			return eUtil.getElementText(countryInitials);
		} catch (NoSuchElementException e) {
			countryInitials = eUtil.convertTextToXpath(
					"//div[@data-cy='List_57']/span[contains(text(),'" + country + "')]/following-sibling::span");
			return eUtil.getElementText(countryInitials);
		}
	}

	@Step("Open the country Drop down")
	public boolean selectCountryFromAllCountries(String country) {
		WebElement defaultCon = eUtil.waitForElementVisible(defaultCountry, Constants.DEFAULT_ELEMENT_TIME_OUT);
		eUtil.performClick(defaultCon);
		List<WebElement> countryList = eUtil.getElements(countryDropdownName);
		return selectCountryFromDropdown(countryList,country);
	}
    
	@Step("Select country from dropdown")
	public boolean selectCountryFromDropdown(List<WebElement> countryList,String country) {
		for (WebElement e : countryList) {
			String countryName = e.getText();
			if (countryName.contains(country)) {
				jUtil.scrollToElement(e);
				eUtil.performClick(e);
				return true;
			}
		}
		System.out.println("Country " + country + " Not found on List");
		return false;
	}

	public String getFooterText() {
		return eUtil.waitForElementVisible(footer, 5).getText();
	}

	public String getInvalidMobileText() {
		eUtil.performClick(mobileNumber);
		eUtil.performClick(footer);
		return eUtil.getElement(invalidMobileText).getText();
	}

	public PrivacyPolicy navigatePrivacyPolicy() {
		eUtil.waitForElementPerformClick(privacyPolicy, 5);
		return new PrivacyPolicy(driver);
	}

	public String getPrivacyPolicyURL() {
		eUtil.waitForElementPerformClick(privacyPolicy, 5);
		String purl = eUtil.getURL();
		eUtil.NavigateBack();
		return purl;
	}

	public String getUserAgreementURL() {
		eUtil.waitForElementClickablePerformClick(userAgreement, 5);
		String uaurl = eUtil.getURL();
		eUtil.NavigateBack();
		return uaurl;
	}

	public String getTermsURL() {
		eUtil.waitForElementClickablePerformClick(Terms, 5);
		String turl = eUtil.getURL();
		eUtil.NavigateBack();
		return turl;
	}

	public boolean NavigateAccountTypes(String aType) {
		eUtil.waitForElementVisible(accountTypeList, 5);
		for (WebElement e : eUtil.getElements(accountTypeList)) {
			if (eUtil.getElementText(e).equalsIgnoreCase(aType)) {
				eUtil.performClick(e);
				break;
			}
		}

		if (aType.contains("Biz")) {
			return eUtil.elementDisplayed(myBizHeader);
		} else if (aType.contains("Personal")) {
			return eUtil.elementDisplayed(personalAccountHeader);
		}
		return false;
	}

	public boolean imageVisibility() {
		if (eUtil.waitForElementDisplayed(popupImage, 5) != null) {
			return true;
		}
		return false;
	}
}
