package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	//BasePage constructor calling
	public HomePage(WebDriver driver) {
		
		super(driver);
		
	}

	
	//Locators for Register
	@FindBy(xpath="//a[@title='My Account']") WebElement lnkMyAccount;
	@FindBy(xpath="//a[text()='Register']") WebElement lnkRegister;
	@FindBy(linkText = "Login") WebElement lnkLogin;
	
	
	//Action Methods
	public void clickMyAccount() {
		lnkMyAccount.click();
	}

	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		lnkLogin.click();
	}
}
