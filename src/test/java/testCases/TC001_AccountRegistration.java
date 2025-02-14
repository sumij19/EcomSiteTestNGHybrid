package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistration extends BaseClass{
	
	@Test
	public void verifyAccountRegistration() {
		
		try {
		logger.info("*********Starting TC001_AccountRegistration*********");
		
		HomePage home=new HomePage(driver);
		home.clickMyAccount();
		home.clickRegister();
		
		logger.info("*********Starting registration TC001_AccountRegistration*********");
		AccountRegistrationPage registerPage=new AccountRegistrationPage(driver);
		registerPage.setFirstName(randomString());
		registerPage.setLastName(randomString().toUpperCase());
		registerPage.setEmail(randomString()+"@gmail.com");// randomly generated the email
		registerPage.setTelephone(randomeNumber());
		
		String password=randomAlphaNumeric();
		
		registerPage.setPassword(password);
		registerPage.setConfirmPassword(password);
		
		registerPage.setPrivacyPolicy();
		
		registerPage.clickContinue();
		
		//Assert.assertEquals("Your Account Has Been Created!", registerPage.getMsgConfirmation());
		if(registerPage.getMsgConfirmation().equals("Your Account Has Been Created!"))
		{
		Assert.assertTrue(true);
		logger.info("*********Finishing TC001_AccountRegistration*********");
		}
		
		else {
			logger.error("***Error logs****");
			logger.debug("***Debug logs****");
			Assert.assertTrue(false);
		}
	}
	
	catch(Exception e)
	{
		Assert.fail();
	}

	}
}
