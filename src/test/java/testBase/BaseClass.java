package testBase;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;  //Log4j
import org.apache.logging.log4j.Logger;  //Log4j

public class BaseClass {

	
		public WebDriver driver;
		public Logger logger;
		public Properties prop;
		
		@BeforeClass
		@Parameters({"os","browser"})
		public void setup(String os,String browser) throws IOException {
			
			//logger initialization
			logger = LogManager.getLogger(this.getClass());
			
			//loading config file
			FileReader file=new FileReader("./src//test//resources//config.properties");
			prop = new Properties();
			prop.load(file);
			
				
			switch(browser.toLowerCase())
			{
			case "chrome":driver=new ChromeDriver();break;
			case "edge":driver=new EdgeDriver();break;
			case "firefox":driver=new FirefoxDriver();break;
			default:System.out.println("Invalid browser");return;
			}
			
						
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			driver.get(prop.getProperty("appURL"));
			driver.manage().window().maximize();
			//Thread.sleep(3000);
			
		}
		
		@AfterClass
		public void tearDown() {
			driver.quit();
		}
		
		public String randomString() {
			String generatedString= RandomStringUtils.randomAlphabetic(10);
			return generatedString;
			
		}
		public String randomeNumber()
		{
			String generatedNumber=RandomStringUtils.randomNumeric(10);
			return generatedNumber;
		}
		
		public String randomAlphaNumeric()
		{
			String str=RandomStringUtils.randomAlphabetic(3);
			String num=RandomStringUtils.randomNumeric(3);
			
			return (str+"@"+num);
		}
}
