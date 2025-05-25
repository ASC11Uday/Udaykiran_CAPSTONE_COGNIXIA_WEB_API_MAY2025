package tests;

import org.testng.annotations.Test;
import pages.LoginPage;
import pages.LogoutPage;
import testbase.BaseTest;

public class LoginTests extends BaseTest {

    @Test(priority = 1)
    public void validLoginTest() throws Exception {
        logger.info("Running validLoginTest");
        LoginPage login = new LoginPage(driver);
        login.login("Admin", "admin123");
        login.verifyApplicationURL("/dashboard");
        LogoutPage logout = new LogoutPage(driver);
        logout.logout();
    }

    @Test(priority = 2)
    public void invalidLoginTest() throws Exception {
        logger.info("Running invalidLoginTest");
        LoginPage login = new LoginPage(driver);
        login.login("InvalidUser", "InvalidPass");
        Thread.sleep(3000); 
    }
}
