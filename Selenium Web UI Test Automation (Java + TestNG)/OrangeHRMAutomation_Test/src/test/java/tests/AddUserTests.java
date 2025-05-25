package tests;

import org.testng.annotations.Test;
import pages.AddUserPage;
import pages.LoginPage;
import pages.AdminPage;
import pages.LogoutPage; 
import testbase.BaseTest;

public class AddUserTests extends BaseTest {

    @Test(priority = 3)
    public void addNewUserTest() throws Exception {
        logger.info("Running addNewUserTest");

        // Login
        LoginPage login = new LoginPage(driver);
        login.login("Admin", "admin123");
        login.verifyApplicationURL("/dashboard");

        // Navigate to Admin page
        AdminPage adminPage = new AdminPage(driver);
        adminPage.clickAdminMenu();

        // Add user
        AddUserPage addUser = new AddUserPage(driver);
        addUser.addUser("Admin", "Enabled");

        // Logout
        LogoutPage logout = new LogoutPage(driver);
        logout.logout();
    }
}
