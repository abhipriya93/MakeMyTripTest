package com.qa.makemytrip.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.makemytrip.base.BaseTest;
import com.qa.makemytrip.constants.Constants;

public class PrivacyPolicyTest extends BaseTest{
	
	@BeforeClass
	public void NavigateURL()
	{
	  policy=homePage.navigatePrivacyPolicy();
	}
	
	@Test
	public void ValidateIntroductionText()
	{
		Assert.assertEquals(policy.getIntroductionText(), Constants.PRIVACY_INTRO);
	}

}
