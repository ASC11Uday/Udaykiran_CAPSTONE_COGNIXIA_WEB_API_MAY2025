package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class AddUserPage {

    WebDriver driver;
    private static final Logger logger = Logger.getLogger(AddUserPage.class);

    public AddUserPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By clickAdd = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']");
    By userDropdown = By.xpath("//label[text()='User Role']/following::div[contains(@class,'oxd-select-text')][1]");
    By empNameField = By.xpath("//input[@placeholder='Type for hints...']");
    By suggestionList = By.xpath("//div[@role='listbox']//span");
    By statusDropdown = By.xpath("//label[text()='Status']/following::div[contains(@class,'oxd-select-text')][1]");
    By userName = By.xpath("//label[text()='Username']/following::input[@autocomplete='off'][1]");
    By password = By.xpath("//label[text()='Password']/following::input[@type='password'][1]");
    By confirmPassword = By.xpath("//label[text()='Confirm Password']/following::input[@type='password'][1]");
    By saveUser = By.xpath("//button[@type='submit' and contains(@class, 'orangehrm-left-space')]");

    public void clickAddUser() {
    	new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(clickAdd));
        driver.findElement(clickAdd).click();
        logger.info("Clicked Add User button");
    }

    public void selectRole(String roleName) {
        driver.findElement(userDropdown).click();
        String xpathOption = "//div[@role='listbox']//span[text()='" + roleName + "']";
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathOption)));
        driver.findElement(By.xpath(xpathOption)).click();
        logger.info("Selected role: " + roleName);
    }

    public void selectEmployeeName() throws InterruptedException {
        driver.findElement(empNameField).sendKeys("Rahul");
        Thread.sleep(1000);
        List<WebElement> suggestions = driver.findElements(suggestionList);
        for (WebElement suggestion : suggestions) {
            if (suggestion.getText().contains("Rahul Das")) {
                suggestion.click();
                logger.info("Selected employee: Rahul Das");
                break;
            }
        }
    }

    public void selectStatus(String statusValue) {
        driver.findElement(statusDropdown).click();
        String optionXpath = "//div[@role='listbox']//span[text()='" + statusValue + "']";
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXpath)));
        driver.findElement(By.xpath(optionXpath)).click();
        logger.info("Selected status: " + statusValue);
    }

    public void enterUserName() {
        driver.findElement(userName).clear();
        driver.findElement(userName).sendKeys("Udaykiran0009");
        logger.info("Entered username: Udaykiran0009");
    }

    public void enterPassword() {
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys("Admin@123");
        logger.info("Entered password");
    }

    public void enterConfirmPassword() {
        driver.findElement(confirmPassword).clear();
        driver.findElement(confirmPassword).sendKeys("Admin@123");
        logger.info("Entered confirm password");
    }

    public void clickSaveButton() {
        driver.findElement(saveUser).click();
        logger.info("Clicked Save button");
    }

    public void addUser(String roleName, String statusValue) throws InterruptedException {
        clickAddUser();
        selectRole(roleName);
        selectEmployeeName();
        selectStatus(statusValue);
        enterUserName();
        enterPassword();
        enterConfirmPassword();
        clickSaveButton();
        logger.info("User added successfully");
    }
}
