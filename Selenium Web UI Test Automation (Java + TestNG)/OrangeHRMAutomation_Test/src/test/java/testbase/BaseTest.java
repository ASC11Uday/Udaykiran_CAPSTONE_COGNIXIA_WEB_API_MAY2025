package testbase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;

public class BaseTest {

    public static WebDriver driver;
    public static Properties config;
    public static Logger logger;

    @BeforeSuite
    public void loadConfig() {
        logger = Logger.getLogger(BaseTest.class);

        try {
            PropertyConfigurator.configure("src/test/resources/log4j.properties");
            logger.info("Log4j configured successfully.");
        } catch (Exception e) {
            System.err.println("ERROR: Failed to configure Log4j - " + e.getMessage());
        }

        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            config = new Properties();
            config.load(fis);
            logger.info("Configuration loaded successfully.");
        } catch (IOException e) {
            logger.error("Failed to load configuration: " + e.getMessage());
        }
    }

    @BeforeMethod
    public void setup() {
        String browser = config.getProperty("browser");
        String headlessFlag = config.getProperty("headless");
        String appURL = config.getProperty("url");

        if (browser == null || appURL == null) {
            logger.error("Browser or URL is not specified in config.properties");
            throw new RuntimeException("Missing browser or URL config");
        }

        logger.info("Launching browser: " + browser);

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if ("true".equalsIgnoreCase(headlessFlag)) {
                    chromeOptions.addArguments("--headless=new");
                }
                driver = new ChromeDriver(chromeOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(); // No headless mode enabled
                break;

            default:
                logger.error("Unsupported browser: " + browser);
                throw new RuntimeException("Unsupported browser in config.properties");
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(appURL);
        logger.info("Navigated to: " + appURL);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed.");
        }
    }
}

