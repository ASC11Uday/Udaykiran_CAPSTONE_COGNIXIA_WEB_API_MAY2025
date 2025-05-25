package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;

public class AdminPage {

    WebDriver driver;
    private static final Logger logger = Logger.getLogger(AdminPage.class);

    // Constructor
    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    By lblAdmin = By.xpath("//span[text()='Admin']");
    By btnAdd = By.xpath("//button[contains(.,'Add')]");

    public void clickAdminMenu() {
        driver.findElement(lblAdmin).click();
        logger.info("Clicked Admin menu");
    }

    public void clickAddUser() {
        driver.findElement(btnAdd).click();
        logger.info("Clicked Add button");
    }
}
