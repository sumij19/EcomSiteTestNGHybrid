package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;  // Log4j
import org.apache.logging.log4j.Logger;  // Log4j

public class BaseClass {

    public WebDriver driver;
    public Logger logger;
    public Properties prop;

    @BeforeClass(groups = { "sanity", "regression" })
    @Parameters({"os", "browser"})
    public void setup(String os, String browser) throws IOException, URISyntaxException {
        
        // Logger initialization
        logger = LogManager.getLogger(this.getClass());

        // Loading config file
        FileReader file = new FileReader("./src/test/resources/config.properties");
        prop = new Properties();
        prop.load(file);

        // Remote execution
        if (prop.getProperty("execution_env").equals("remote")) {
            DesiredCapabilities cap = new DesiredCapabilities();

            // Selecting OS
            switch (os.toLowerCase()) {
                case "windows":
                    cap.setPlatform(Platform.WIN11);
                    break;
                case "mac":
                    cap.setPlatform(Platform.MAC);
                    break;
                case "linux":
                    cap.setPlatform(Platform.LINUX);
                    break;
                default:
                    System.out.println("Invalid OS");
                    return;
            }

            // Selecting browser
            switch (browser.toLowerCase()) {
                case "chrome":
                    cap.setBrowserName("chrome");
                    break;
                case "edge":
                    cap.setBrowserName("MicrosoftEdge");
                    break;
                case "firefox":
                    cap.setBrowserName("firefox");
                    break;
                default:
                    System.out.println("Invalid browser");
                    return;
            }

            // Initializing remote driver using URI
            URI uri = new URI("http://192.168.1.6:4444");
            driver = new RemoteWebDriver(uri.toURL(), cap);
        }

        // Local execution
        if (prop.getProperty("execution_env").equals("local")) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                /*case "firefox":
                    driver = new FirefoxDriver();
                    break;*/
                default:
                    System.out.println("Invalid browser");
                    return;
            }
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(prop.getProperty("appURL"));
        driver.manage().window().maximize();
    }

    @AfterClass(groups = { "sanity", "regression" })
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String randomString() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphaNumeric() {
        String str = RandomStringUtils.randomAlphabetic(3);
        String num = RandomStringUtils.randomNumeric(3);
        return str + "@" + num;
    }
}
