package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups= {"sanity","regression"})
	public  void verifyLoginTest() {
		
		try {
			logger.info("*********Starting TC002_LoginTest*********");
			
			HomePage home=new HomePage(driver);
			home.clickMyAccount();
			home.clickLogin();
			
			LoginPage login=new LoginPage(driver);
			login.setEmail(prop.getProperty("email"));
			login.setPassword(prop.getProperty("password"));
			login.clickLogin();
			
			MyAccountPage myAccount=new MyAccountPage(driver);
			boolean targetPage = myAccount.isMyAccountDisplayed();
			
			//Assert.assertEquals(targetPage, true,"Login failed");
			Assert.assertTrue(targetPage);
			
			myAccount.clickLogout();
			
	}
		catch(Exception e)
		{
			Assert.fail();
		}

}

}