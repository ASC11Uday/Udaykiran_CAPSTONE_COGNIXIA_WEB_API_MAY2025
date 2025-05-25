package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LogoutPage {

    WebDriver driver;
    private static final Logger logger = Logger.getLogger(LogoutPage.class);

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    By profileIcon = By.className("oxd-userdropdown-tab");
    By logoutLink = By.xpath("//a[text()='Logout']");

    public void logout() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(profileIcon))
                .click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(logoutLink))
                .click();
        logger.info("Clicked logout and exited application");
    }
}
