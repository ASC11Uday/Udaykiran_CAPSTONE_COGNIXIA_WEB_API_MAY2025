package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BuzzPage {

    WebDriver driver;
    private static final Logger logger = Logger.getLogger(BuzzPage.class);

    public BuzzPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By buzzMenu = By.xpath("//a[contains(@href,'/buzz/viewBuzz')]");
    By postTextArea = By.xpath("//textarea[@class='oxd-buzz-post-input' and @placeholder=\"What's on your mind?\"]");
    By postButton = By.xpath("//button[@type='submit' and contains(@class,'oxd-button--main')]");

    public void navigateToBuzzPage() {
        driver.findElement(buzzMenu).click();
        logger.info("Navigated to Buzz page");
    }

    public void postBuzzMessage(String message) {
        driver.findElement(postTextArea).sendKeys(message);
        logger.info("Entered message: " + message);

        driver.findElement(postButton).click();
        logger.info("Clicked Post button");
    }
}
