import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.*;
import testUtils.BaseTest;

import java.net.MalformedURLException;

public class NewCampaignTests extends BaseTest {
    WebDriver driver;
    static Logger log = LogManager.getLogger(NewCampaignTests.class.getName());


    @BeforeTest
    public void setupTest() throws MalformedURLException {
        //init Driver
        driver = initializeDriver();
        //Get to the Leanplum url
        driver.get(getUrlFromProperies());

    }

    @Test
    public void verifyFlowCampaignToFinish() throws InterruptedException {
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
        navigationMenu.selectOptionFromSideMenuNav("Campaigns");
        CampaignsPage campaignPage = new CampaignsPage(driver);
        campaignPage.clickNewCampaignButton();
        NewCampaignPage newCampaignPageGoal = new NewCampaignPage(driver);
        newCampaignPageGoal.selectCampaignGoal("Onboard");
        newCampaignPageGoal.clickCampaignName();

        CampaignSettingsPage campaignSettingsPage = new CampaignSettingsPage(driver);
        campaignSettingsPage.setCampaignName("Yavor's Campaign");
        campaignSettingsPage.selectSaveOrCancel("Save");
        NewCampaignPage newCampaignPageDMethod = new NewCampaignPage(driver);
        newCampaignPageDMethod.selectCampaignOption("Delivery Method");
        CampaignDeliveryAndDuratuionPage delivery = new CampaignDeliveryAndDuratuionPage(driver);
        delivery.clickScheduledOption();
        DatePicker datePicker = new DatePicker(driver);
        datePicker.openDatePicker();
        datePicker.setDayToDatePicker("22");
        NewCampaignPage newCampaignActions = new NewCampaignPage(driver);
        newCampaignActions.selectCampaignOption("Actions");
        CampaignActions actions = new CampaignActions(driver);
        actions.clickOutsideAppOption("Push Notification");
        actions.setMessagePushNotifications("Message");
        newCampaignActions.clickStartCampaignButton();
        StartCampaignPopup popup = new StartCampaignPopup(driver);
        popup.clickStartCampaign();
        NewCampaignPage newCampaignPage = new NewCampaignPage(driver);
        newCampaignPage.clickEndCampaignButton();
        newCampaignPage.clickEndCampaignOnPopupMessage();
        Assert.assertEquals("FINISHED", newCampaignPage.getEndStatusCampaign());
    }

    @AfterTest
    public void tearDown() {

        // Close application.
        driver.quit();


    }
}
