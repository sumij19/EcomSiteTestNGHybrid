package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistration extends BaseClass{
	
	@Test
	public void verifyAccountRegistration() {
		
		HomePage home=new HomePage(driver);
		home.clickMyAccount();
		home.clickRegister();
		
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
		
		Assert.assertEquals("Your Account Has Been Created!", registerPage.getMsgConfirmation());
	}

}
