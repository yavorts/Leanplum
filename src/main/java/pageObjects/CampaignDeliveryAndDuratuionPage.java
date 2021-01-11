package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

/**
 * Page object class for the Delivery and Duration options of the campaign
 * @author yavort
 */
public class CampaignDeliveryAndDuratuionPage {
    WebDriver driver;

    @FindBy(css = ".scheduled")
    private WebElement scheduledOption;

    /**
     * The CampaignDeliveryAndDuratuionPage constructor
     * @param driver The Webdriver instance
     */
    public CampaignDeliveryAndDuratuionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WaitUtils.waitForDocumentReadyState(driver);
    }

    /**
     * Method for clicking the scheduled option for campaign delivery
     */
    public void clickScheduledOption() {
        WaitUtils.waitForVisibleElement(scheduledOption, driver);
        WaitUtils.waitForClickableElement(scheduledOption,driver);
        scheduledOption.click();
    }


}
