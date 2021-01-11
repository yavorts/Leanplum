package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;
import utils.WebElementUtils;

import java.util.List;

/**
 * Class for the Campaign actions page object
 * @author yavort
 */
public class CampaignActions {
    WebDriver driver;

    @FindBy(css = ".outside-app span")
    private List<WebElement> outsideAppOptions;

    @FindBy(css = ".field-value-view input[name=Message]")
    private WebElement messageForPushNotifiations;

    /**
     * Constructor of the CampaignActions class
     * @param driver The Webdriver instance
     */
    public CampaignActions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WaitUtils.waitForDocumentReadyState(driver);
    }

    /**
     * Method to click one the outside app options
     * @param option The option to click
     */
    public void clickOutsideAppOption(String option) {
        WaitUtils.waitForListOfElementsToBeVisible(outsideAppOptions,driver);
        WebElementUtils.clickElementWithTextFromListOfElements(option, outsideAppOptions);
    }

    /**
     * Method to set the text for the push notifications
     * @param message The message in the push notifications
     */
    public void setMessagePushNotifications(String message) {
        WaitUtils.waitForClickableElement(messageForPushNotifiations, driver);
        messageForPushNotifiations.sendKeys(message);
    }
}
