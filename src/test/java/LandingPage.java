
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.SideNavigationMenu;
import testUtils.BaseTest;
import utils.WaitUtils;

public class LandingPage extends BaseTest {
    public WebDriver driver;
    static Logger log = LogManager.getLogger(LandingPage.class.getName());


    @BeforeTest
    public void setupTest() {
        driver = initializeDriver();
        log.info("Driver is initialized");
        driver.get(getUrlFromProperies());
        log.info("Get to Leanplum url");
    }

    @Test
    public void verifyUserCanLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail("tsanov.y@gmail.com");
        log.info("Enter email to log in");
        loginPage.setPassword("vitorbelfort4");
        log.info("Enter password");
        loginPage.setRememberState(true);
        log.info("Checked the remember me");
        loginPage.clickLoginButton();
        log.info("User has successfully logged in");
        SideNavigationMenu navigationMenu = new SideNavigationMenu(driver);
        navigationMenu.setSideNavMenuState(true);
        navigationMenu.selectOptionFromSideMenuNav("Analytics");
        Assert.assertEquals(driver.getTitle(), "Leanplum");
        log.info("User has successfully logged in");

    }

    @AfterTest
    public void tearDown() {

        // Close application.
        driver.quit();


    }
}
