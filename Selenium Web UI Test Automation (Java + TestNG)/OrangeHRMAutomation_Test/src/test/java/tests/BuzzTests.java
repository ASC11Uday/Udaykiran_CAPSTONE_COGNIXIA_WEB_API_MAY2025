package tests;

import org.testng.annotations.Test;
import pages.BuzzPage;
import pages.LoginPage;
import pages.LogoutPage;
import testbase.BaseTest;

public class BuzzTests extends BaseTest {

    @Test(priority = 4)
    public void postMessageOnBuzzTest() throws Exception {
        logger.info("Running postMessageOnBuzzTest");

        // Login first
        LoginPage login = new LoginPage(driver);
        login.login("Admin", "admin123");
        login.verifyApplicationURL("/dashboard");

        // Navigate to Buzz and post a message
        BuzzPage buzzPage = new BuzzPage(driver);
        buzzPage.navigateToBuzzPage();
        buzzPage.postBuzzMessage("Hi folks, GoodMorning");

        // Optional: Wait briefly if needed for UI delay
        Thread.sleep(1000);

        // Logout
        LogoutPage logout = new LogoutPage(driver);
        logout.logout();
    }
}
